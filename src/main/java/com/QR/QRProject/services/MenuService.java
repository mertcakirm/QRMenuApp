package com.QR.QRProject.services;


import com.QR.QRProject.dtos.*;
import com.QR.QRProject.entities.Menu;

import java.util.List;

public interface MenuService {
    List<MenuDto> findAllByToken();
    MenuWithCompanyDto findAllByCompanyName(String companyName);
    MenuWithItemsDto findMenuWithItems(Long menuId);
    MenuItemDto saveMenuItem(MenuItemDtoIU menuItemDto);
    MenuDto saveMenu(MenuDtoIU menuDtoIU);
    boolean removeMenuById(Long menuId);
}
