package com.example.timesup.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.view.round.RoundStartActivity;

public class GameStartActivity extends BaseActivity {

    private Long cardsAmount = 40L;

    @Override
    protected int getLayoutId(){
        return R.layout.activity_game_start;
    }

    @Override
    protected boolean fetchGameState() {
        return false;
    }

    @Override
    protected void prepareView(Game game) {
        TextView textView = (TextView) findViewById(R.id.cardsAmount);
        textView.setText(cardsAmount.toString());
    }

    @Override
    protected void addListenerOnButton() {
        ((Button) findViewById(R.id.startButton)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cardsAmount = Long.valueOf(((TextView) findViewById(R.id.cardsAmount)).getText().toString());
                game =  new Game(cardsAmount, getApplicationContext());
                switchActivity(RoundStartActivity.class);
            }

        });
    }

    @Override
    protected void changeGameState() {
        game.getRound().setRoundNumber(RoundNumber.ROUND_ONE);
    }

}
