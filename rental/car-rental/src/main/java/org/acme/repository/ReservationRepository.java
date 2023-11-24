package org.acme.repository;

import java.util.List;

import org.acme.model.Reservation;

public interface ReservationRepository {
    List<Reservation> findAll();
    Reservation save(Reservation reservation);
}
