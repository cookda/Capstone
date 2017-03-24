package nodes.impl;

import org.jxmapviewer.viewer.GeoPosition;

/**
 * Created by gigaw on 2/13/2017.
 */
public class TNode {
    private long id;
    private double lon;
    private double lat;
    private GeoPosition geoPosition;

    public TNode(long id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
        geoPosition = new GeoPosition(lat, lon);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Latitude: " + lat + " - Longitude: " + lon;
    }
}