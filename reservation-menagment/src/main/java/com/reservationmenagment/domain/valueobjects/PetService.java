package com.reservationmenagment.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharedkernel.domain.base.ValueObject;
import com.sharedkernel.domain.financial.Currency;
import com.sharedkernel.domain.financial.Money;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
public class PetService implements ValueObject {

    private final ServiceId id;
    private final String serviceName;
    private final String serviceDescription;
    private final LocalDate date;
    private final LocalTime time;
    private final Money price;
    private final int reservations;

    private PetService() {
        this.id = ServiceId.randomId(ServiceId.class);
        this.serviceName = "";
        this.serviceDescription = "";
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.price = Money.valueOf(Currency.MKD, 0);
        this.reservations = 0;
    }

    @JsonCreator
    public PetService(@JsonProperty("id") ServiceId id, @JsonProperty("serviceName") String serviceName, @JsonProperty("serviceDescription") String serviceDescription, @JsonProperty("date") LocalDate date, @JsonProperty("time") LocalTime time, @JsonProperty("price") Money price, @JsonProperty("reservations") int reservations) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.date = date;
        this.time = time;
        this.price = price;
        this.reservations = reservations;
    }


}
