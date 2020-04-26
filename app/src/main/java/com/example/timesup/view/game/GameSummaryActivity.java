package com.example.timesup.view.game;

import android.view.View;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.viewmodel.game.GameSummaryViewModel;

public class GameSummaryActivity extends BaseActivity<GameSummaryViewModel> {

    private TextView winningTeamTextView;
    private TextView teamAScoreTextView;
    private TextView teamBScoreTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game_summary;
    }

    @Override
    protected Class getViewModelClass() {
        return GameSummaryViewModel.class;
    }

    @Override
    protected void prepareView() {
        winningTeamTextView = findViewById(R.id.gameSummaryWinningTeam);
        teamAScoreTextView = findViewById(R.id.gameSummaryTeamAScore);
        teamBScoreTextView = findViewById(R.id.gameSummaryTeamBScore);
        setLabelTexts();
    }

    private void setLabelTexts() {
        winningTeamTextView.setText(getLabelText(viewModel.getWinningTeamMessage()));
        teamAScoreTextView.setText(getLabelText(MessageCode.TEAM_A) + ": " + viewModel.getTeamAScore());
        teamBScoreTextView.setText(getLabelText(MessageCode.TEAM_B) + ": " + viewModel.getTeamBScore());
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
