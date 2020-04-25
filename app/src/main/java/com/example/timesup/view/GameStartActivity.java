package com.example.timesup.view;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.timesup.R;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Card;
import com.example.timesup.model.Game;
import com.example.timesup.model.Turn;
import com.example.timesup.model.UsedCard;
import com.example.timesup.view.round.RoundStartActivity;
import com.example.timesup.view.turn.TurnSummaryActivity;
import com.example.timesup.view.turn.WikiInformationDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class GameStartActivity extends BaseActivity {

    private Long cardsAmount = 40L;

    @Override
    protected int getLayoutId(){
        return R.layout.activity_game_start;
    }

    @Override
    protected boolean fetchGameState() {
        return false;
    }

    @Override
    protected void prepareView(Game game) {
        TextView textView = (TextView) findViewById(R.id.cardsAmount);
        textView.setText(cardsAmount.toString());
    }

    @Override
    protected void addListenerOnButton() {
        ((Button) findViewById(R.id.startButton)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cardsAmount = Long.valueOf(((TextView) findViewById(R.id.cardsAmount)).getText().toString());
                game =  new Game(cardsAmount, getApplicationContext());
                game.getRound().setTurn(new Turn());
                game.getRound().getTurn().setTurnOfTeam(TurnOfTeam.TEAM_A);
                switchActivity(RoundStartActivity.class);
            }

        });
    }

    @Override
    protected void changeGameState() {
        game.getRound().setRoundNumber(RoundNumber.ROUND_ONE);
    }

}
