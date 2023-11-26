package org.acme.service;

import java.util.List;
import java.util.Optional;

import org.acme.database.CarInventory;
import org.acme.model.Car;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import jakarta.inject.Inject;

@GraphQLApi
public class GraphQLInventoryService {
    @Inject
    CarInventory inventory;

    @Query
    public List<Car> cars() {
        return inventory.getCars();
    }

    @Mutation
    public Car register(Car car) {
        car.setId(CarInventory.ids.incrementAndGet());
        inventory.getCars().add(car);
        return car;
    }

    @Mutation
    public boolean remove(String licensePlateNumber) {
        List<Car> cars = inventory.getCars();
        Optional<Car> toBeRemoved = cars.stream()
                .filter(car -> car.getLicensePlateNumber().equals(licensePlateNumber))
                .findAny();
        if (toBeRemoved.isPresent()) {
            return cars.remove(toBeRemoved.get());

        }
        return false;

    }
}
