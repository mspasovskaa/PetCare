package com.servicecatalog.domain.valueobjects;

import com.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Quantity implements ValueObject {
    private final int numberOfPets;

    protected Quantity (){
        this.numberOfPets=0;
    }
}
