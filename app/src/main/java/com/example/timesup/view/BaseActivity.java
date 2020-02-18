package com.example.timesup.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;

public class BaseActivity extends AppCompatActivity {

    protected Game game;

    protected Game getGameState() {
        Bundle bundle = getIntent().getExtras();
        return (Game) bundle.get("gameState");
    }

    protected RoundNumber getRoundNumber(){
        Bundle bundle  = getIntent().getExtras();
        return (RoundNumber) bundle.get("roundNumber");
    }

    protected Runnable addStopwatch(final TextView textView) {
        final Handler handler = new Handler();
        return new Runnable() {
            Long startTime  = 30L;
            public void run() {
                startTime -= 1L;
                textView.setText(startTime.toString());
                handler.postDelayed(this, 10L);
            }
        };
    }
}
