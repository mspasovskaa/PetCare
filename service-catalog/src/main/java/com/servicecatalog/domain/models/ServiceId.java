package com.servicecatalog.domain.models;

import com.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ServiceId extends DomainObjectId {
    private ServiceId() {
        super(ServiceId.randomId(ServiceId.class).getId());
    }

    public ServiceId(@NonNull String uuid) {
        super(uuid);
    }

    public static ServiceId of(String uuid) {
        ServiceId p = new ServiceId(uuid);
        return p;
    }

}
