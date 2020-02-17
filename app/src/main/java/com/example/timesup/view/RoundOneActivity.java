package com.example.timesup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.controller.GameController;
import com.example.timesup.controller.RoundOneController;
import com.example.timesup.model.Game;

public class RoundOneActivity extends BaseActivity {

    ImageButton correctAnswerRoundOneButton;
    ImageButton incorrectAnswerRoundOneButton;
    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_one);
        addListenerOnButton();

        game = getGameState();
    }

    public void addListenerOnButton() {
        correctAnswerRoundOneButton = (ImageButton) findViewById(R.id.correctAnswerRoundOneButton);
        correctAnswerRoundOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //refreshCard(game.getNextCard());
            }
        });
        incorrectAnswerRoundOneButton = (ImageButton) findViewById(R.id.incorrectAnswerRoundOneButton);
        incorrectAnswerRoundOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //game.returnCard(getCurrentCard());
                //refreshCard(game.getNextCard());
            }
        });
    }

    private void refreshCard(String s){
        TextView textView = (TextView) findViewById(R.id.roundOneCard);
        textView.setText(s);
    }

    private String getCurrentCard() {
        return ((TextView) findViewById(R.id.roundOneCard)).getText().toString();
    }
}
