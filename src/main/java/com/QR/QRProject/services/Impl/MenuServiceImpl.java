package com.QR.QRProject.services.Impl;

import com.QR.QRProject.repositories.MenuRepository;
import com.QR.QRProject.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;



}
