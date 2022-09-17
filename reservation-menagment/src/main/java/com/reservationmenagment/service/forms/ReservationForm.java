package com.reservationmenagment.service.forms;

import com.sharedkernel.domain.financial.Currency;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReservationForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<ReservationItemForm> items = new ArrayList<>();
}
