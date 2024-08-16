package com.br.jfoodservices.jfood.controller;

import com.br.jfoodservices.jfood.exception.ResourceNotFoundException;
import com.br.jfoodservices.jfood.model.data.response.ApiResponse;
import com.br.jfoodservices.jfood.model.business.ProductBusiness;
import com.br.jfoodservices.jfood.model.data.entity.ProductEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductBusiness productBusiness;

    @Operation(summary = "Create a product")
    @PostMapping(path = "/createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse<ProductEntity>>> includeProduct(@RequestBody ProductEntity productEntity) {
        return productBusiness.includeProduct(productEntity)
                .map(p -> new ApiResponse<>(HttpStatus.CREATED, "Saved With Success", p))
                .map(response -> ResponseEntity.ok(response));
    }

    @GetMapping(path = "/productById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse<ProductEntity>>> allProducts(@PathVariable String id) {
        return productBusiness.getProductById(id)
                .map(p -> new ApiResponse<>(HttpStatus.OK, "Product was found With Success", p))
                .map(response -> ResponseEntity.ok(response))
                .onErrorResume(ResourceNotFoundException.class, e -> {
                    ApiResponse<ProductEntity> errorResponse = new ApiResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), null);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));
                });


    }

    @GetMapping(path = "/productsByStoreId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse<List<ProductEntity>>>> productsByStoreId(@PathVariable String id) {
        return productBusiness.getProductsByStoreId(id)
                .map(p -> new ApiResponse<>(HttpStatus.OK, "Product found with sucess", p))
                .map(response -> ResponseEntity.ok(response))
                .onErrorResume(ResourceNotFoundException.class, e -> {
                    ApiResponse<List<ProductEntity>> errorResponse = new ApiResponse<>(HttpStatus.NOT_FOUND, e.getMessage(), null);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));
                });
    }

    @PatchMapping(path = "/updateProduct/{id}")
    public void updateProduct(@RequestBody ProductEntity productEntity, @PathVariable UUID id) {
        productBusiness.updateProduct(productEntity, id);
    }


    @GetMapping(path = "/allProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ProductEntity> allProducts() {
        return productBusiness.allProducts();
    }

    @DeleteMapping(path = "/removeProduct/{id}")
    public void removeProduct(@PathVariable UUID id) {
        productBusiness.removeProduct(id);
    }
}
