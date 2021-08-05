package sg.edu.rp.c346.id20032316.oursingapore;

import java.io.Serializable;

public class Island implements Serializable {

    private int _id;
    private  String name;
    private String description;
    private int squareKm;
    private float stars;

    public Island(int _id, String name, String description, int squareKm, float stars) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.squareKm = squareKm;
        this.stars = stars;
    }

    public Island(String name, String description, int squareKm, float stars) {
        this.name = name;
        this.description = description;
        this.squareKm = squareKm;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSquareKm() {
        return squareKm;
    }

    public void setSquareKm(int squareKm) {
        this.squareKm = squareKm;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
