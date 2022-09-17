package com.servicecatalog.xport.events;

import com.servicecatalog.domain.models.ServiceId;
import com.servicecatalog.services.ServiceService;
import com.sharedkernel.domain.config.TopicHolder;
import com.sharedkernel.domain.events.DomainEvent;
import com.sharedkernel.domain.events.reservations.ReservationItemCreated;
import com.sharedkernel.domain.events.reservations.ReservationItemRemoved;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceEventListener {

    private final ServiceService serviceService;

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_ITEM_CREATED, groupId = "serviceCatalog")
    public void consumeReservationItemCreatedEvent(String jsonMessage) {
        try {
            ReservationItemCreated event = DomainEvent.fromJson(jsonMessage, ReservationItemCreated.class);
            System.out.println(event.getQuantity());
            serviceService.reservationItemCreated(ServiceId.of(event.getServiceId()), event.getQuantity());
        } catch (Exception e) {
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_ITEM_REMOVED, groupId = "ServiceCatalog")
    public void consumeReservationItemRemovedEvent(String jsonMessage) {
        try {
            ReservationItemRemoved event = DomainEvent.fromJson(jsonMessage, ReservationItemRemoved.class);
            serviceService.reservationItemRemoved(ServiceId.of(event.getServiceId()), event.getQuantity());
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}
