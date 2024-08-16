package com.br.jfoodservices.jfood.model.business;

import com.br.jfoodservices.jfood.exception.GeneralException;
import com.br.jfoodservices.jfood.exception.ResourceNotFoundException;
import com.br.jfoodservices.jfood.model.data.entity.ProductEntity;
import com.br.jfoodservices.jfood.repository.ProductRepository;
import com.google.cloud.firestore.FirestoreException;
import com.br.jfoodservices.jfood.config.JfoodConfig;
import com.google.cloud.spring.data.firestore.FirestoreTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductBusiness {

    private static final Logger logger = LoggerFactory.getLogger(ProductBusiness.class);
    @Autowired
    private JfoodConfig jfoodConfig;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FirestoreTemplate firestoreTemplate;

    public Flux<ProductEntity> allProducts() {
        return productRepository.findAll();
    }

    public Mono<ProductEntity> includeProduct(ProductEntity productEntity) {
        return this.productRepository.save(productEntity)
                .doOnSuccess(p -> logger.info("Product saved with sucess " + p.getProductId()))
                .onErrorResume(FirestoreException.class, e -> Mono.error(new GeneralException("Error Save product: " + e.getMessage())))
                .doOnError(e -> logger.error("Erro ao salvar usuário: {}", e.getMessage(), e));
    }

    public ProductEntity updateProduct(ProductEntity productEntity, UUID id) {

        ProductEntity entityAux = null;
        Optional<ProductEntity> productEntityResult = null;

        if (!productEntityResult.isPresent()) {
            return null;
        }

        entityAux = productEntityResult.get();
        BeanUtils.copyProperties(productEntity, entityAux, "productId");
        return null;
    }

    public void removeProduct(UUID id) {
        //   productRepository.deleteById(id);
    }

    public Mono<ProductEntity> getProductById(String id) {
        return this.productRepository.findById(id)
                .doOnSuccess(p -> logger.info("Product ID " + id + "was found"))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found")));
    }

    public Mono<List<ProductEntity>> getProductsByStoreId(String id) {
        return firestoreTemplate.findAll(ProductEntity.class)
                .filter(p -> p.getStoreId().equals(id))
                .collectList()
                .flatMap(listProducts -> {
                    if (listProducts.isEmpty()) {
                        return Mono.error(new ResourceNotFoundException("There is no products for this store id " + id));
                    }
                    return Mono.just(listProducts);
                });

    }


}
