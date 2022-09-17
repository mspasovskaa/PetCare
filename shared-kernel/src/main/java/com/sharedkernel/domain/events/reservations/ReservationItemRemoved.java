package com.sharedkernel.domain.events.reservations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharedkernel.domain.config.TopicHolder;
import com.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;


@Getter
public class ReservationItemRemoved extends DomainEvent {

    private String serviceId;
    private int quantity;

    public ReservationItemRemoved(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_ITEM_REMOVED);
    }

    @JsonCreator
    public ReservationItemRemoved(@JsonProperty("serviceId") String serviceId, @JsonProperty("quantity") int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_ITEM_REMOVED);
        this.serviceId = serviceId;
        this.quantity = quantity;
    }
}
