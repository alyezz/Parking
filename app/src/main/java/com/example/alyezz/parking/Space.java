package com.example.alyezz.parking;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Aly Ezz on 07/03/2016.
 */
public class Space {

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    private LatLng position;
    private Marker marker;
    private boolean isFree;

    public Space (LatLng position, Marker marker, boolean isFree)
    {
        this.position = position;
        this.marker = marker;
        this.isFree = isFree;
    }
}
