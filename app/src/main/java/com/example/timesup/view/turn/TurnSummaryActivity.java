package com.example.timesup.view.turn;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.widget.CompoundButtonCompat;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Game;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.round.RoundSummaryActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TurnSummaryActivity extends BaseActivity {

    private Map<String, Boolean> answerMap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_turn_summary;
    }

    @Override
    protected void prepareView(Game game){
        generateAnswerMap();
        refreshAnswers();
        generateTable();
    }

    private void generateAnswerMap() {
        answerMap = new HashMap();
        game.getRound().getTurn().getCorrectCards().forEach(card -> answerMap.put(card, Boolean.TRUE));
        game.getRound().getTurn().getIncorrectCards().forEach(card -> answerMap.put(card, Boolean.FALSE));
    }

    private void generateTable() {
        TableLayout tableView = (TableLayout) findViewById(R.id.turnSummaryTableLayout);
        for (Map.Entry card : answerMap.entrySet()) {
            TableRow row = new TableRow(this);
            addCardTextView(row, card);
            addCheckBox(row, card);
            tableView.addView(row);
        }
    }

    private void addCheckBox(TableRow row, Map.Entry card) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setChecked((boolean) card.getValue());
        checkBox.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                answerMap.put((String) card.getKey(), !answerMap.get(card.getKey()));
                refreshAnswers();
            }
        });
        row.addView(checkBox);
    }

    private void addCardTextView(TableRow row, Map.Entry card) {
        TextView cardTextView = new TextView(this);
        cardTextView.setText(card.getKey().toString());
        cardTextView.setTextSize(16);
        cardTextView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        cardTextView.setTypeface(Typeface.create("casual", Typeface.BOLD));
        cardTextView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        row.addView(cardTextView);
    }

    private void refreshAnswers() {
        setLabelText(R.id.turnSummaryCorrectCards, MessageCode.TURN_SUMMARY_CORRECT_CARDS, answerMap.values().stream().filter(v -> v).count());
        setLabelText(R.id.turnSummaryIncorrectCards, MessageCode.TURN_SUMMARY_INCORRECT_CARDS, answerMap.values().stream().filter(v -> !v).count());
    }

    @Override
    protected void addListenerOnButton() {
        ((Button) findViewById(R.id.turnSummaryOkButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startTurn();
            }
        });
    }

    private void startTurn() {
        List<String> correctCards = answerMap.entrySet().stream().filter(t -> t.getValue()).map(t -> t.getKey()).collect(Collectors.toList());
        int score = correctCards.size();
        game.getRound().getAvailableCards().removeAll(correctCards);
        if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getCurrentTurnOfTeam())) {
                game.getScore().incrementTeamARoundOneScore(score);
            } else {
                game.getScore().incrementTeamBRoundOneScore(score);
            }
        } else if (RoundNumber.ROUND_TWO.equals(game.getCurrentRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getCurrentTurnOfTeam())) {
                game.getScore().incrementTeamARoundTwoScore(score);
            } else {
                game.getScore().incrementTeamBRoundTwoScore(score);
            }
        } else if (RoundNumber.ROUND_THREE.equals(game.getCurrentRoundNumber())) {
            if (TurnOfTeam.TEAM_A.equals(game.getCurrentTurnOfTeam())) {
                game.getScore().incrementTeamARoundThreeScore(score);
            } else {
                game.getScore().incrementTeamBRoundThreeScore(score);
            }
        };
        passCards();
        if (game.getRound().getAvailableCards().isEmpty()){
            switchActivity(RoundSummaryActivity.class);
        } else {
            switchActivity(TurnStartActivity.class);
        }
    }

    private void passCards() {
        game.getRound().getTurn().setTurnOfTeam(TurnOfTeam.next(game.getCurrentTurnOfTeam()));
    }
}
