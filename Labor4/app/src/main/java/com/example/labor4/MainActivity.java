package com.example.labor4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String str;
    Location locationObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView view_debug = (TextView)findViewById(R.id.view_debug);
        final OnView view_map = findViewById(R.id.view_map);
        final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION=1;

        //Set up location manager
        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                locationObj = location;
                // Called when a new location is found by the network location provider.
                double[] pixel = {0,0};
                //OnView.onDraw(location);
                view_map.invalidate();
                str = "onLocationChanged(Location: " + location.toString();
                
                Log.d("dbg", str);
                view_debug.setText(pixel[1] +" ;" + pixel[0] +" ;" + str);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("dbg", "onStatusChanged(provider: " + provider + " status: " + status + " extras: " + extras.toString());
                Log.d("dbg", str);
                view_debug.setText(str);
            }

            public void onProviderEnabled(String provider) {
                str = "onProviderEnabled(provider: " + provider;
                Log.d("dbg", str);
                view_debug.setText(str);
            }

            public void onProviderDisabled(String provider) {
                str = "onProviderDisabled(provider: " + provider;
                Log.d("dbg", str);
                view_debug.setText(str);
            }
        };

        Button button_start = findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //GPS aktivieren
                Log.d("dbg", "Active GPS");
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Show rationale and request permission.
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_PERMISSION_ACCESS_FINE_LOCATION);

                    Log.d("dbg", "no permission");
                }

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Register the listener with the Location Manager to receive location updates
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, locationListener); // via GPS
                }
            }
        });

        Button button_stop = findViewById(R.id.button_stop);
        button_stop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //GPS deaktivieren
                Log.d("dbg", "Deactive GPS");
                locationManager.removeUpdates(locationListener);
            }
        });

        Button button_clear = findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Clear output
                Log.d("dbg", "Clear output");

            }
        });
    }
}
