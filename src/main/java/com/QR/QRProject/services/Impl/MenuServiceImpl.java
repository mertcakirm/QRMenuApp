package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.*;
import com.QR.QRProject.entities.*;
import com.QR.QRProject.repositories.*;
import com.QR.QRProject.security.JwtUtil;
import com.QR.QRProject.services.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuItemVariantRepository menuItemVariantRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MenuDto> findAllByToken() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));


        List<Menu> menuDb = menuRepository.findAllByCompanyId(user.getCompany().getId());

        return menuDb.stream()
                .map(menu -> {
                    MenuDto dto = new MenuDto();
                    BeanUtils.copyProperties(menu, dto);
                    return dto;
                })
                .toList();
    }

    @Override
    public MenuWithCompanyDto findAllByCompanyName(String companyName) {
        // Optional ile çağırıyoruz
        Optional<Company> optionalCompany = companyRepository.findByName(companyName);
        if (optionalCompany.isEmpty()) return null;

        Company company = optionalCompany.get();

        MenuWithCompanyDto dto = new MenuWithCompanyDto();
        dto.setCompanyName(company.getName());
        dto.setCompanyEmail(company.getEmail());
        dto.setCompanyPhone(company.getPhone());
        dto.setCompanyAddress(company.getAddress());

        List<MenuDto> menuDtos = company.getMenus().stream()
                .map(menu -> {
                    MenuDto menuDto = new MenuDto();
                    menuDto.setId(menu.getId());
                    menuDto.setTitle(menu.getTitle());
                    menuDto.setBase64Image(menu.getBase64Image());
                    return menuDto;
                })
                .toList();

        dto.setItems(menuDtos);

        return dto;
    }

    @Override
    public MenuWithItemsDto findMenuWithItems(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        List<MenuItem> menuItems = menuItemRepository.findAllByMenuId(menuId);

        MenuWithItemsDto menuDto = new MenuWithItemsDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getTitle());
        menuDto.setDescription(menu.getDescription());
        menuDto.setBase64Image(menu.getBase64Image());

        List<MenuItemDto> itemDtos = menuItems.stream()
                .map(menuItem -> {
                    MenuItemDto dto = new MenuItemDto();
                    BeanUtils.copyProperties(menuItem, dto);

                    if (menuItem.getVariants() != null && !menuItem.getVariants().isEmpty()) {
                        List<MenuItemVariantDto> variantDtos = menuItem.getVariants().stream()
                                .map(variant -> {
                                    MenuItemVariantDto vDto = new MenuItemVariantDto();
                                    BeanUtils.copyProperties(variant, vDto);
                                    return vDto;
                                })
                                .toList();
                        dto.setVariants(variantDtos);
                    }

                    return dto;
                })
                .toList();

        menuDto.setItems(itemDtos);

        return menuDto;
    }



    @Override
    public MenuItemDto saveMenuItem(MenuItemDtoIU menuItemDto) {
        if (menuItemDto == null) return null;

        Menu menu = menuRepository.findById(menuItemDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu bulunamadı"));

        MenuItem menuItem = new MenuItem();
        BeanUtils.copyProperties(menuItemDto, menuItem);
        menuItem.setMenu(menu);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        if (menuItemDto.getVariants() != null && !menuItemDto.getVariants().isEmpty()) {
            List<MenuItemVariant> savedVariants = new ArrayList<>();
            for (MenuItemVariantDtoIU vDto : menuItemDto.getVariants()) {
                MenuItemVariant variant = new MenuItemVariant();
                BeanUtils.copyProperties(vDto, variant);
                variant.setMenuItem(savedMenuItem);

                MenuItemVariant savedVariant = menuItemVariantRepository.save(variant);
                savedVariants.add(savedVariant);
            }
            savedMenuItem.setVariants(savedVariants);
        }

        MenuItemDto resultDto = new MenuItemDto();
        BeanUtils.copyProperties(savedMenuItem, resultDto);

        List<MenuItemVariantDto> variantDtos = new ArrayList<>();
        for (MenuItemVariant v : savedMenuItem.getVariants()) {
            MenuItemVariantDto dto = new MenuItemVariantDto();
            BeanUtils.copyProperties(v, dto);
            variantDtos.add(dto);
        }
        resultDto.setVariants(variantDtos);

        return resultDto;
    }

    @Override
    public MenuDto saveMenu(MenuDtoIU menuDtoIU) {
        if (menuDtoIU == null) return null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Menu menu = new Menu();
        menu.setCompany(user.getCompany());
        menu.setTitle(menuDtoIU.getTitle());
        menu.setDescription(menuDtoIU.getDescription());
        menu.setBase64Image(menuDtoIU.getBase64Image());

        Menu savedMenu = menuRepository.save(menu);

        MenuDto resultDto = new MenuDto();
        BeanUtils.copyProperties(savedMenu, resultDto);
        return resultDto;
    }

    @Override
    public boolean removeMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if (menu != null) {
            menuRepository.delete(menu);
            return true;
        }
        return false;
    }

}
