package com.QR.QRProject.controllers;


import com.QR.QRProject.dtos.MenuDto;
import com.QR.QRProject.dtos.MenuItemDto;
import com.QR.QRProject.dtos.MenuItemDtoIU;
import com.QR.QRProject.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("get-all/{companyId}")
    public List<MenuDto> getAll(@PathVariable Long companyId) {
        return menuService.findAllByCompanyId(companyId);
    }

    @GetMapping("get-by-menuId/{menuId}")
    public List<MenuItemDto> getByMenuId(@PathVariable Long menuId) {
        return menuService.findAllItemsByMenuId(menuId);
    }

    @PostMapping("add-menu-item")
    public MenuItemDto addMenuItem(@RequestBody MenuItemDtoIU menuItemDtoIU) {
        return menuService.saveMenuItem(menuItemDtoIU);
    }
}
