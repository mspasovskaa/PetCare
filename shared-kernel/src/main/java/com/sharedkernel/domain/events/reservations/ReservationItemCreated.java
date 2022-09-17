package com.sharedkernel.domain.events.reservations;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharedkernel.domain.config.TopicHolder;
import com.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class ReservationItemCreated extends DomainEvent {

    private String serviceId;
    private int quantity;

    public ReservationItemCreated(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_ITEM_CREATED);
    }

    @JsonCreator
    public ReservationItemCreated(@JsonProperty("serviceId") String serviceId, @JsonProperty("serviceName") String serviceName, @JsonProperty("quantity") int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_ITEM_CREATED);
        this.serviceId = serviceId;
        this.quantity = quantity;
    }
}
