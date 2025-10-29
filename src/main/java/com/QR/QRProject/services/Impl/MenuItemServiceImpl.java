package com.QR.QRProject.services.Impl;

import com.QR.QRProject.entities.MenuItem;
import com.QR.QRProject.repositories.MenuItemRepository;
import com.QR.QRProject.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;


    @Override
    public boolean removeMenuItemById(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        if (menuItem != null) {
            menuItemRepository.delete(menuItem);
            return true;
        }
        return false;
    }
}
