package com.example.timesup.view;

import android.view.View;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.model.Game;

public class GameSummaryActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game_summary;
    }

    @Override
    protected void prepareView(Game game) {
        Long teamAScore = game.getScore().sumTeamAScore();
        Long teamBScore = game.getScore().sumTeamBScore();
        MessageCode winningTeamCode =  teamAScore.equals(teamBScore) ? MessageCode.GAME_RESULT_DRAW
                                            : teamAScore > teamBScore ? MessageCode.GAME_RESULT_TEAM_A_WIN
                                                    : MessageCode.GAME_RESULT_TEAM_B_WIN;
        ((TextView)findViewById(R.id.gameSummaryWinningTeam)).setText(getLabelText(winningTeamCode));
        ((TextView)findViewById(R.id.gameSummaryTeamAScore)).setText(getLabelText(MessageCode.TEAM_A) + ": " + teamAScore.toString());
        ((TextView)findViewById(R.id.gameSummaryTeamBScore)).setText(getLabelText(MessageCode.TEAM_B) + ": " + teamBScore.toString());

    }

    @Override
    protected void addListenerOnButton() {
        findViewById(R.id.gameSummaryOkButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                switchActivity(GameStartActivity.class);
            }
        });
    }

}
