package com.reservationmenagment.domain.model;

import com.reservationmenagment.domain.valueobjects.ServiceId;
import com.sharedkernel.domain.base.AbstractEntity;
import com.sharedkernel.domain.base.DomainObjectId;
import com.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reservation_item")
@Getter
public class ReservationItem extends AbstractEntity<ReservationItemId> {

    private Money servicePrice;

    @Column(name = "qty", nullable = false)
    private int numberOfPets;


    @AttributeOverride(name = "id", column = @Column(name = "service_id", nullable = false))
    private ServiceId serviceId;

    @Column(name = "serviceName")
    private String serviceName;

    @Column(name = "serviceDate")
    private LocalDate serviceDate;

    private ReservationItem() {
        super(DomainObjectId.randomId(ReservationItemId.class));
    }

    public ReservationItem(@NonNull ServiceId serviceId, @NonNull String serviceName, @NonNull Money servicePrice, int qty, LocalDate serviceDate) {
        super(DomainObjectId.randomId(ReservationItemId.class));
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.numberOfPets = qty;
        this.serviceDate = serviceDate;
    }


    public void changeNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    public Money subtotal() {
        return servicePrice.multiply(numberOfPets);
    }
}

