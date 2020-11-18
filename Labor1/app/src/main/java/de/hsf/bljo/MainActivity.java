package de.hsf.bljo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView tv = new TextView(this); //TextView Objekt erstellen
        //tv.setText("Hello Android");                //Text für die neue Ausgabe
        //setContentView(tv);                         //TextObjekt als UI Inhalt setzen
    }
}
