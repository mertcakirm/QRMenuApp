package com.QR.QRProject.services.Impl;

import com.QR.QRProject.dtos.*;
import com.QR.QRProject.entities.Menu;
import com.QR.QRProject.entities.MenuItem;
import com.QR.QRProject.entities.MenuItemVariant;
import com.QR.QRProject.repositories.MenuItemRepository;
import com.QR.QRProject.repositories.MenuItemVariantRepository;
import com.QR.QRProject.repositories.MenuRepository;
import com.QR.QRProject.services.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuItemVariantRepository menuItemVariantRepository;


    @Override
    public List<MenuDto> findAllByCompanyId(Long companyId) {

        List<Menu> menuDb = menuRepository.findAllByCompanyId(companyId);

        return menuDb.stream()
                .map(menu -> {
                    MenuDto dto = new MenuDto();
                    BeanUtils.copyProperties(menu, dto);
                    return dto;
                })
                .toList();
    }

    @Override
    public List<MenuItemDto> findAllItemsByMenuId(Long menuId) {
        List<MenuItem> menuItems = menuItemRepository.findAllByMenuId(menuId);

        return menuItems.stream()
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


}
