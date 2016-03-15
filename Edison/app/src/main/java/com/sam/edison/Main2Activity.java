package com.sam.edison;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView name, password;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        name = (TextView)findViewById(R.id.textView);
        password = (TextView)findViewById(R.id.textView2);
        Toast.makeText(getApplicationContext(),intent.getStringExtra("name"),Toast.LENGTH_SHORT).show();
        name.setText(intent.getStringExtra("name"));
        password.setText(intent.getStringExtra("password"));
        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
    }
}
