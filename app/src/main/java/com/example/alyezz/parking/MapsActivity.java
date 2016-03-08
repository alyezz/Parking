package com.example.alyezz.parking;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.alyezz.parking.model.Sensor;
import com.example.alyezz.parking.util.ApiRouter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.ui.BubbleIconFactory;
import com.google.maps.android.ui.IconGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private int currentBlock = 0;
    private  ArrayList<ArrayList<Space>> spaces = new ArrayList<>();
    private ArrayList<Marker> blocks = new ArrayList<>();
    private ArrayList<Sensor> sensors = new ArrayList<>();
    TextView tvSpaces;
    TextView tvTitle;
    private int availablespaces = 0;
    private int availablespaces2 = 0;
    private int availablespaces3 = 0;
    private int availablespaces4 = 17;
    private int availablespaces5 = 26;
    private int availablespaces6 = 31;
    private int availablespaces7 = 35;


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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng guc = new LatLng(29.985115,31.441827);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(guc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo((float) 17.6));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getSensors();
            }
        }, 0, 15000);


//        Polyline line = mMap.addPolyline(new PolylineOptions()
//                .add(new LatLng(29.984331, 31.441830), new LatLng(29.985826, 31.441834))
//                .width(5)
//                .color(Color.RED));

        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        /////// change text below block name
                        if (marker.equals(blocks.get(0))) {
                            tvTitle.setText("Block 1");
                            tvSpaces.setText(""+availablespaces + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(-89)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            showSpaces(0);
                            currentBlock = 0;
                            return true;
                        }

                        if (marker.equals(blocks.get(1))) {
                            tvTitle.setText("Block 2");
                            tvSpaces.setText(""+availablespaces2 + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(-89)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            // showSpaces(1);
                            //currentBlock = 1;
                            return true;
                        }

                        if (marker.equals(blocks.get(2))) {
                            tvTitle.setText("Block 3");
                            tvSpaces.setText(""+availablespaces3 + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(-89)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            // showSpaces(2);
                           // currentBlock = 2;
                            return true;
                        }

                        if (marker.equals(blocks.get(3))) {
                            tvTitle.setText("Block 4");
                            tvSpaces.setText(""+availablespaces4 + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(-89)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            //  showSpaces(3);
                           // currentBlock = 3;
                            return true;
                        }

                        if (marker.equals(blocks.get(4))) {
                            tvTitle.setText("Block 5");
                            tvSpaces.setText(""+availablespaces5 + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(-89)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            //  showSpaces(4);
                           // currentBlock = 4;
                            return true;
                        }

                        if (marker.equals(blocks.get(5))) {
                            tvTitle.setText("Block 6");
                            tvSpaces.setText(""+availablespaces6 + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(90)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            //  showSpaces(5);
                           // currentBlock = 5;
                            return true;
                        }

                        if (marker.equals(blocks.get(6))) {
                            tvTitle.setText("Block 7");
                            tvSpaces.setText(""+availablespaces7 + " Available Spaces");
                            hideBlocks();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(marker.getPosition())      // Sets the center of the map to Mountain View
                                    .zoom((float) 19.2)                   // Sets the zoom
                                    .bearing(90)                // Sets the orientation of the camera to east
                                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                                    .build();                   // Creates a CameraPosition from the builder
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            //  showSpaces(6);
                           // currentBlock = 6;
                            return true;
                        }
                        return true;
                    }
                }
        );
    }

    public void fillMarkers()
    {
        mMap.clear();
        blocks.clear();
        //// add change marker color
        IconGenerator redMarker = new IconGenerator(this);
        redMarker.setStyle(IconGenerator.STYLE_RED);
        redMarker.setContentPadding(5, 0, 5, 0);
        IconGenerator greenMarker = new IconGenerator(this);
        greenMarker.setStyle(IconGenerator.STYLE_GREEN);
        greenMarker.setContentPadding(5,0,5,0);
        Bitmap iconBitmap;
        if (availablespaces>0)
        {
            iconBitmap = greenMarker.makeIcon("" + availablespaces);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.985558, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.985558, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        //////////////////////////////////
        if (availablespaces2<0)
        {
            iconBitmap = greenMarker.makeIcon("" + availablespaces2);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.985336, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.985336, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        //////////////////////////////////
        if (availablespaces3>0)
        {
            iconBitmap = greenMarker.makeIcon(""+ availablespaces3);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.985114, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.985114, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        ////////////////////////////////
        if (availablespaces4>0)
        {
            iconBitmap = greenMarker.makeIcon(""+ availablespaces4);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.984892, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.984892, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        /////////////////////////////////
        if (availablespaces5>0)
        {
            iconBitmap = greenMarker.makeIcon(""+availablespaces5);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.98467, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.98467, 31.441334))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        /////////////////////////////////
        if (availablespaces6>0)
        {
            iconBitmap = greenMarker.makeIcon("" + availablespaces6);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.984861, 31.442348))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.984861, 31.442348))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        /////////////////////////////////
        if (availablespaces7>0)
        {
            iconBitmap = greenMarker.makeIcon(""+availablespaces7);
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.984639, 31.442348))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }
        else
        {
            iconBitmap = redMarker.makeIcon("Full");
            blocks.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(29.984639, 31.442348))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap))));
        }

        fillSpaces();
    }

    public void fillSpaces()
    {
        spaces.clear();
        ArrayList<Space> block = new ArrayList<>();

        for (int i = 0; i< 4; i++)
        {
            if (sensors.get(i).getValue() >0)
            {
                block.add(new Space(new LatLng(29.985633, 31.440923 + 0.000032 * i), mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(29.985633, 31.440923 + 0.000032 * i))
                        .visible(false)
                        .rotation(90)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))), true));
            }
            else
            {
                block.add(new Space(new LatLng(29.985633, 31.440923 + 0.000032 * i), mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(29.985633, 31.440923 + 0.000032 * i))
                        .visible(false)
                        .rotation(90)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))), true));
            }

        }

        spaces.add(block);
        tvTitle.setText("Guc Parking");
        int temp = 109+availablespaces;
        tvSpaces.setText(""+temp+" Available Spaces");

    }

    public void showSpaces(int x)
    {
            for (int j = 0; j<spaces.get(x).size();j++)
            {
               spaces.get(x).get(j).getMarker().setVisible(true);
            }
    }

    public void hideSpaces(int x)
    {
        for (int j = 0; j<spaces.get(x).size();j++) {
            spaces.get(x).get(j).getMarker().setVisible(false);
        }
    }

    public void showBlocks()
    {
        for (int j = 0; j<blocks.size();j++)
        {
            blocks.get(j).setVisible(true);
           // blocks.get(j).showInfoWindow();
        }
    }

    public void hideBlocks()
    {
        for (int j = 0; j<blocks.size();j++)
        {
            blocks.get(j).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        tvTitle.setText("Guc Parking");
        int temp = availablespaces +availablespaces2 + availablespaces3 + availablespaces4 + availablespaces5 + availablespaces6+ availablespaces7;
        tvSpaces.setText(""+temp+" Available Spaces");
        //// could be any space not just 0;
        hideSpaces(currentBlock);
        showBlocks();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(29.985115,31.441827))      // Sets the center of the map to Mountain View
                .zoom((float) 17.6)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    protected void getSensors() {
        sensors.clear();
        ApiRouter.withoutToken().getSensors(new Callback<List<Sensor>>() {
            @Override
            public void success(List<Sensor> result, Response response) {
                sensors.addAll(result);
                count();
            }

            @Override
            public void failure(RetrofitError e) {

            }
        });

    }

    public void count()
    {
        availablespaces = 0;
        for (int i = 0; i<sensors.size();i++)
        {
            if (sensors.get(i).getValue() == 0 || sensors.get(i).getValue()>240)
            {
                availablespaces++;
            }
        }
        fillMarkers();
    }
}