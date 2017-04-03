package nodes.impl;

import org.jxmapviewer.viewer.GeoPosition;
import sim.SearchAlgs.Edge;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Dalton on 2/13/2017.
 */
public class TNode implements Serializable {

    private static final long serialVersionUID = 40234L;

    private long id;
    private double lon;
    private double lat;

    public HashSet<Edge> getEdges() {
        return edges;
    }

    private GeoPosition geoPosition;
    private HashSet<Edge> edges;

    public TNode(long id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
        geoPosition = new GeoPosition(lat, lon);
        edges = new HashSet<>();
    }

    public void addEdge(Edge e){
        edges.add(e);
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