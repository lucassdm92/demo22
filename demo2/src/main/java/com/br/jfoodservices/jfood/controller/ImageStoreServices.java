package com.br.jfoodservices.jfood.controller;

import com.br.jfoodservices.jfood.config.JfoodConfig;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ImageStoreServices implements ImageInterface {
    /**
     *
     */

    @Autowired
    private JfoodConfig jfoodConfig;

    private String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/jfood-4c3be.appspot.com/o/%s?alt=media";

    @Override
    public ResponseEntity<Object> uploadImage(MultipartFile mp, String imageID) {

        File file = this.convertToFile(mp, imageID);

        try {
            BlobInfo blobInfo = BlobInfo.newBuilder("jfood-4c3be.appspot.com", file.getName())
                    .setContentType(mp.getContentType()).setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                    .build();

            Blob blob = jfoodConfig.getStorageOptions().getService().create(blobInfo, Files.readAllBytes(file.toPath()));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok(String.format(DOWNLOAD_URL, URLEncoder.encode(imageID, StandardCharsets.UTF_8)));
    }
}
