package com.example.mapsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private FirstMapFragment firstMapFragment;
    private Marker markerPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstMapFragment = FirstMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, firstMapFragment)
                .commit();

        //registrar escucha de eventos
        firstMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng position = new LatLng(-0.217, -78.51);
        markerPais = googleMap.addMarker(new MarkerOptions()
                .position(position)
                .draggable(true)
                .title("Ecuador"));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(position)
                .zoom(10)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(MainActivity.this, MarkerDetailActivity.class);
        intent.putExtra("EXTRA_LATITUD", marker.getPosition().latitude);
        intent.putExtra("EXTRA_LONGITUD", marker.getPosition().longitude);

        startActivity(intent);

        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerPais)) {
            Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerPais)) {
            String newTitle = String.valueOf(marker.getPosition().latitude) + " " + String.valueOf(marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerPais)) {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();
        }
    }
}
