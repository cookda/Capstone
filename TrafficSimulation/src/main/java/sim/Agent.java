package sim;

import javafx.util.Pair;
import nodes.impl.TNode;
import org.jxmapviewer.viewer.GeoPosition;
import vehicle.VehicleType;

import java.lang.Math;
/**
 * Created by Wayne on 3/5/2017.
 */
public class Agent {

    private double latitude;
    private double longitude;
    private double distance;
    private final static double R = 6372.8;
    private GeoPosition geoPosition;
    private Pair<GeoPosition, GeoPosition> trip;
    private VehicleType vehicleType;

    public Agent(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        geoPosition = new GeoPosition(latitude, longitude);
        vehicleType = VehicleType.CAR;
    }

    public Agent(GeoPosition start, GeoPosition end) {
        this(start.getLatitude(), start.getLongitude());
        trip = new Pair<>(start, end);
    }

    public Agent(TNode start, TNode end) {
        this(start.getGeoPosition(), end.getGeoPosition());
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public Pair<GeoPosition, GeoPosition> getTrip() {
        return trip;
    }

    public void setTrip(Pair<GeoPosition, GeoPosition> trip) {
        this.trip = trip;
    }

    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "GeoPosition: " + geoPosition + " - Trip start: " + trip.getKey() + " - Trip end: " + trip.getValue();
    }

    public static double haverSine(double latStart, double lonStart, double latEnd, double lonEnd){
        double disLon = Math.toRadians(lonEnd - lonStart);
        double disLat = Math.toRadians(latEnd - latStart);
        latStart = Math.toRadians(latStart);
        latEnd = Math.toRadians(latEnd);
        double a = Math.pow(Math.sin(disLat/2),2) + Math.pow(Math.sin(disLon / 2),2) * Math.cos(latStart) * Math.cos(latEnd);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
