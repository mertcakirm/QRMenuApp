package com.QR.QRProject.services;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeService {
    byte[] generateQRCodeBytes(String url, int width, int height) throws WriterException, IOException;
}
