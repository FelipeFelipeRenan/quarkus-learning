package org.acme.controllers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.model.Car;
import org.acme.model.Reservation;
import org.acme.repository.InventoryClient;
import org.acme.repository.ReservationRepository;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {
    private final ReservationRepository reservationRepository;
    private final InventoryClient inventoryClient;

    public ReservationResource(ReservationRepository reservations, InventoryClient inventoryClient){
        this.reservationRepository = reservations;
        this.inventoryClient = inventoryClient;
    }

    @GET
    @Path("availability")
    public Collection<Car> availability(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
        // cria lista com todos os carros
        List<Car> availableCars = inventoryClient.allCars();
        // cria um map entre os carros e o id
        Map<Long, Car> carsById = new HashMap<>();
        for(Car car: availableCars){
            carsById.put(car.getId(), car);
        }
        // pega todas as reservas atuais
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if(reservation.isReserved(startDate, endDate)){
                carsById.remove(reservation.getCarId());
            }
        }
        return carsById.values();
    }
    
}
