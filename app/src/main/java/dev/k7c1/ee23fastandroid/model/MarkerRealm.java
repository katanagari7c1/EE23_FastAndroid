package dev.k7c1.ee23fastandroid.model;

import dev.k7c1.ee23fastandroid.Marker;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MarkerRealm extends RealmObject {

    @PrimaryKey
    private String name;
    private double x,y;

    public MarkerRealm() {
        super();
    }

    public MarkerRealm(Marker marker) {
        this.name = marker.name;
        this.x = marker.x;
        this.y = marker.y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
