package com.gymity.project.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    public FirebaseConfig() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("/home/sangy/MPIP-project/api/src/main/resources/gymity-735ac-firebase-adminsdk-q9p0v-4ca92f1170.json");
//        new FileInputStream("/src/main/resources/gymity-735ac-firebase-adminsdk-q9p0v-4ca92f1170.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://gymity-735ac.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
