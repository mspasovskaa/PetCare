package com.servicecatalog.domain.models;

import com.sharedkernel.domain.base.AbstractEntity;
import com.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
public class PetService extends AbstractEntity<ServiceId> {
    private String serviceName;
    private String serviceDescription;
    private LocalDate date;
    private LocalTime time;

    private int reservations = 0;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    public PetService() {
        super(ServiceId.randomId(ServiceId.class));
    }


    public static PetService build(String serviceName, String serviceDescription, LocalDate date, LocalTime time, Money price, int reservations) {
        PetService s = new PetService();
        s.price = price;
        s.serviceName = serviceName;
        s.serviceDescription = serviceDescription;
        s.date = date;
        s.time = time;
        s.reservations = reservations;
        return s;
    }


    public void addReservations(int qty) {
        this.reservations = this.reservations + qty;
    }

    public void removeReservations(int qty) {
        this.reservations = this.reservations - qty;
    }

    public void changeReservations(int qty) {
        this.reservations = qty;
    }

    public void changeServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void changeServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public void changeServiceDate(LocalDate date) {
        this.date = date;
    }

    public void changeServiceTime(LocalTime time) {
        this.time = time;
    }

    public void changeServicePrice(Money price) {
        this.price = price;
    }


}
