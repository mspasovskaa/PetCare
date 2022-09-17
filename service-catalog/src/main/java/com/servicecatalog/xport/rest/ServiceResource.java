package com.servicecatalog.xport.rest;

import com.servicecatalog.domain.models.PetService;
import com.servicecatalog.domain.models.ServiceId;
import com.servicecatalog.services.ServiceService;
import com.servicecatalog.services.form.ServiceForm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ServiceResource {
    private final ServiceService serviceService;

    @GetMapping
    public List<PetService> getAll() {
        return serviceService.getAll();
    }

    @GetMapping("{id}")
    public PetService getServiceById(@PathVariable ServiceId id) {
        return serviceService.findById(id);
    }

    @PostMapping("/addService")
    public PetService createService(@RequestBody ServiceForm serviceForm) {
        return serviceService.createService(serviceForm);
    }

    @DeleteMapping("/deleteService/{id}")
    public PetService deleteService(@PathVariable ServiceId id) {
        return serviceService.deleteService(id);
    }

    @PutMapping("/editService/{id}")
    public PetService updateService(@PathVariable ServiceId id, @RequestBody ServiceForm serviceForm) {
        return serviceService.editService(id, serviceForm);
    }

}
