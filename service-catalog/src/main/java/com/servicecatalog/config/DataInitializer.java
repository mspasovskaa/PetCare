package com.servicecatalog.config;

import com.servicecatalog.domain.models.PetService;
import com.servicecatalog.domain.repository.ServiceRepository;
import com.sharedkernel.domain.financial.Currency;
import com.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ServiceRepository serviceRepository;

    @PostConstruct
    public void initData() {
        PetService p1 = PetService.build("Bath","Bathing for dogs", LocalDate.now(), LocalTime.now(),Money.valueOf(Currency.MKD,500),10);
        PetService p2 = PetService.build("Walk","Walking for dogs", LocalDate.now(), LocalTime.now(),Money.valueOf(Currency.MKD,1000),5);
        if (serviceRepository.findAll().isEmpty()) {
            serviceRepository.saveAll(Arrays.asList(p1,p2));
        }
    }

}
