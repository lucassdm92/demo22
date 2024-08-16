package com.br.jfoodservices.jfood.model.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductItem {

    private String productItemId;

    private LocalDateTime createAt;


  //  @ManyToOne
   // @JoinColumn(name = "PRODUCT_ID")
   // private ProductEntity productEntity;


    //@ManyToOne
    //@JoinColumn(name = "ITEM_ID")
    //private ItemEntity itemEntity;
}
