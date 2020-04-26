package com.example.timesup.view.game;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.round.RoundStartActivity;
import com.example.timesup.viewmodel.game.GameStartViewModel;

public class GameStartActivity extends BaseActivity<GameStartViewModel> {

    private TextView textView;

    @Override
    protected int getLayoutId(){
        return R.layout.activity_game_start;
    }

    @Override
    protected Class getViewModelClass() {
        return GameStartViewModel.class;
    }

    @Override
    protected void prepareView() {
        textView = findViewById(R.id.cardsAmount);
    }

    @Override
    protected void addListenerOnButton() {
        findViewById(R.id.startButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewModel.startGame(Long.parseLong(textView.getText().toString()));
                switchActivity(RoundStartActivity.class);
            }
        });
    }

}
