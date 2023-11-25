package main.java.org.acme.repository;

import java.util.List;

import main.java.org.acme.model.Reservation;

public interface ReservationRepository {
    List<Reservation> findAll();
    Reservation save(Reservation reservation);
}
