package com.example.timesup.view.turn;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;

public class TurnSummaryActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_turn_summary;
    }

    @Override
    protected void prepareView(Game game){
        ((TextView)findViewById(R.id.turnSummaryOkCards)).setText("Zgadnięte hasła: " + game.getRound().getTurn().getCorrectCards().size());
        ((TextView)findViewById(R.id.turnSummaryNotOkCards)).setText("Niezgadnięte hasła: " + game.getRound().getTurn().getIncorrectCards().size());
    }

    @Override
    protected void addListenerOnButton() {
        ((Button) findViewById(R.id.turnSummaryOkButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startTurn();
            }
        });
    }

    private void startTurn() {
        game.getRound().getAvailableCards().removeAll(game.getRound().getTurn().getCorrectCards());
        if (RoundNumber.ROUND_ONE.equals(game.getRound().getRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getRound().getTurn().getTurnOfTeam())) {
                game.getScore().incrementTeamARoundOneScore();
            } else {
                game.getScore().incrementTeamBRoundOneScore();
            }
        } else if (RoundNumber.ROUND_TWO.equals(game.getRound().getRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getRound().getTurn().getTurnOfTeam())) {
                game.getScore().incrementTeamARoundTwoScore();
            } else {
                game.getScore().incrementTeamBRoundTwoScore();
            }
        } else if (RoundNumber.ROUND_THREE.equals(game.getRound().getRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getRound().getTurn().getTurnOfTeam())) {
                game.getScore().incrementTeamARoundThreeScore();
            } else {
                game.getScore().incrementTeamBRoundThreeScore();
            }
        }
    }
}
