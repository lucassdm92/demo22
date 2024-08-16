package com.br.jfoodservices.jfood.model.business;

import com.br.jfoodservices.jfood.config.JfoodConfig;
import com.br.jfoodservices.jfood.exception.GeneralException;
import com.br.jfoodservices.jfood.model.data.entity.StoreEntity;
import com.br.jfoodservices.jfood.repository.StoreRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.Blob;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StoreServices {

    @Autowired
    private JfoodConfig jfoodConfig;

    @Autowired
    private StoreRepository storeRepository;

    public ResponseEntity<Object> includeImage(MultipartFile mp) {


        StoreEntity storeEntity = new StoreEntity();
        try {
            DocumentReference documentReference = jfoodConfig.getDefaultInstance().collection("store_collection").document();
            Blob blob = jfoodConfig.getStorageOptions().getService().get("jfood-4c3be.appspot.com")
                    .create(documentReference.getId(), mp.getInputStream());
            storeEntity.setStoreUrladdressImage(blob.getMediaLink());
            storeEntity.setStoreId(documentReference.getId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeEntity);
    }


    public Mono<StoreEntity> createStore(StoreEntity storeEntity) throws IOException {
        return this.storeRepository.save(storeEntity)
                .onErrorResume(FirestoreException.class, e -> Mono.error(new GeneralException("Error save store " + e.getMessage())));
    }


    public ResponseEntity<Object> getStores() {

        Gson g = new Gson();
        List<JsonObject> jsonList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> querySnapShot = jfoodConfig.getDefaultInstance().collection("store_collection").get();
            QuerySnapshot qs = querySnapShot.get();
            for (QueryDocumentSnapshot e : querySnapShot.get().getDocuments()) {
                JsonElement jsonElement = g.toJsonTree(e.getData());
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                jsonList.add(jsonObject);
            }


            //this.jfoodConfig.getDefaultInstance().collection("jfood-4c3be.appspot.com").listDocuments();


            //.collection("jfood-4c3be.appspot.com")
            //.document("Google-flutter-logo.pngQscH3bN5l3I9ZSWyd0Lr").get();

            this.jfoodConfig.getStorageOptions().getService();

            // future.get().exists();
            //          .document("Google-flutter-logo.pngQscH3bN5l3I9ZSWyd0Lr").get();
            //        DocumentSnapshot document = future.get();
            //      if(document.exists()){
            //      byte[] bt =    document.getBlob("name").toBytes();
            //        Base64.getEncoder().encode(bt).toString();
            //   }
            return ResponseEntity.status(HttpStatus.OK).body(jsonList.toString());


        } catch (InterruptedException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ExecutionException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
}

