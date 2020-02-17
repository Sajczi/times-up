package com.example.timesup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.timesup.R;
import com.example.timesup.controller.GameController;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.view.round.RoundStartActivity;

public class GameStartActivity extends AppCompatActivity {

    ImageButton imageButton;
    GameController gameController;
    Game game;

    private Long cardsAmount = 40L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        setDefaultValues();
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        imageButton = (ImageButton) findViewById(R.id.startButton);
        imageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                gameController = new GameController();
                game = gameController.initGame(cardsAmount);
                startRoundOne();
            }

        });
    }

    private void setDefaultValues(){
        TextView textView = (TextView) findViewById(R.id.cardsAmount);
        textView.setText(cardsAmount.toString());
    }

    private void startRoundOne() {
        Intent intent = new Intent(this, RoundStartActivity.class);
        game.getRound().setRoundNumber(RoundNumber.ROUND_ONE);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
