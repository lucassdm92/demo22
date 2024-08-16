package com.br.jfoodservices.jfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT}, origins = "*")
@RequestMapping(path = "/imageServices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageServicesController {

    @Autowired
    private ImageStoreServices imageStoreServices;

    @PostMapping(path = "/includeImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> includeImage(@RequestPart("file") MultipartFile mp, @RequestPart("fileName") String imageName) throws IOException {
        return ResponseEntity.ok(imageStoreServices.uploadImage(mp, imageName));
    }
}
