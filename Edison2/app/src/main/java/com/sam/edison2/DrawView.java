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
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    final float scale = getResources().getDisplayMetrics().density;

    public DrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(30, 30, 80, 80, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.CYAN);
        canvas.drawRect(33, 60, 77, 77, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, paint);
        Log.d("SIZE", scale + "");
        drawPoly(canvas, Color.CYAN, new Point[]{
                new Point(1 * scale, 1 * scale),
                new Point(300 * scale, 1 * scale),
                new Point(300 * scale, 20 * scale),
                new Point(1 * scale, 20 * scale)
        });
        drawPoly(canvas, Color.RED, new Point[]{
                new Point(300*scale, 1*scale),
                new Point(360*scale, 1*scale),
                new Point(360*scale, 20*scale),
                new Point(300*scale, 20*scale)
        });
        drawPoly(canvas, Color.RED, new Point[]{
                new Point(1*scale, 1*scale),
                new Point(30*scale, 1*scale),
                new Point(30*scale, 570*scale),
                new Point(1*scale, 570*scale)
        });
        canvas.drawRect(30, 30, 80, 80, paint);
        drawPoly(canvas, Color.CYAN, new Point[]{
                new Point(250 * scale, 45 * scale),
                new Point(315 * scale, 55 * scale),
                new Point(300 * scale, 165 * scale),
                new Point(240 * scale, 160 * scale)
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