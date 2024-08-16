package com.br.jfoodservices.jfood.controller;

import com.br.jfoodservices.jfood.model.business.ProductItemBusiness;
import com.br.jfoodservices.jfood.model.data.entity.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductItemController {

    @Autowired
    private ProductItemBusiness productItemBusiness;

    @PostMapping(path = "/includeProductItem")
    public void includeProductItem(@RequestBody ProductItem productEntity){
     //   productItemBusiness.includeProductItem(productEntity);
    }

    @PatchMapping(path = "/updateProductItem/{id}")
    public void updateProductItem(@RequestBody ProductItem productEntity, @PathVariable UUID id){
       // productItemBusiness.updateProductItem(productEntity,id);
    }

    @DeleteMapping(path = "/removeProductItem/{id}")
    public void removeProductItem(@PathVariable UUID id){
        //productItemBusiness.removeProduct(id);
    }

}
