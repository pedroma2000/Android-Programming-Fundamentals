package pt.ipp.estg.tourpediaexample.Models;

import java.io.Serializable;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class POI implements Serializable {
    private int id;
    private String name;
    private String address;
    private String category;
    private String location;
    private float lat;
    private float lng;
    private String details;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getDetails() {
        return details;
    }

    public String getLocation() {
        return location;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
