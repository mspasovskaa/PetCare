package com.servicecatalog.domain.repository;

import com.servicecatalog.domain.models.PetService;
import com.servicecatalog.domain.models.ServiceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<PetService, ServiceId> {
}
