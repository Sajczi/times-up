package com.example.timesup.view.turn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timesup.R;
import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.Game;
import com.example.timesup.model.UsedCard;
import com.example.timesup.util.ComponentDimensionUtil;
import com.example.timesup.view.BaseActivity;
import com.example.timesup.view.round.RoundSummaryActivity;

import java.util.List;
import java.util.stream.Collectors;

public class TurnSummaryActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_turn_summary;
    }

    private ComponentDimensionUtil componentDimensionUtil;

    @Override
    protected void prepareView(Game game) {
        this.componentDimensionUtil = new ComponentDimensionUtil(getWindowManager());
        refreshAnswers();
        generateTable();
    }

    private void generateTable() {
        TableLayout tableView = (TableLayout) findViewById(R.id.turnSummaryTableLayout);
        for (UsedCard usedCard : game.getRound().getTurn().getUsedCards()) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f));
            addCardTextView(row, usedCard);
            addCheckBox(row, usedCard);
            if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
                addDeleteCardButton(row, usedCard);
            } else if (RoundNumber.ROUND_THREE.equals(game.getCurrentRoundNumber())) {
                addInfoButton(row, usedCard);
            }
            tableView.addView(row);
        }
    }

    private void addCardTextView(TableRow row, UsedCard usedCard) {
        TextView cardTextView = new TextView(this);
        cardTextView.setText(usedCard.getText());
        cardTextView.setTextSize(16);
        cardTextView.setGravity(Gravity.LEFT | Gravity.CENTER_HORIZONTAL);
        cardTextView.setTypeface(Typeface.create("casual", Typeface.BOLD));
        cardTextView.setLayoutParams(new TableRow.LayoutParams(0, componentDimensionUtil.getScreenHeight() * 1/20, 0.5f));
        row.addView(cardTextView);
    }

    private void addCheckBox(TableRow row, UsedCard usedCard) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setChecked(usedCard.isCorrectAnswer());
        checkBox.setLayoutParams(new TableRow.LayoutParams(0, componentDimensionUtil.getScreenHeight() * 1/20, 0.25f));
        checkBox.setGravity(Gravity.CENTER);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                usedCard.setCorrectAnswer(isChecked);
                refreshAnswers();
            }
        });
        row.addView(checkBox);
    }

    private void addInfoButton(TableRow row, UsedCard usedCard) {
        Activity a = this;
        ImageButton infoButton = new ImageButton(this);
        infoButton.setImageResource(R.drawable.question_mark_button);
        formatThirdColumnButton(infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new WikiInformationDialog(a, usedCard.getText()).show();
            }
        });
        row.addView(infoButton);
    }

    private void addDeleteCardButton(TableRow row, UsedCard usedCard) {
        Activity a = this;
        Game g = game;
        ImageButton deleteCardButton = new ImageButton(this);
        deleteCardButton.setImageResource(R.drawable.delete_card_button);
        formatThirdColumnButton(deleteCardButton);
        deleteCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new AlertDialog.Builder(TurnSummaryActivity.this)
                        .setTitle("USUŃ HASŁO")
                        .setMessage(String.format("Czy na pewno chcesz usunąć %S z gry?", usedCard.getText().toUpperCase()))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                g.replaceCard(usedCard, TurnSummaryActivity.this);
                                refreshTable(usedCard);
                            }})
                        .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }}).show();
            }
        });
        row.addView(deleteCardButton);
    }

    private void refreshTable(UsedCard usedCard) {
        TableLayout tableView = (TableLayout) findViewById(R.id.turnSummaryTableLayout);
        for (int i = 0; i < tableView.getChildCount(); i++) {
            View child = tableView.getChildAt(i);
            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;
                for (int x = 0; x < row.getChildCount(); x++) {
                    View view = row.getChildAt(x);
                    if (view instanceof TextView) {
                        if (usedCard.getText().equals(((TextView) view).getText())) {
                            tableView.removeView(child);
                            break;
                        }
                    }
                }
            }
        }
        refreshAnswers();
    }

    private void formatThirdColumnButton(ImageButton infoButton) {
        infoButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        infoButton.setBackgroundColor(0xE7D27F);
        infoButton.setLayoutParams(new TableRow.LayoutParams(0, componentDimensionUtil.getScreenHeight() * 1/20, 0.25f));
    }

    private void refreshAnswers() {
        setLabelText(R.id.turnSummaryCorrectCards, MessageCode.TURN_SUMMARY_CORRECT_CARDS, game.getRound().getTurn().getUsedCards().stream().filter(card -> card.isCorrectAnswer()).count());
        setLabelText(R.id.turnSummaryIncorrectCards, MessageCode.TURN_SUMMARY_INCORRECT_CARDS, game.getRound().getTurn().getUsedCards().stream().filter(card -> !card.isCorrectAnswer()).count());
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
        List<String> correctCards = game.getRound().getTurn().getUsedCards().stream().filter(card -> card.isCorrectAnswer()).map(card -> card.getText()).collect(Collectors.toList());
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
