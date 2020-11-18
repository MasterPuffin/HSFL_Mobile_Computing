package de.hsf.mclabor2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean timerStarted = false;
    boolean timerDone = false;
    CountDownTimer myTimer;
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.startButton);
        final EditText inputTime = (EditText)findViewById(R.id.inputTime);
        final TextView remainingTime = (TextView)findViewById(R.id.remainingTime);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Check if timer is already started
                if (!timerStarted) {
                    //Timer is not started, start it now

                    // Code here executes on main thread after user presses button
                    Log.d("startButton pressed", "");

                    startButton.setText("Stop");
                    timerStarted = true;

                    String time = inputTime.getText().toString();
                    int timeInt = Integer.parseInt(time);
                    //remainingTime.setText(time.toString() + "s");
                    progressBar.setProgress(timeInt);
                    progressBar.setMax(timeInt);

                    // einfaches Timerobjekt z.B. in OnCreate() instanzieren
                    myTimer = new CountDownTimer(timeInt * 1000, 100) {
                        public void onTick(long millisUntilFinished) {
                            // Logging: man beachte: verbose wg. Wiederholung
                            Log.d("dbg", "CountDownTimer.onTick(): sUntilFinished: " + millisUntilFinished);

                            long timeRemaining = millisUntilFinished / 1000;
                            int timeRemainingInt = (int) timeRemaining;

                            remainingTime.setText(timeRemainingInt + 1 + "s");
                            progressBar.setProgress(timeRemainingInt + 1);
                        }

                        public void onFinish() {
                            Log.d("dbg", "CountDownTimer.onFinish()");

                            remainingTime.setText("0s");
                            progressBar.setProgress(0);

                            startButton.setText("Ausschalten");
                            timerDone = true;

                            //Play ringtone
                            ringtone.play();
                        }
                    };

                    myTimer.start();
                } else if (timerStarted && !timerDone) {
                    //Timer is started but not done
                    Log.d("dbg", "timer has been stopped");
                    myTimer.cancel();
                    timerStarted = false;
                    startButton.setText("Start");

                } else if (timerDone) {
                    //Timer is done, reset it
                    Log.d("dbg", "timer is done");
                    ringtone.stop();
                    //Reset sound
                    startButton.setText("Start");
                    timerStarted = false;
                    timerDone = false;
                }
            }
        });
    }
}
