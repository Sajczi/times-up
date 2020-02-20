package com.example.timesup.view.round;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Game;
import com.example.timesup.model.Turn;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.turn.TurnStartActivity;

public class RoundStartActivity extends BaseActivity {

    private Button startRoundButton;
    private TextView roundNumberText;
    private TextView roundStartDescription;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_round_start;
    }

    @Override
    protected void prepareView(Game game){
        startRoundButton = findViewById(R.id.turnSummaryOkButton);
        roundNumberText = findViewById(R.id.roundNameTextView);
        roundStartDescription =findViewById(R.id.roundStartDescription);
        if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
            roundNumberText.setText(getLabelText(MessageCode.ROUND_ONE_START));
            roundStartDescription.setText(getLabelText(MessageCode.ROUND_ONE_START_DESCRIPTION));
        } else if (RoundNumber.ROUND_TWO.equals(game.getCurrentRoundNumber())) {
            roundNumberText.setText(getLabelText(MessageCode.ROUND_TWO_START));
            roundStartDescription.setText(getLabelText(MessageCode.ROUND_TWO_START_DESCRIPTION));
        }else {
            roundNumberText.setText(getLabelText(MessageCode.ROUND_THREE_START));
            roundStartDescription.setText(getLabelText(MessageCode.ROUND_THREE_START_DESCRIPTION));
        }
    }

    @Override
    protected void addListenerOnButton() {
        startRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                switchActivity(TurnStartActivity.class);
            }
        });
    }

    @Override
    protected void changeGameState(Game game) {
        Turn turn = new Turn();
        turn.setAvailableCards(game.getRound().getAvailableCards());
        if  (turn.getTurnOfTeam() == null) {
            turn.setTurnOfTeam(TurnOfTeam.TEAM_A);
        }
        game.getRound().setTurn(turn);
    }

}
