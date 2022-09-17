package com.servicecatalog.services.impl;

import com.servicecatalog.domain.exceptions.ServiceNotFoundException;
import com.servicecatalog.domain.models.PetService;
import com.servicecatalog.domain.models.ServiceId;
import com.servicecatalog.domain.repository.ServiceRepository;
import com.servicecatalog.services.ServiceService;
import com.servicecatalog.services.form.ServiceForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public PetService findById(ServiceId id) {
        return serviceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
    }

    @Override
    public PetService createService(ServiceForm form) {

        PetService p = PetService.build(form.getServiceName(), form.getServiceDescription(), form.getDate(), form.getTime(), form.getPrice(), form.getReservations());
        serviceRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public PetService reservationItemCreated(ServiceId ServiceId, int quantity) {
        PetService p = this.findById(ServiceId);
        p.addReservations(quantity);
        serviceRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public PetService reservationItemRemoved(ServiceId ServiceId, int quantity) {
        PetService p = this.findById(ServiceId);
        p.removeReservations(quantity);
        serviceRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<PetService> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public PetService deleteService(ServiceId id) {
        PetService p = this.findById(id);
        serviceRepository.delete(p);
        return p;
    }

    @Override
    public PetService editService(ServiceId id, ServiceForm serviceForm) {
        PetService p = this.findById(id);
        p.changeServiceName(serviceForm.getServiceName());
        p.changeServiceDescription(serviceForm.getServiceDescription());
        p.changeServiceDate(serviceForm.getDate());
        p.changeServiceTime(serviceForm.getTime());
        p.changeReservations(serviceForm.getReservations());
        p.changeServicePrice(serviceForm.getPrice());
        serviceRepository.saveAndFlush(p);
        return p;
    }
}
