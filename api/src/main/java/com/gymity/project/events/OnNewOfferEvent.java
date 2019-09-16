package com.gymity.project.events;

import com.gymity.project.model.Offer;
import org.springframework.context.ApplicationEvent;

public class OnNewOfferEvent extends ApplicationEvent {
    private Offer offer;

    public OnNewOfferEvent(Offer offer) {
        super(offer);
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
