package com.sam.edison2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements JSONRequest.JSONCallback, View.OnClickListener {

    ImageView imageView;
    DrawView drawView;
    JSONArray jsonArray;
    Intent intent;
    int noZones = 0, cap = 0, count = 0;
    int[] deltaCar;
    boolean request = false;
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                makeRequest();
                Log.d("COUNTER",""+count++); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //drawView = (DrawView)findViewById(R.id.)
        imageView=(ImageView) findViewById(R.id.image);
       // makeRequest();
    /*
        drawView = new DrawView(this);
        drawView.setBackgroundResource(R.drawable.mapa1);
        setContentView(drawView);
        */
        intent = new Intent(this, ZoneActivity.class);

        //drawView.setOnClickListener(this);

        mHandler = new Handler();
        startRepeatingTask();
    }

    public void makeRequest(){

        // start request
        new JSONRequest(this).execute("http://10.43.49.5:3000/db");
        if(request) setMapView();
    }

    public void setMapView(){
        Log.d("noZones", noZones + "");
        Log.d("cap", cap + "");
        drawView = new DrawView(this, noZones, deltaCar, cap );
        drawView.setBackgroundResource(R.drawable.maptest);


        drawView.setOnClickListener(this);
        setContentView(drawView);
    }

    @Override
    public void requestComplete(JSONArray array) {

        try {

            this.jsonArray = array;
            Log.d("REQUEST COMPLETE", "success, apparently.");
            intent.putExtra("noParking", array.length() + "");
            for (int i = 0; i < array.length(); i++) {

                JSONObject current = array.getJSONObject(i);
                Log.d("JSON:", current.getString("name") + "");
                intent.putExtra("name", current.getString("name") + "");
                intent.putExtra("noZones", current.getJSONArray("data").length() + "");
                noZones = current.getJSONArray("data").length();
                deltaCar = new int[noZones];
                /*
                for(int j = 0; j <current.getJSONArray("data").length(); j++) {
                    JSONObject currentItem =  current.getJSONArray("data").getJSONObject(j);
                    deltaCar[i] = currentItem.getInt("carLimit")-currentItem.getInt("currentCars");
                    Log.d("Delta"+i,deltaCar[i]+"");
                    cap = currentItem.getInt("carLimit");
                    intent.putExtra("zoneId", currentItem.getString("id"));
                    intent.putExtra("currentCars", currentItem.getInt("currentCars")+"");
                    intent.putExtra("carLimit", currentItem.getInt("carLimit")+"");
                }
                */

                    JSONObject currentItem =  current.getJSONArray("data").getJSONObject(0);
                    deltaCar[0] = currentItem.getInt("carLimit")-currentItem.getInt("currentCars");
                    Log.d("Delta"+i,deltaCar[0]+"");
                    cap = currentItem.getInt("carLimit");
                    intent.putExtra("zoneId", currentItem.getString("id"));
                    intent.putExtra("currentCars", currentItem.getInt("currentCars")+"");
                    intent.putExtra("carLimit", currentItem.getInt("carLimit")+"");


                setMapView();
                request = true;
               //Log.d("JSON:", current.getInt("carLimit")+"");

            }
        }catch(Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(intent);
    }
}
