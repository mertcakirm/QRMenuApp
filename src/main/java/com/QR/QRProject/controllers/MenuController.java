package com.QR.QRProject.controllers;

import com.QR.QRProject.dtos.*;
import com.QR.QRProject.services.MenuItemService;
import com.QR.QRProject.services.MenuService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("get-all")
    public List<MenuDto> getAll() {
        return menuService.findAllByToken();
    }

    @GetMapping("get-all-by-name/{companyName}")
    public MenuWithCompanyDto getAllByName(@PathVariable String companyName) {
        return menuService.findAllByCompanyName(companyName);
    }

    @GetMapping("get-by-menuId/{menuId}")
    public MenuWithItemsDto getByMenuId(@PathVariable Long menuId) {
        return menuService.findMenuWithItems(menuId);
    }

    @PostMapping("add-menu-item")
    @SecurityRequirement(name = "bearerAuth")
    public MenuItemDto addMenuItem(@RequestBody MenuItemDtoIU menuItemDtoIU) {
        return menuService.saveMenuItem(menuItemDtoIU);
    }

    @PostMapping("create-menu")
    @SecurityRequirement(name = "bearerAuth")
    public MenuDto createItem(@RequestBody MenuDtoIU menuDtoIU) {
        return menuService.saveMenu(menuDtoIU);
    }

    @DeleteMapping("delete-item/{menuItemId}")
    @SecurityRequirement(name = "bearerAuth")
    public boolean deleteMenuItem(@PathVariable Long menuItemId) {
        return menuItemService.removeMenuItemById(menuItemId);
    }

    @DeleteMapping("delete/{menuId}")
    @SecurityRequirement(name = "bearerAuth")
    public boolean deleteMenu(@PathVariable Long menuId) {
        return menuService.removeMenuById(menuId);
    }

}
