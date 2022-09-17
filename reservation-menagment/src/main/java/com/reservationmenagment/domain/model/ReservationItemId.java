package com.reservationmenagment.domain.model;

import com.sharedkernel.domain.base.DomainObjectId;

public class ReservationItemId extends DomainObjectId {

    private ReservationItemId() {
        super(ReservationItemId.randomId(ReservationItemId.class).getId());
    }

    public ReservationItemId(String uuid) {
        super(uuid);
    }

}
