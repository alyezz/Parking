package com.example.alyezz.parking;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<ArrayList<Marker>> blocks = new ArrayList<ArrayList<Marker>>();
    private ArrayList<ArrayList<LatLng>> positions = new ArrayList<ArrayList<LatLng>>();
    TextView tvSpaces;
    TextView tvTitle;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvSpaces = (TextView) findViewById(R.id.tvSpaces);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng block1 = new LatLng(29.985558, 31.441334);
        LatLng guc = new LatLng(29.985115,31.441827);
        //mMap.addMarker(new MarkerOptions().position(guc).title("Marker in guc"));
        marker =  mMap.addMarker(new MarkerOptions()
               .title("  30")
               .position(block1)
               .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        marker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo((float) 17.6));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        fillMarkers();
        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        tvTitle.setText("Block 1");
                        marker.setVisible(false);
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                .zoom((float) 19.5)                   // Sets the zoom
                                .bearing(-89)                // Sets the orientation of the camera to east
                                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                .build();                   // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        showMarkers(0);
                        return true;
                    }
                }
        );

    }

    public void fillMarkers()
    {
        ArrayList<LatLng> position = new ArrayList<>();
        position.add(new LatLng(29.985633, 31.440923));
        position.add(new LatLng(29.985633, 31.440955));
        position.add(new LatLng(29.985633, 31.440987));
        position.add(new LatLng(29.985633, 31.441019));
        positions.add(position);
        for (int i = 0; i<1;i++)      // 7 parking blocks
        {
            ArrayList<Marker> block = new ArrayList<Marker>();
            for (int j = 0; j<positions.get(i).size();j++)
            {
                block.add(mMap.addMarker(new MarkerOptions()
                        .position(positions.get(i).get(j))
                        .visible(false)
                        .rotation(90)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
            }
            blocks.add(block);
        }
    }

    public void showMarkers(int x)
    {
            for (int j = 0; j<blocks.get(x).size();j++)
            {
               blocks.get(x).get(j).setVisible(true);
            }
    }

    public void hideMarkers(int x)
    {
        for (int j = 0; j<blocks.get(x).size();j++)
        {
            blocks.get(x).get(j).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        tvTitle.setText("Guc Parking");
        hideMarkers(0);
        marker.setVisible(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(29.985115,31.441827))      // Sets the center of the map to Mountain View
                .zoom((float) 17.6)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}