package core;

import java.io.Serializable;

/**
 * Created by aron on 2/16/17.
 */
public class UserMap implements Serializable {

    private static final long serialVersionUID = 40234L;

    private double latitude;
    private double longitude;
    private double radius;
    private int zoomLevel;
    private String imageName;
    private String dataName;

    public UserMap(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.zoomLevel = generateZoom();
        imageName = generateImageName();
        dataName = generateDataName();
    }

    private String generateImageName() {
        return latitude + "_" + longitude + "_" + zoomLevel + "_" + radius + ".png";
    }

    private String generateDataName() {
        return latitude + "_" + longitude + "_" + radius + ".xml";
    }

    private int generateZoom() {
        return 17;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    @Override
    public String toString() {
        return "Latitude: " + latitude + " - Longitude: " + longitude + " - Radius: " + radius;
    }
}
