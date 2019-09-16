package com.gymity.project.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.gymity.project.service.FirebaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceImpl implements FirebaseService {
    private final Logger logger = LoggerFactory.getLogger(FirebaseServiceImpl.class);

    @Override
    public void sendToTopic(String topic, String notificationTitle, String notificationBody) throws FirebaseMessagingException {
        Notification notification = new Notification(topic, notificationTitle, notificationBody);

        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(topic)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        logger.info("Successfully sent message: {}", response);
    }
}
