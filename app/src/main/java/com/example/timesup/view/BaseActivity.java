package com.example.timesup.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.util.MessageSourceAccessor;

public class BaseActivity extends AppCompatActivity {

    protected Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (fetchGameState()) {
            game = getGameState();
        }
        prepareView(game);
        addListenerOnButton();
    }

    protected boolean fetchGameState() {
        return true;
    }

    protected void addListenerOnButton() {
    }

    protected void prepareView(Game game) {
    }

    protected int getLayoutId() {
        throw new UnsupportedOperationException("Layout ID not defined");
    }

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

    protected <T extends BaseActivity> void switchActivity(Class<T> clazz) {
        Intent intent = new Intent(this, clazz);
        changeGameState(game);
        intent.putExtra("gameState", game);
        startActivity(intent);
    }

    protected void changeGameState(Game game) {
    }

    protected String getLabelText(MessageCode messageCode) {
        return MessageSourceAccessor.getMessage(messageCode);
    }

    private String getLabelText(MessageCode messageCode, Object... params) {
        return MessageSourceAccessor.getMessage(messageCode, params);
    }

    protected void setLabelText(int textViewId, MessageCode messageCode, Object... params) {
        ((TextView) findViewById(textViewId)).setText(getLabelText(messageCode, params));
    }

    protected void setLabelText(int textViewId, String text) {
        ((TextView) findViewById(textViewId)).setText(text);
    }
}
