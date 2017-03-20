package sim;

import org.antlr.v4.runtime.misc.Pair;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Created by Wayne on 3/5/2017.
 */
public class Agent {

    private double latitude;
    private double longitude;
    private GeoPosition geoPosition;
    private Pair<GeoPosition, GeoPosition> trip;

    public Agent(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        geoPosition = new GeoPosition(latitude, longitude);
    }

    public Agent(double latitude, double longitude, GeoPosition start, GeoPosition end) {
        this(latitude, longitude);
        trip = new Pair<>(start, end);

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

}
