package com.example.timesup.view.turn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Game;
import com.example.timesup.model.Turn;
import com.example.timesup.view.BaseActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TurnStartActivity extends BaseActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_start);
        game = getGameState();
        prepareView(game);
        addListenerOnButton();
    }

    private void prepareView(Game game){
        ((TextView)findViewById(R.id.turnTeam)).setText(game.getRound().getTurn().getTurnOfTeam().getText());
    }

    public void addListenerOnButton() {
        ((Button) findViewById(R.id.turnStartButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startTurn();
            }
        });
    }

    private void startTurn() {
        Intent intent = new Intent(this, TurnActivity.class);
        game.getRound().getTurn().setCorrectCards(new ArrayList());
        game.getRound().getTurn().setIncorrectCards(new ArrayList());
        intent.putExtra("gameState", game);
        startActivity(intent);
    }
}
