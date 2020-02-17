package com.example.timesup.view.round;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.model.Round;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.turn.TurnStartActivity;

import org.w3c.dom.Text;

import java.util.List;

public class RoundStartActivity extends BaseActivity {

    private Game game;
    private Button startRoundButton;
    private TextView roundNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_start);
        game = getGameState();
        addListenerOnButton();
        renderScreen(game);
    }

    public void addListenerOnButton() {
        startRoundButton = (Button) findViewById(R.id.startRoundButton);
        startRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startTurn();
            }
        });
    }

    private void renderScreen(Game game) {
        roundNumberText = (TextView) findViewById(R.id.roundNameTextView);
        roundNumberText.setText(game.getRound().getRoundNumber().getText());
    }

    private void startTurn() {
        Intent intent = new Intent(this, TurnStartActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

}
