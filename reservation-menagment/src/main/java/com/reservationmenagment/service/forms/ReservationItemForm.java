package com.reservationmenagment.service.forms;

import com.reservationmenagment.domain.valueobjects.PetService;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReservationItemForm {

    @NotNull
    private PetService service;

    @Min(1)
    private int quantity = 1;


}
