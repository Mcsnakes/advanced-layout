package sp.fr.advancedlayout.model;

/**
 * Created by Formation on 16/01/2018.
 */

public class RandomUser {

    private String username;
    private String name;
    private String toString;
    private double latitude;
    private double longitude;

    public RandomUser() {
    }

    public String getUsername() {
        return username;
    }

    public RandomUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public RandomUser setName(String name) {
        this.name = name;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public RandomUser setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public RandomUser setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String ToString() {
        return this.name;
    }
}
