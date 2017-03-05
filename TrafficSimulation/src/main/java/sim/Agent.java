package sim;

import org.jxmapviewer.viewer.GeoPosition;

/**
 * Created by Wayne on 3/5/2017.
 */
public class Agent {

    private double latitude;
    private double longitude;
    private GeoPosition geoPosition;

    public Agent(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        geoPosition = new GeoPosition(latitude, longitude);
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
}
