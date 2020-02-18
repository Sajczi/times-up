package com.example.timesup.view.turn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_summary);
        game = getGameState();
        prepareView(game);
        addListenerOnButton();
    }

    private void prepareView(Game game){
        ((TextView)findViewById(R.id.turnSummaryOkCards)).setText("Zgadnięte hasła: " + game.getRound().getTurn().getCorrectCards().size());
        ((TextView)findViewById(R.id.turnSummaryNotOkCards)).setText("Niezgadnięte hasła: " + game.getRound().getTurn().getIncorrectCards().size());
    }

    public void addListenerOnButton() {
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
                game.getScore().setTeamARoundOneScore(game.getScore().getTeamARoundOneScore() + 1);
            } else {
                game.getScore().setTeamBRoundOneScore(game.getScore().getTeamBRoundOneScore() + 1);
            }
        } else if (RoundNumber.ROUND_TWO.equals(game.getRound().getRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getRound().getTurn().getTurnOfTeam())) {
                game.getScore().setTeamARoundTwoScore(game.getScore().getTeamARoundTwoScore() + 1);
            } else {
                game.getScore().setTeamBRoundTwoScore(game.getScore().getTeamBRoundTwoScore() + 1);
            }
        } else if (RoundNumber.ROUND_THREE.equals(game.getRound().getRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getRound().getTurn().getTurnOfTeam())) {
                game.getScore().setTeamARoundThreeScore(game.getScore().getTeamARoundThreeScore() + 1);
            } else {
                game.getScore().setTeamBRoundThreeScore(game.getScore().getTeamBRoundThreeScore() + 1);
            }
        }
    }
}
