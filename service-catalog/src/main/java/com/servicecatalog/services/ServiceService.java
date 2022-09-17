package com.servicecatalog.services;

import com.servicecatalog.domain.models.PetService;
import com.servicecatalog.domain.models.ServiceId;
import com.servicecatalog.services.form.ServiceForm;

import java.util.List;

public interface ServiceService {

    PetService findById(ServiceId id);

    PetService createService(ServiceForm form);

    PetService reservationItemCreated(ServiceId serviceId, int quantity);

    PetService reservationItemRemoved(ServiceId serviceId, int quantity);

    List<PetService> getAll();

    PetService deleteService(ServiceId id);

    PetService editService(ServiceId id, ServiceForm serviceForm);
}
