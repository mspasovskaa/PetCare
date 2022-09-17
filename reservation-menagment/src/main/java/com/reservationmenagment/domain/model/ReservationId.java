package com.reservationmenagment.domain.model;

import com.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;


public class ReservationId extends DomainObjectId {

    private ReservationId() {
        super(ReservationId.randomId(ReservationId.class).getId());
    }

    public ReservationId(@NonNull String uuid) {
        super(uuid);
    }

}
