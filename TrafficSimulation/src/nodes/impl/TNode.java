package nodes.impl;

/**
 * Created by gigaw on 2/13/2017.
 */
public class TNode {
    private int id;
    private double lon;
    private double lat;

    public TNode(int id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Latitude: " + lat + " - Longitude: " + lon;
    }
}