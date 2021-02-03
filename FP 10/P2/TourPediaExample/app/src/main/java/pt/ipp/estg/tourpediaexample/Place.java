package pt.ipp.estg.tourpediaexample;

import java.io.Serializable;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Place implements Serializable {

    private int id;
    private String name;
    private String address;
    private String category;
    private String location;
    private float lat;
    private float lng;
    private String details;

    public Place(int id, String name, String address, String category, String location, float lat, float lng, String details) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
