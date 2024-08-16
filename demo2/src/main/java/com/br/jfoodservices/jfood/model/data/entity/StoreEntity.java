package com.br.jfoodservices.jfood.model.data.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
@Builder
@Document(collectionName = "store_collection")
public class StoreEntity {
    @DocumentId
    private String storeId;
    private String storeName;
    private String storeDescription;
    private String storeLocation;
    private String storeUrladdressImage;
}
