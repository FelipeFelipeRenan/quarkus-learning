package org.acme.repository;

import java.util.List;

import org.acme.model.Car;

public interface InventoryClient {
    List<Car> allCars();
}
