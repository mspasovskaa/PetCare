package com.reservationmenagment.infra;

import com.sharedkernel.domain.events.DomainEvent;
import com.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());

    }
}
