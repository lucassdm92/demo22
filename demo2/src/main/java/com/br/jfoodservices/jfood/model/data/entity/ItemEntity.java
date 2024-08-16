package com.br.jfoodservices.jfood.model.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter

public class ItemEntity {

    private String itemId;

    private String itemName;

    private String itemDescription;

    private BigDecimal itemValue;

    //   @OneToMany(mappedBy = "itemEntity")
    //  private List<ProductItem> listItems;

}
