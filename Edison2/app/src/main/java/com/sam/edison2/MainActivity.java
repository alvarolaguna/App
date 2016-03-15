package com.sam.edison2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements JSONRequest.JSONCallback {

    ImageView imageView;
    DrawView drawView;
    JSONArray jsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView=(ImageView) findViewById(R.id.image);
        drawView = new DrawView(this);
        drawView.setBackgroundResource(R.drawable.mapa1);
        makeRequest();
        setContentView(drawView);

    }

    public void makeRequest(){

        // start request
        new JSONRequest(this).execute("http://10.43.49.5:3000/db");
    }

    @Override
    public void requestComplete(JSONArray array) {

        try {
            this.jsonArray = array;
            Log.d("REQUEST COMPLETE", "success, apparently.");
            for (int i = 0; i < array.length(); i++) {

                JSONObject current = array.getJSONObject(i);
                Log.d("JSON:", current.getString("id") + "");
                for(int j = 0; j <current.getJSONArray("data").length(); j++) {
                    JSONObject currentItem =  current.getJSONArray("data").getJSONObject(j);
                    Log.d("JSON:", currentItem.getString("id"));
                    Log.d("JSON:", currentItem.getInt("currentCars")+"" );
                    Log.d("JSON:", currentItem.getInt("carLimit")+"" );
                }
                
              //  Log.d("JSON:", current.getInt("carLimit")+"");

            }
        }catch(Exception e){

            e.printStackTrace();
        }
    }

}
