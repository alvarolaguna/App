package com.sam.edison2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ZoneActivity extends AppCompatActivity {

    TextView parkName, noZones, zoneId, currentCars, carLimit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);
        Intent intent = getIntent();
        parkName = (TextView)findViewById(R.id.textView);
        noZones = (TextView)findViewById(R.id.textView2);
        zoneId = (TextView)findViewById(R.id.textView3);
        currentCars = (TextView)findViewById(R.id.textView4);
        carLimit = (TextView)findViewById(R.id.textView5);
        parkName.setText(intent.getStringExtra("name"));
        noZones.setText(intent.getStringExtra("noZones"));
        zoneId.setText(intent.getStringExtra("zoneId"));
        currentCars.setText(intent.getStringExtra("currentCars"));
        carLimit.setText(intent.getStringExtra("carLimit"));
    }
}
