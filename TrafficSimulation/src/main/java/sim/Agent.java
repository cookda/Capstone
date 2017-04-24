package sim;

import javafx.util.Pair;
import nodes.impl.TNode;
import org.jxmapviewer.viewer.GeoPosition;
import vehicle.VehicleType;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    private List<TNode> path;
    private int currPos = 0;
    private int speed;

    public Agent(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        geoPosition = new GeoPosition(latitude, longitude);
        vehicleType = VehicleType.CAR;
        path = new ArrayList<>();
        speed = ThreadLocalRandom.current().nextInt(20, 50);
    }

    public Agent(GeoPosition start, GeoPosition end) {
        this(start.getLatitude(), start.getLongitude());
        trip = new Pair<>(start, end);
    }

    public void reset() {
        setGeoPosition(trip.getKey());
        currPos = 0;
    }

    public void advancePosition() {
        GeoPosition startPos = path.get(currPos++).getGeoPosition();
        GeoPosition endPos = path.get(currPos).getGeoPosition();
        double dist = haverSine(startPos, endPos);

        dist *= (60/speed);

        setGeoPosition(nextPosition(startPos, endPos, dist));

    }

    private GeoPosition nextPosition(GeoPosition start, GeoPosition end, double distance) {
        return add(multiply(subtract(end, start), distance), start);
    }

    private GeoPosition multiply(GeoPosition a, double b) {
        return new GeoPosition(a.getLatitude() * b, a.getLongitude() * b);
    }

    private GeoPosition subtract(GeoPosition a, GeoPosition b) {
        return new GeoPosition(a.getLatitude() - b.getLatitude(), a.getLongitude() - b.getLongitude());
    }

    private GeoPosition add(GeoPosition a, GeoPosition b) {
        return new GeoPosition(a.getLatitude() + b.getLatitude(), a.getLongitude() + b.getLongitude());
    }


    public int incrementPosition() {
        return currPos++;
    }

    public boolean isDone() {
        return path.size() > 0 && path.get(currPos).getGeoPosition().equals(trip.getValue());
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

    private double haverSine(GeoPosition one, GeoPosition two) {
        return haverSine(one.getLatitude(), one.getLongitude(), two.getLatitude(), two.getLongitude());
    }

    public void setPath(List<TNode> path) {
        this.path = path;
    }

    public List<TNode> getPath() {
        return path;
    }
}
