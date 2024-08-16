package com.br.jfoodservices.jfood.model.data.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@ToString
@JsonSerialize
@Document(collectionName = "product_collection")
public class ProductEntity {

    @DocumentId
    private String productId;
    private String storeId;
    private String productName;
    private String productDescription;
    private String productQtd;
    private String productSize;
    private String amountProduct;
}
