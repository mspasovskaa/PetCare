package com.servicecatalog.services.form;

import com.sharedkernel.domain.financial.Money;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ServiceForm {


    private String serviceName;
    private String serviceDescription;
    private LocalDate date;
    private LocalTime time;
    private Money price;

    private int reservations = 0;


}
