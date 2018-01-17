package sp.fr.advancedlayout.model;

/**
 * Created by Formation on 16/01/2018.
 */

public class User {

    private String username = "anonymous";

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
}
