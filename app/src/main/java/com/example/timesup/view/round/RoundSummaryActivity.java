package com.example.timesup.view.round;

import android.view.View;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.GameSummaryActivity;

public class RoundSummaryActivity extends BaseActivity {

    private static final String TEAM_A_SCORE = "Drużyna A: ";
    private static final String TEAM_B_SCORE = "Drużyna B: ";

    @Override
    protected int getLayoutId(){
        return R.layout.activity_round_summary;
    }

    @Override
    protected void addListenerOnButton() {
        findViewById(R.id.turnSummaryOkButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                goToNextScreen();
            }
        });
    }

    @Override
    protected void prepareView(Game game) {
        if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
            ((TextView) findViewById(R.id.turnSummaryTeamAScore)).setText(TEAM_A_SCORE + game.getScore().getTeamARoundOneScore().toString());
            ((TextView) findViewById(R.id.turnSummaryTeamBScore)).setText(TEAM_B_SCORE + game.getScore().getTeamBRoundOneScore().toString());
        } else if (RoundNumber.ROUND_TWO.equals(game.getCurrentRoundNumber())) {
            ((TextView) findViewById(R.id.turnSummaryTeamAScore)).setText(TEAM_A_SCORE + game.getScore().getTeamARoundTwoScore().toString());
            ((TextView) findViewById(R.id.turnSummaryTeamBScore)).setText(TEAM_B_SCORE + game.getScore().getTeamBRoundTwoScore().toString());
        } else {
            ((TextView) findViewById(R.id.turnSummaryTeamAScore)).setText(TEAM_A_SCORE + game.getScore().getTeamARoundThreeScore().toString());
            ((TextView) findViewById(R.id.turnSummaryTeamBScore)).setText(TEAM_B_SCORE + game.getScore().getTeamBRoundThreeScore().toString());
        }
    }

    private void goToNextScreen() {
        if (RoundNumber.ROUND_THREE.equals(game.getCurrentRoundNumber()))  {
            switchActivity(GameSummaryActivity.class);
        } else {
            game.getRound().setRoundNumber(RoundNumber.next(game.getCurrentRoundNumber()));
            game.getRound().setAvailableCards(game.getCards());
            switchActivity(RoundStartActivity.class);
        }

    }
}
