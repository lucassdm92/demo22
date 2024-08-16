package com.br.jfoodservices.jfood.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class JfoodConfig {


    private Resource resource;


    public FirebaseApp createConnectionFirebase() {


        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(this.getResource("application_default_credentials.json").getInputStream()))
                    .setDatabaseUrl("https://jfood-21caf-default-rtdb.firebaseio.com").build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public Firestore getDefaultInstance() {
        return FirestoreOptions.getDefaultInstance().getService();
    }


    public StorageOptions getStorageOptions() {

        try {
            return StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(this.getResource("application_default_credentials.json").getInputStream())).build();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Resource getResource(final String fileName) {
        return resource = new ClassPathResource(fileName);
    }
}
