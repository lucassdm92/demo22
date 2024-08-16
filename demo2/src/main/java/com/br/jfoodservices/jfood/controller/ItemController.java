package com.br.jfoodservices.jfood.controller;

import com.br.jfoodservices.jfood.model.business.ItemBusiness;
import com.br.jfoodservices.jfood.model.data.entity.ItemEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemBusiness itemBusiness;
    @PostMapping(path = "/includeItem")
    public void includeItem(@RequestBody ItemEntity productEntity){
        //      itemBusiness.includeItem(productEntity);
    }

    @PatchMapping(path = "/updateItem/{id}")
    public void updateItem(@RequestBody ItemEntity productEntity, @PathVariable UUID id){
       // itemBusiness.updateItem(productEntity,id);
    }

    @DeleteMapping(path = "/removeItem/{id}")
    public void removeItem(@PathVariable UUID id){
  //      itemBusiness.removeProduct(id);
    }
}
