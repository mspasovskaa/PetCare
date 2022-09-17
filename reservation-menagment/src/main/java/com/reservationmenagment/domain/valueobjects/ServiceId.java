package com.reservationmenagment.domain.valueobjects;

import com.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ServiceId extends DomainObjectId {

    private ServiceId() {
        super(ServiceId.randomId(ServiceId.class).getId());
    }

    public ServiceId(String uuid) {
        super(uuid);
    }


}
