package com.example.labor4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class OnView extends View {
    private Paint paint;
    private Bitmap bmKarte;
    private Rect rView;
    private Rect rMap;

    private double latTop = 54.776627;
    private double lngTop = 9.448113;
    private double latBot = 54.774028;
    private double lngBot = 9.453253;

    public OnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bmKarte = BitmapFactory.decodeResource(getResources(), R.drawable.fhfl_map);
        rMap = new Rect(0, 0, bmKarte.getWidth(), bmKarte.getHeight());
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rView = new Rect(0, 0, this.getWidth(), this.getHeight());
        canvas.drawBitmap(bmKarte, rMap, rView, paint);
        MainActivity activity = (MainActivity) this.getContext();

        @Nullable
        Location location = activity.locationObj;

        if (location != null) {
            Log.d("dbg",location.toString());

            //Calculate pixel coordinates
            double y = location.getLatitude();
            double x = location.getLongitude();

            double fullY = latBot - latTop;
            double fullX = lngBot - lngTop;

            double diffX = x - lngTop;
            double diffY = y - latTop;

            double pixelX = diffX / fullX * 480;
            double pixelY = diffY / fullY * 420;

            canvas.drawCircle((float)pixelX, (float)pixelY, 15f, paint);
        }
    }
}
