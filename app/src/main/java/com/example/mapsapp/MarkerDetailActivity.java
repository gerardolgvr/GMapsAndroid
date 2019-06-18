package com.example.mapsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MarkerDetailActivity extends AppCompatActivity {
    private TextView edit_latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_detail);

        edit_latlng = (TextView) findViewById(R.id.tv_latlng);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getting the data
        Intent intent = getIntent();
        String latlng = String.valueOf(intent.getDoubleExtra("EXTRA_LATITUD", 0)) + " " + String.valueOf(intent.getDoubleExtra("EXTRA_LONGITUD", 0));

        //setting the data
        edit_latlng.setText(latlng);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
