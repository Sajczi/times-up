package com.example.timesup.view.turn;

import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;

public class TurnActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
       return R.layout.activity_turn;
    }

    @Override
    protected void prepareView(Game game) {
        refreshCard();
    }

    @Override
    protected void addListenerOnButton() {
        (findViewById(R.id.turnOkButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        game.getRound().getTurn().getCorrectCards().add(getCard());
                        if(!finishTurnIfNoMoreCards()) {
                            refreshCard();
                        }
                        ((ImageButton) v).setImageDrawable(getApplicationContext().getDrawable(R.drawable.ok_button_square_down));
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        ((ImageButton) v).setImageDrawable(getApplicationContext().getDrawable(R.drawable.ok_button_square));
                        break;
                    }
                }
                return true;
            }
        });
        (findViewById(R.id.turnWrongButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        game.getRound().getTurn().getIncorrectCards().add(getCard());
                        if(!finishTurnIfNoMoreCards()) {
                            refreshCard();
                        }
                        ((ImageButton) v).setImageDrawable(getApplicationContext().getDrawable(R.drawable.wrong_button_square_down));
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        ((ImageButton) v).setImageDrawable(getApplicationContext().getDrawable(R.drawable.wrong_button_square));
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void refreshCard(){
        setLabelText(R.id.turnStartTeamPlaying, game.getRound().getTurn().drawCard());
        setLabelText(R.id.turnCardsLeft, MessageCode.TURN_CARDS_LEFT, game.getRound().getTurn().getAvailableCards().size());
    }

    private boolean finishTurnIfNoMoreCards() {
        if (game.getRound().getTurn().getAvailableCards().isEmpty()) {
            switchActivity(TurnSummaryActivity.class);
            return true;
        }
        return false;
    }

    private String getCard() {
        return ((TextView)findViewById(R.id.turnStartTeamPlaying)).getText().toString();
    }

}
