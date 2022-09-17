package com.reservationmenagment.service.impl;

import com.reservationmenagment.domain.exceptions.ReservationIdNotExistsException;
import com.reservationmenagment.domain.exceptions.ReservationItemNotFoundException;
import com.reservationmenagment.domain.model.Reservation;
import com.reservationmenagment.domain.model.ReservationId;
import com.reservationmenagment.domain.model.ReservationItem;
import com.reservationmenagment.domain.model.ReservationItemId;
import com.reservationmenagment.domain.repository.ReservationItemRepository;
import com.reservationmenagment.domain.repository.ReservationRepository;
import com.reservationmenagment.domain.valueobjects.ServiceId;
import com.reservationmenagment.service.ReservationService;
import com.reservationmenagment.service.forms.ReservationForm;
import com.reservationmenagment.service.forms.ReservationItemForm;
import com.sharedkernel.domain.events.reservations.ReservationItemCreated;
import com.sharedkernel.domain.financial.Money;
import com.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationItemRepository reservationItemRepository;

    private final Validator validator;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public ReservationId placeReservation(ReservationForm reservationForm) {
        Objects.requireNonNull(reservationForm, "reservation must not be null");//ako e null frli exception so ovaa poraka
        var constraintViolations = validator.validate(reservationForm); //da ja proveri formata
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The reservation form is not valid", constraintViolations);
        }

        var newReservation = reservationRepository.saveAndFlush(toDomainObject(reservationForm));
        newReservation.getReservationItemList().forEach(item -> domainEventPublisher.publish(new ReservationItemCreated(item.getServiceId().getId(), item.getServiceName(), item.getNumberOfPets())));
        return newReservation.getId();
    }


    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(ReservationId ReservationId) {
        return this.reservationRepository.findById(ReservationId);
    }

    @Override
    public ReservationItemForm addItem(ReservationId ReservationId, ReservationItemForm reservationItemForm) throws ReservationIdNotExistsException {

        Reservation reservation = reservationRepository.findById(ReservationId).orElseThrow(ReservationIdNotExistsException::new);

        if (reservationItemForm.getQuantity() <= 5) {
            reservation.addItem(reservationItemForm.getService(), reservationItemForm.getQuantity());
            reservation.changeTotal(reservation.total().getAmount());
            reservationRepository.saveAndFlush(reservation);
            domainEventPublisher.publish(new ReservationItemCreated(reservationItemForm.getService().getId().getId(), reservationItemForm.getService().getServiceName(), reservationItemForm.getQuantity()));
            return reservationItemForm;
        }
        return null;
    }


    @Override
    public void deleteItem(ReservationId ReservationId, ReservationItemId reservationItemId) throws ReservationIdNotExistsException, ReservationItemNotFoundException {
        Reservation reservation = reservationRepository.findById(ReservationId).orElseThrow(ReservationIdNotExistsException::new);
        Optional<ReservationItem> reservationItem = reservation.getReservationItemList().stream().filter(v -> v.getId().getId().equals(reservationItemId.getId())).findFirst();

        reservation.removeItem(reservationItem.get().getId());
        reservation.changeTotal(reservation.total().getAmount());
        reservationRepository.saveAndFlush(reservation);
        domainEventPublisher.publish(new ReservationItemCreated(reservationItem.get().getServiceId().getId(), reservationItem.get().getServiceName(), reservationItem.get().getNumberOfPets()));


    }

    @Override
    public Reservation deleteReservation(ReservationId reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationIdNotExistsException::new);
        this.reservationRepository.delete(reservation);

        return reservation;
    }

    @Override
    public Money getTotalPrice(ReservationId reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationIdNotExistsException::new);

        return reservation.total();
    }

    @Override
    public int changeQuanity(ServiceId id, int quantity) {
        ReservationItem reservationItem = this.reservationItemRepository.findAll().stream().filter(r -> r.getServiceId().equals(id)).findFirst().get();

        if (ChronoUnit.DAYS.between(LocalDate.now(), reservationItem.getServiceDate()) > 7) {
            reservationItem.changeNumberOfPets(quantity);
        }
        return quantity;
    }


    private Reservation toDomainObject(ReservationForm reservationForm) {
        var reservation = new Reservation(reservationForm.getCurrency());
        reservationForm.getItems().forEach(item -> reservation.addItem(item.getService(), item.getQuantity()));
        reservationRepository.saveAndFlush(reservation);
        return reservation;
    }
}
