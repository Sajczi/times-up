package com.example.timesup.view.round;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.GameSummaryActivity;

public class RoundSummaryActivity extends BaseActivity {

    @Override
    protected int getLayoutId(){
        return R.layout.activity_round_summary;
    }

    @Override
    protected void addListenerOnButton() {
        ((Button)findViewById(R.id.roundSummaryOkButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                goToNextScreen();
            }
        });
    }

    @Override
    protected void prepareView(Game game) {
        if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
            setLabelText(R.id.roundSummaryRoundNumber, MessageCode.ROUND_ONE_START);
            setLabelText(R.id.roundSummaryTeamAScore, MessageCode.TEAM_A_SCORE, game.getScore().getTeamARoundOneScore());
            setLabelText(R.id.roundSummaryTeamBScore, MessageCode.TEAM_B_SCORE, game.getScore().getTeamBRoundOneScore());
        } else if (RoundNumber.ROUND_TWO.equals(game.getCurrentRoundNumber())) {
            setLabelText(R.id.roundSummaryRoundNumber, MessageCode.ROUND_TWO_START);
            setLabelText(R.id.roundSummaryTeamAScore, MessageCode.TEAM_A_SCORE, game.getScore().getTeamARoundTwoScore());
            setLabelText(R.id.roundSummaryTeamBScore, MessageCode.TEAM_B_SCORE, game.getScore().getTeamBRoundTwoScore());
        } else {
            setLabelText(R.id.roundSummaryRoundNumber, MessageCode.ROUND_THREE_START);
            setLabelText(R.id.roundSummaryTeamAScore, MessageCode.TEAM_A_SCORE, game.getScore().getTeamARoundThreeScore());
            setLabelText(R.id.roundSummaryTeamBScore, MessageCode.TEAM_B_SCORE, game.getScore().getTeamBRoundThreeScore());
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
