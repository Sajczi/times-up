package com.example.timesup.view.turn;

import android.view.View;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.viewmodel.turn.TurnStartViewModel;

public class TurnStartActivity extends BaseActivity<TurnStartViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_turn_start;
    }

    @Override
    protected Class getViewModelClass() {
        return TurnStartViewModel.class;
    }

    @Override
    protected void prepareView(){
        ((TextView)findViewById(R.id.turnStartNowPlaying)).setText(getLabelText(MessageCode.TURN_START_NOW_PLAYING));
        ((TextView)findViewById(R.id.turnStartTeamPlaying)).setText(getLabelText(viewModel.getTeamPlaying()));
    }

    @Override
    protected void addListenerOnButton() {
        findViewById(R.id.turnStartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewModel.changeGameState();
                switchActivity(TurnActivity.class);
            }
        });
    }
}
