package com.QR.QRProject.services;


import com.QR.QRProject.dtos.MenuDto;
import com.QR.QRProject.dtos.MenuDtoIU;
import com.QR.QRProject.dtos.MenuItemDto;
import com.QR.QRProject.dtos.MenuItemDtoIU;
import com.QR.QRProject.entities.Menu;

import java.util.List;

public interface MenuService {
    List<MenuDto> findAllByCompanyId(Long companyId);
    List<MenuDto> findAllByCompanyName(String  companyName);
    List<MenuItemDto> findAllItemsByMenuId(Long menuId);
    MenuItemDto saveMenuItem(MenuItemDtoIU menuItemDto);
    MenuDto saveMenu(MenuDtoIU menuDtoIU);
    boolean removeMenuById(Long menuId);
}
