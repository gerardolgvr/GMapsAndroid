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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

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

        LatLng mx = new LatLng(19.406063, -99.4238213);
        markerPais = googleMap.addMarker(new MarkerOptions()
                .position(mx)
                .title("México"));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(mx)
                .zoom(10)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(MainActivity.this, MarkerDetailActivity.class);
        intent.putExtra("EXTRA_LATITUD", marker.getPosition().latitude);
        intent.putExtra("EXTRA_LONGITUD", marker.getPosition().longitude);

        startActivity(intent);

        return true;
    }
}
