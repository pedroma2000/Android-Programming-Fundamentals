package pt.ipp.estg.fragments.entities;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private String country;
    private String description;

    public City(String name, String country, String description){
        this.name = name;
        this.country = country;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }
}
