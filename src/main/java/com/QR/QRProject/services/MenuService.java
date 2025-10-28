package com.QR.QRProject.services;


import com.QR.QRProject.dtos.MenuDto;
import com.QR.QRProject.dtos.MenuItemDto;
import com.QR.QRProject.dtos.MenuItemDtoIU;

import java.util.List;

public interface MenuService {
    List<MenuDto> findAllByCompanyId(Long companyId);
    List<MenuItemDto> findAllItemsByMenuId(Long menuId);
    MenuItemDto saveMenuItem(MenuItemDtoIU menuItemDto);
}
