package com.sharedkernel.infra;

import com.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {

    void publish(DomainEvent domainEvent);
}
