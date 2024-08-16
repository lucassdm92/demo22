package com.br.jfoodservices.jfood.repository;

import com.br.jfoodservices.jfood.model.data.entity.StoreEntity;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StoreRepository  extends FirestoreReactiveRepository<StoreEntity> {
}
