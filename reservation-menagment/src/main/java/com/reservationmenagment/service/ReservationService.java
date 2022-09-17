package com.reservationmenagment.service;

import com.reservationmenagment.domain.exceptions.ReservationIdNotExistsException;
import com.reservationmenagment.domain.exceptions.ReservationItemNotFoundException;
import com.reservationmenagment.domain.model.Reservation;
import com.reservationmenagment.domain.model.ReservationId;
import com.reservationmenagment.domain.model.ReservationItem;
import com.reservationmenagment.domain.model.ReservationItemId;
import com.reservationmenagment.domain.valueobjects.ServiceId;
import com.reservationmenagment.service.forms.ReservationForm;
import com.reservationmenagment.service.forms.ReservationItemForm;
import com.sharedkernel.domain.financial.Money;


import java.util.List;
import java.util.Optional;

public interface ReservationService {

    ReservationId placeReservation(ReservationForm reservationForm);
    List<Reservation> findAll();
    Optional<Reservation> findById(ReservationId reservationId);

    ReservationItemForm addItem(ReservationId reservationId, ReservationItemForm reservationItemForm) throws ReservationIdNotExistsException;

    void deleteItem(ReservationId reservationId, ReservationItemId reservationItemId) throws  ReservationIdNotExistsException, ReservationItemNotFoundException;

    Reservation deleteReservation(ReservationId reservationId);

    Money getTotalPrice(ReservationId reservationId);

    int changeQuanity(ServiceId id,int quantity);

}
