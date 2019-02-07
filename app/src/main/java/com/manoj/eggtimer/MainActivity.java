package com.manoj.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



       Button button;
    CountDownTimer countDownTimer ;
    TextView textView;
    SeekBar seekBar;
    public void updateTimer(long millisUntilFinished){
        long minute = millisUntilFinished/(60*1000);
        long second = (millisUntilFinished/1000)%60;
      textView = (TextView) findViewById(R.id.textView);
      if(second<10){
        textView.setText(String.valueOf(minute)+":0"+String.valueOf(second));}
        else
          textView.setText(String.valueOf(minute)+":"+String.valueOf(second));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setMax(60 * 10 * 1000);
        seekBar.setProgress(30 * 1000);
        updateTimer(seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void play(View view) {
        button = (Button) findViewById(R.id.button1);

                  if(button.getText().toString().equals("Go!")){
                 seekBar.setEnabled(false);

                      button.setText("STOP");
          countDownTimer=  new CountDownTimer(seekBar.getProgress(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
               updateTimer(millisUntilFinished);

                }

                @Override
                public void onFinish() {

                     MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                     mplayer.start();
                   seekBar.setProgress(30000);

                    button.setText("Go!");
                    seekBar.setEnabled(true);

                }
            }.start();

                  }else { countDownTimer.cancel();
                       seekBar.setProgress(30000);
                      textView.setText("0:30");
                    button.setText("Go!");
                      seekBar.setEnabled(true);
                  }



    }
}
