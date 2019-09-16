package com.gymity.project.service;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface FirebaseService {
    void sendToTopic(String topic, String notificationTitle, String notificationBody) throws FirebaseMessagingException;
}
