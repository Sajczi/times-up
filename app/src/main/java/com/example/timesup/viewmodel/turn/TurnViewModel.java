package com.example.timesup.viewmodel.turn;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.UsedCard;
import com.example.timesup.viewmodel.BaseViewModel;

import lombok.Getter;

public class TurnViewModel extends BaseViewModel {

    private Integer i = 30;
    private Handler handler = new Handler();
    private Runnable r;

    @Getter
    private final MutableLiveData<Integer> timeObservable;
    @Getter
    private final MutableLiveData<String> cardObservable;
    @Getter
    private final MutableLiveData<Integer> cardsLeftObservable;
    @Getter
    private final MutableLiveData<Boolean> timesUpObservable;

    public TurnViewModel(Application app) {
        super(app);
        timeObservable = new MutableLiveData(i);
        cardObservable = new MutableLiveData(game.getRound().getTurn().getCurrentCard());
        cardsLeftObservable = new MutableLiveData(game.getRound().getTurn().getAvailableCards().size());
        timesUpObservable = new MutableLiveData(false);
        refreshCard();
        initStopWatch();
    }

    private void initStopWatch() {
        r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 1000);
                i--;
                if (i <= 0) {
                    game.getRound().getTurn().getUsedCards().add(game.getRound().getTurn().getCurrentCard());
                    finishTurn();
                } else {
                    timeObservable.setValue(i);
                }
            }
        };
        handler.postDelayed(r, 1000);
    }

    public void handleIncorrectAnswer(String card) {
        cardGuessedIncorrectly(card);
        if (RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber())) {
            i -= 5;
            if (i<=0) {
                finishTurn();
            }
        }
        drawNextCard();
    }

    public void handleCorrectAnswer(String card) {
        cardGuessedCorrectly(card);
        drawNextCard();
    }

    private void cardGuessedCorrectly(String card) {
        addToUsedCards(card, true);
    }

    private void cardGuessedIncorrectly(String card) {
        addToUsedCards(card, false);
    }

    private void addToUsedCards(String card, boolean guessedCorrectly) {
        game.getRound().getTurn().getUsedCards().add(new UsedCard(card, guessedCorrectly));
    }

    private void drawNextCard() {
        if(canDrawNextCard()) {
            refreshCard();
        } else {
            finishTurn();
        }
    }

    private boolean canDrawNextCard() {
        return !game.getRound().getTurn().getAvailableCards().isEmpty();
    }

    private void refreshCard() {
        UsedCard newCard = game.getRound().getTurn().drawCard();
        Integer cardsLeft = game.getRound().getTurn().getAvailableCards().size();
        timeObservable.setValue(i);
        cardObservable.setValue(newCard.getText());
        cardsLeftObservable.setValue(cardsLeft);
    }

    private void finishTurn() {
        timesUpObservable.setValue(true);
        handler.removeCallbacks(r);
    }
}
