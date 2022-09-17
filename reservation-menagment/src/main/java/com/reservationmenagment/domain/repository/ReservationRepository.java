package com.reservationmenagment.domain.repository;

import com.reservationmenagment.domain.model.Reservation;
import com.reservationmenagment.domain.model.ReservationId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
}
