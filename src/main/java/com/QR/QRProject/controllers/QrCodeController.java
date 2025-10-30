package com.QR.QRProject.controllers;

import com.QR.QRProject.services.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("Qr/")
public class QrCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping(value = "generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String url) throws IOException, WriterException {
        byte[] qrImage = qrCodeService.generateQRCodeBytes(url, 300, 300);
        return ResponseEntity.ok(qrImage);
    }
}
