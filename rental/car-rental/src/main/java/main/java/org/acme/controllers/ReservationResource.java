package main.java.org.acme.controllers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import main.java.org.acme.model.Car;
import main.java.org.acme.model.Reservation;
import main.java.org.acme.repository.InventoryClient;
import main.java.org.acme.repository.ReservationRepository;
import main.java.org.acme.services.RentalClient;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {
    private final ReservationRepository reservationRepository;
    private final InventoryClient inventoryClient;
    private final RentalClient rentalClient;

    public ReservationResource(ReservationRepository reservations, InventoryClient inventoryClient, @RestClient RentalClient rentalClient){
        this.reservationRepository = reservations;
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Reservation make(Reservation reservation){
        Reservation result = reservationRepository.save(reservation);
        String userId = "x";
        if(reservation.getStartDate().equals(LocalDate.now())){
            rentalClient.start(userId, result.getId());
        }
        return result;
    
    }
}
