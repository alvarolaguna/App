package com.sam.edison2;

/**
 * Created by Sam on 3/15/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    final float scale = getResources().getDisplayMetrics().density;
    int noZones, cap;
    int[] deltaCar;
    int available = 6,
        full = 0;

    public DrawView(Context context, int noZones, int[] delta, int cap) {
        super(context);
        this.noZones = noZones;
        this.cap = cap;
        this.deltaCar = new int[delta.length] ;
        for(int i=0; i < delta.length; i++){
            this.deltaCar[i] = delta[i];
        }

    }

    public DrawView(Context context){
        super(context);
    }


    public int evalColor(int deltaCar){
        Log.d("DELTA",deltaCar+"");
        if(deltaCar > this.cap){
            Log.d("COLOR","BLUE");
            return Color.BLUE;
        }
        else if(deltaCar >= available && deltaCar < this.cap){
            Log.d("COLOR","GREEN");

            return Color.GREEN;
        }
        else if(deltaCar > full && deltaCar < available){
            Log.d("COLOR","YELLOW");
            return Color.YELLOW;
        }
        else if(deltaCar == full) return Color.RED;
        else return Color.BLUE;
    }


    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //paint.setColor(evalColor(this.deltaCar[0]));
       //Log.d("SIZE", scale + "");
        drawPoly(canvas, evalColor(this.deltaCar[0]), new Point[]{
                new Point(30 * scale, 69 * scale),
                new Point(327 * scale, 69   * scale),
                new Point(327 * scale, 232 * scale),
                new Point(30 * scale, 232 * scale)
        });


        drawPoly(canvas,evalColor(this.deltaCar[1]), new Point[]{
                new Point(30 * scale, 284 * scale), //
                new Point(327 * scale, 284  * scale), //
                new Point(327 * scale, 446 * scale), // Derecha
                new Point(30 * scale, 446 * scale) // Izquierda
        });

    }

    private void drawPoly(Canvas canvas, int color, Point[] points) {
        // line at minimum...
        if (points.length < 2) {
            return;
        }

        // paint
        Paint polyPaint = new Paint();
        polyPaint.setColor(color);
        polyPaint.setAlpha(100);
        polyPaint.setStyle(Paint.Style.FILL);

        // path
        Path polyPath = new Path();
        polyPath.moveTo(points[0].x, points[0].y);
        int i, len;
        len = points.length;
        for (i = 0; i < len; i++) {
            polyPath.lineTo(points[i].x, points[i].y);
        }
        polyPath.lineTo(points[0].x, points[0].y);

        // draw
        canvas.drawPath(polyPath, polyPaint);
    }

}