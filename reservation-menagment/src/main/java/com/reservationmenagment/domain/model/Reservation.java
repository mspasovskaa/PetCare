package com.reservationmenagment.domain.model;

import com.reservationmenagment.domain.valueobjects.PetService;
import com.sharedkernel.domain.base.AbstractEntity;
import com.sharedkernel.domain.financial.Currency;
import com.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;


import javax.persistence.*;
import java.util.*;

@Entity
@Getter
public class Reservation extends AbstractEntity<ReservationId> {


    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_currency")
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReservationItem> reservationItemList;

    @Column(nullable = true)
    private double total;

    private Reservation() {
        super(ReservationId.randomId(ReservationId.class));
    }

    public Reservation(com.sharedkernel.domain.financial.Currency currency) {
        super(ReservationId.randomId(ReservationId.class));
        this.currency = currency;
        this.total = 0.0;
    }

    public void setReservationItemList(List<ReservationItem> reservationItemList) {
        this.reservationItemList = reservationItemList;
    }

    public Money total() {
        return reservationItemList.stream().map(ReservationItem::subtotal).reduce(new Money(currency, 0), Money::add);

    }

    public void changeTotal(double total) {
        this.total = total;
    }

    public ReservationItem addItem(@NonNull PetService service, int qty) {

        Objects.requireNonNull(service, "service must not be null");
        var item = new ReservationItem(service.getId(), service.getServiceName(), service.getPrice(), qty, service.getDate());

        if (reservationItemList == null) {
            reservationItemList = new ArrayList<>();
        }

        reservationItemList.removeIf(v -> v.getServiceId().getId().equals(item.getServiceId().getId()));

        reservationItemList.add(item);

        return item;
    }

    public void removeItem(@NonNull ReservationItemId reservationItemId) {
        Objects.requireNonNull(reservationItemId, "Reservation Item must not be null");

        reservationItemList.removeIf(v -> v.getId().getId().equals(reservationItemId.getId()));
    }


}
