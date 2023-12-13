package org.teste.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Fruit extends PanacheEntity{
    public String name;
    public String season;

    public static List<Fruit> findBySeason(String season){
        return find("season", season).list();
    }
}
