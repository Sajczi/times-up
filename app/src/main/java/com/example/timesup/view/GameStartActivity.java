package com.example.timesup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.timesup.R;
import com.example.timesup.controller.GameController;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.view.round.RoundStartActivity;

public class GameStartActivity extends BaseActivity {

    Button imageButton;
    GameController gameController;

    private Long cardsAmount = 4L;

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
        imageButton = (Button) findViewById(R.id.startButton);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cardsAmount = Long.valueOf(((TextView) findViewById(R.id.cardsAmount)).getText().toString());
                gameController = new GameController();
                game = gameController.initGame(cardsAmount, getApplicationContext());
                switchActivity(RoundStartActivity.class);
            }

        });
    }

    @Override
    protected void changeGameState(Game game) {
        game.getRound().setRoundNumber(RoundNumber.ROUND_ONE);
    }

}
