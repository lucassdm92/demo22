package com.br.jfoodservices.jfood.controller;

import com.br.jfoodservices.jfood.model.data.entity.ItemEntity;
import com.br.jfoodservices.jfood.model.business.StoreServices;
import com.br.jfoodservices.jfood.model.data.entity.ProductEntity;
import com.br.jfoodservices.jfood.model.data.entity.StoreEntity;
import com.br.jfoodservices.jfood.model.data.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.*;
import java.util.UUID;

@RestController
@SpringBootConfiguration

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT}, origins = "*")
@RequestMapping(path = "/store", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreController {

    @Autowired
    private StoreServices storeServices;

    @Autowired
    private ImageStoreServices imageStoreServices;

    @GetMapping(path = "/getStores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStores() throws IOException {


        return storeServices.getStores();
    }

    @PostMapping(path = "/createStore", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ApiResponse<StoreEntity>>> createStore(@RequestBody StoreEntity productEntity) throws IOException {
        return storeServices.createStore(productEntity)
                .map(s -> new ApiResponse<>(HttpStatus.CREATED, "Saved with success", s))
                .map(response -> ResponseEntity.ok(response));
    }

    @PatchMapping(path = "/updateStore/{id}")
    public void updateStore(@RequestBody ItemEntity productEntity, @PathVariable UUID id) {
        //itemBusiness.updateItem(productEntity,id);
    }

    @DeleteMapping(path = "/removeStore/{id}")
    public void removeStore(@PathVariable UUID id) {
        //itemBusiness.removeProduct(id);
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }
}
