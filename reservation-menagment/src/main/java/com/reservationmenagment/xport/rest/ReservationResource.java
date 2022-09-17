package com.reservationmenagment.xport.rest;

import com.reservationmenagment.domain.model.Reservation;
import com.reservationmenagment.domain.model.ReservationId;
import com.reservationmenagment.domain.model.ReservationItemId;
import com.reservationmenagment.domain.valueobjects.ServiceId;
import com.reservationmenagment.service.ReservationService;
import com.reservationmenagment.service.forms.ReservationForm;
import com.reservationmenagment.service.forms.ReservationItemForm;
import com.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ReservationResource {
    private final ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable ReservationId id) {
        return reservationService.findById(id);
    }

    @PostMapping("/addReservation")
    public ReservationId createReservation(@RequestBody ReservationForm reservationForm) {
        return reservationService.placeReservation(reservationForm);
    }

    @PostMapping("/addReservationItem/{id}")
    public String addReservationItem(@PathVariable ReservationId id, @RequestBody ReservationItemForm reservationItemForm) {
        ReservationItemForm reservationItemForm1 = reservationService.addItem(id, reservationItemForm);
        if (reservationItemForm1 == null) {
            String error = "The maximum number of pets to reserve for a service is 5!";
            return error;
        }
        return reservationItemForm.getService().getServiceName() + " service is added to your reservation";
    }

    @PostMapping("/addReservationItemQuantity/{id}")
    public int addReservationItemQuantity(@PathVariable ServiceId id, @RequestBody int quantity) {
        return this.reservationService.changeQuanity(id, quantity);

    }

    @PostMapping("/deleteReservationItem/{id}/{reservationItemId}")
    public ReservationId removeReservationItem(@PathVariable ReservationId id, @PathVariable ReservationItemId reservationItemId) {
        reservationService.deleteItem(id, reservationItemId);
        return id;
    }

    @DeleteMapping("/deleteReservation/{id}")
    public Reservation deleteReservation(@PathVariable ReservationId id) {
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/getReservationTotalPrice/{id}")
    public Money getTotalPrice(@PathVariable ReservationId id) {
        return reservationService.getTotalPrice(id);
    }

}
