package ParseData;

/**
 * Created by gigaw on 2/22/2017.
 */
public class LongLat {
    private double longitude;
    private double latitude;

    public LongLat(double lat, double lon){
        longitude = lon;
        latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
