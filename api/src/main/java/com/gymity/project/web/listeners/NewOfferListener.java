package com.gymity.project.web.listeners;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.gymity.project.events.OnNewOfferEvent;
import com.gymity.project.model.Offer;
import com.gymity.project.service.FirebaseService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewOfferListener {
    private final FirebaseService firebaseService;

    public NewOfferListener(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @EventListener
    public void onNewOffer(OnNewOfferEvent onNewOfferEvent) throws FirebaseMessagingException {
        publish(onNewOfferEvent.getOffer());
    }

    private void publish(Offer offer) throws FirebaseMessagingException {
        firebaseService.sendToTopic(offer.gym.name.replace(' ', '-'),
                "New offer for " + offer.gym.name,
                offer.description + " : " + offer.price + "MKD");
    }
}
