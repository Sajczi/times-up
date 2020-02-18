package com.example.timesup.view.turn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.controller.GameController;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;

import java.util.ArrayList;

public class TurnActivity extends BaseActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);
        game = getGameState();
        addListenerOnButton();
        refreshCard();
    }

    private void refreshCard(){
        ((TextView)findViewById(R.id.turnTeam)).setText(game.getRound().drawCard());
    }

    public void addListenerOnButton() {
        ((Button) findViewById(R.id.turnOkButton)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        game.getRound().getTurn().getCorrectCards().add(getCard());
                        if(!finishTurnIfNoMoreCards()) {
                            refreshCard();
                        }
                    }
                });
        ((Button) findViewById(R.id.turnNotOkButton)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        game.getRound().getTurn().getIncorrectCards().add(getCard());
                        if(!finishTurnIfNoMoreCards()) {
                            refreshCard();
                        }
                    }
                });
    }

    private boolean finishTurnIfNoMoreCards() {
        if (game.getRound().endOfCards()) {
            goToTurnSummary();
            return true;
        }
        return false;
    }

    private String getCard() {
        return ((TextView)findViewById(R.id.turnTeam)).getText().toString();
    }

    private void goToTurnSummary() {
        Intent intent = new Intent(this, TurnSummaryActivity.class);
        intent.putExtra("gameState", game);
        startActivity(intent);
    }

}
