package com.br.jfoodservices.jfood.model.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter

public class OrderEntity {
    private String orderId;

    private LocalDateTime orderDate;

    private BigDecimal orderAmount;

  //  @ManyToOne
  //  @JoinColumn(name = "STORE_ID")
  //  private StoreEntity storeEntity;

  //  @ManyToOne
  //  @JoinColumn(name ="PRODUCT_ID")
   // private ProductEntity productEntity;

}
