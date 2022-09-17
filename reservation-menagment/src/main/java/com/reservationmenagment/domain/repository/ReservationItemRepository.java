package com.reservationmenagment.domain.repository;


import com.reservationmenagment.domain.model.ReservationItem;
import com.reservationmenagment.domain.model.ReservationItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, ReservationItemId> {
    void deleteAll();
}
