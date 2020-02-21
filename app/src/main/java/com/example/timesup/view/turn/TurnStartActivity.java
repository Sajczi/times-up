package com.example.timesup.view.turn;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;

import java.util.ArrayList;

public class TurnStartActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_turn_start;
    }

    @Override
    protected void prepareView(Game game){
        ((TextView)findViewById(R.id.turnStartNowPlaying)).setText(getLabelText(MessageCode.TURN_START_NOW_PLAYING));
        ((TextView)findViewById(R.id.turnStartTeamPlaying)).setText(getLabelText(game.getCurrentTurnOfTeam().getCode()));

    }

    @Override
    protected void addListenerOnButton() {
        ((Button) findViewById(R.id.turnStartButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                switchActivity(TurnActivity.class);
            }
        });
    }

    @Override
    protected void changeGameState() {
        game.getRound().getTurn().setAvailableCards(game.getRound().getAvailableCards());
        game.getRound().getTurn().setCorrectCards(new ArrayList());
        game.getRound().getTurn().setIncorrectCards(new ArrayList());
    }
}
