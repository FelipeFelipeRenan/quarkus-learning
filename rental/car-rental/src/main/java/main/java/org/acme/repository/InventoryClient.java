package main.java.org.acme.repository;

import java.util.List;

import main.java.org.acme.model.Car;

public interface InventoryClient {
    List<Car> allCars();
}
