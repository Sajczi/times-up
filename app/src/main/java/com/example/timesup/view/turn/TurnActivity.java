package com.example.timesup.view.turn;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.viewmodel.turn.TurnViewModel;

public class TurnActivity extends BaseActivity<TurnViewModel> {

    @Override
    protected int getLayoutId() {
       return R.layout.activity_turn;
    }

    @Override
    protected Class getViewModelClass() {
        return TurnViewModel.class;
    }

    @Override
    protected void prepareView() {
        initObservables();
    }

    private void initObservables() {
        viewModel.getTimeObservable().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer timeLeft) {
                setLabelText(R.id.turnStopwatch, new StringBuilder().append(timeLeft.toString()).append("'").toString());
            }
        });

        viewModel.getCardsLeftObservable().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                setLabelText(R.id.turnCardsLeft, MessageCode.TURN_CARDS_LEFT, count);
            }
        });

        viewModel.getCardObservable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String card) {
                setLabelText(R.id.turnCard, card);
            }
        });

        viewModel.getTimesUpObservable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldFinishTurn) {
                if (shouldFinishTurn) {
                    switchActivity(TurnSummaryActivity.class);
                }
            }
        });
    }

    @Override
    protected void addListenerOnButton() {
        (findViewById(R.id.turnOkButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                renderButton(event.getAction(), v.getId());
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    viewModel.handleCorrectAnswer(getCard());
                }
                return true;
            }
        });
        (findViewById(R.id.turnWrongButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                renderButton(event.getAction(), v.getId());
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    viewModel.handleIncorrectAnswer(getCard());
                }
                return true;
            }
        });
    }

    private void renderButton(int action, int viewId) {
        if (viewId == R.id.turnOkButton) {
            if (action == MotionEvent.ACTION_DOWN) {
                setButtonImage(viewId, R.drawable.ok_button_square_down);
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP){
                setButtonImage(viewId, R.drawable.ok_button_square);
            }
        } else if (viewId == R.id.turnWrongButton) {
            if (action == MotionEvent.ACTION_DOWN) {
                setButtonImage(viewId, R.drawable.wrong_button_square_down);
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP){
                setButtonImage(viewId, R.drawable.wrong_button_square);
            }
        }
    }

    private void setButtonImage(int viewId, int drawableId) {
        ((ImageButton) findViewById(viewId)).setImageDrawable(getApplicationContext().getDrawable(drawableId));
    }

    private String getCard() {
        return ((TextView) findViewById(R.id.turnCard)).getText().toString();
    }

}
