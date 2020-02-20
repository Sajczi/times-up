package com.example.timesup.view.turn;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.round.RoundSummaryActivity;

public class TurnSummaryActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_turn_summary;
    }

    @Override
    protected void prepareView(Game game){
        setLabelText(R.id.turnSummaryTeamAScore, MessageCode.TURN_SUMMARY_CORRECT_CARDS, game.getRound().getTurn().getCorrectCards().size());
        setLabelText(R.id.turnSummaryTeamBScore, MessageCode.TURN_SUMMARY_INCORRECT_CARDS, game.getRound().getTurn().getIncorrectCards().size());
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
        game.getRound().getTurn().getCorrectCards().forEach(card -> {
            if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
                if (TurnOfTeam.TEAM_A.equals(game.getCurrentTurnOfTeam())) {
                    game.getScore().incrementTeamARoundOneScore();
                } else {
                    game.getScore().incrementTeamBRoundOneScore();
                }
            } else if (RoundNumber.ROUND_TWO.equals(game.getCurrentRoundNumber())) {
                if (TurnOfTeam.TEAM_A.equals(game.getCurrentTurnOfTeam())) {
                    game.getScore().incrementTeamARoundTwoScore();
                } else {
                    game.getScore().incrementTeamBRoundTwoScore();
                }
            } else if (RoundNumber.ROUND_THREE.equals(game.getCurrentRoundNumber())) {
                if (TurnOfTeam.TEAM_A.equals(game.getCurrentTurnOfTeam())) {
                    game.getScore().incrementTeamARoundThreeScore();
                } else {
                    game.getScore().incrementTeamBRoundThreeScore();
                }
            }});
        if (game.getRound().getAvailableCards().isEmpty()){
            switchActivity(RoundSummaryActivity.class);
        } else {
            game.getRound().getTurn().setTurnOfTeam(TurnOfTeam.next(game.getCurrentTurnOfTeam()));
            game.getRound().getTurn().setAvailableCards(game.getRound().getAvailableCards());
            switchActivity(TurnStartActivity.class);
        }
    }
}
