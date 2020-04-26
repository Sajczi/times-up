package com.example.timesup.view.round;

import android.view.View;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.viewmodel.round.RoundSummaryViewModel;

public class RoundSummaryActivity extends BaseActivity<RoundSummaryViewModel> {

    @Override
    protected int getLayoutId(){
        return R.layout.activity_round_summary;
    }

    @Override
    protected Class getViewModelClass() {
        return RoundSummaryViewModel.class;
    }

    @Override
    protected void addListenerOnButton() {
        findViewById(R.id.roundSummaryOkButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Class nextActivityClass = viewModel.getNextScreen();
                viewModel.summarizeRound();
                switchActivity(nextActivityClass);
            }
        });
    }

    @Override
    protected void prepareView() {
        setLabelText(R.id.roundSummaryRoundNumber, viewModel.getRoundNumberMessageCode());
        setLabelText(R.id.roundSummaryTeamAScore, MessageCode.TEAM_A_SCORE, viewModel.getTeamAScore());
        setLabelText(R.id.roundSummaryTeamBScore, MessageCode.TEAM_B_SCORE, viewModel.getTeamBScore());
    }
}
