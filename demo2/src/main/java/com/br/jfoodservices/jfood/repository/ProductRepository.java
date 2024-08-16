package com.br.jfoodservices.jfood.repository;

import com.br.jfoodservices.jfood.model.data.entity.ProductEntity;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface  ProductRepository extends FirestoreReactiveRepository<ProductEntity> {

}
