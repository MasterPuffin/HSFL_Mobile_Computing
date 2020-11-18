package de.hsf.mclabor2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.startButton);
        final EditText inputTime = (EditText)findViewById(R.id.inputTime);
        final TextView remainingTime = (TextView)findViewById(R.id.remainingTime);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Log.d("startButton pressed","");

                String time = inputTime.getText().toString();
                remainingTime.setText(time.toString() + "s");
                progressBar.setProgress(Integer.parseInt(time));
            }
        });
    }
}
