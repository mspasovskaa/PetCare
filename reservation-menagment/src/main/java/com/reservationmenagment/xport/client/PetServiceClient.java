package com.reservationmenagment.xport.client;

import com.reservationmenagment.domain.model.Reservation;
import com.reservationmenagment.domain.model.ReservationId;
import com.reservationmenagment.domain.model.ReservationItemId;
import com.reservationmenagment.domain.valueobjects.PetService;
import com.sharedkernel.domain.financial.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class PetServiceClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public PetServiceClient(@Value("${app.service-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<PetService> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/service").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<PetService>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public PetService findById() {
        try {
            return restTemplate.exchange(uri().path("/api/service/{id}").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<PetService>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public PetService addService() {
        try {
            return restTemplate.exchange(uri().path("/api/service/addService").build().toUri(), HttpMethod.POST, null, new ParameterizedTypeReference<PetService>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public PetService deleteService() {
        try {
            return restTemplate.exchange(uri().path("/api/service/deleteService/{id}").build().toUri(), HttpMethod.DELETE, null, new ParameterizedTypeReference<PetService>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public PetService editService() {
        try {
            return restTemplate.exchange(uri().path("/api/service/editService/{id}").build().toUri(), HttpMethod.PUT, null, new ParameterizedTypeReference<PetService>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Reservation> getAllReservations() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Reservation>>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public Reservation getReservationById() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/{id}").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<Reservation>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public ReservationId createReservation() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/addReservation}").build().toUri(), HttpMethod.POST, null, new ParameterizedTypeReference<ReservationId>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public ReservationId addReservationItem() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/addReservationItem/{id}").build().toUri(), HttpMethod.POST, null, new ParameterizedTypeReference<ReservationId>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public ReservationItemId removeReservationItem() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/deleteReservationItem/{id}").build().toUri(), HttpMethod.DELETE, null, new ParameterizedTypeReference<ReservationItemId>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public Reservation removeReservation() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/deleteReservation/{id}").build().toUri(), HttpMethod.DELETE, null, new ParameterizedTypeReference<Reservation>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public Money getTotalPriceForReservation() {
        try {
            return restTemplate.exchange(uri().path("/api/reservation/getReservationTotalPrice/{id}").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<Money>() {
            }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
