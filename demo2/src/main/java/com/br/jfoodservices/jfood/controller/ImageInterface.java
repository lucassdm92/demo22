package com.br.jfoodservices.jfood.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public interface ImageInterface {

    ResponseEntity<Object> uploadImage(MultipartFile multipartFile, String imageID) throws IOException;

    default File convertToFile(MultipartFile multipartFile, String fileName) {

        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }
}
