package com.example.timesup.viewmodel.game;

import android.app.Application;

import com.example.timesup.TimesUpApplication;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.viewmodel.BaseViewModel;

public class GameStartViewModel extends BaseViewModel {

    public GameStartViewModel(Application app) {
        super(app);
    }

    public void startGame(long cardsAmount) {
        TimesUpApplication app = getApplication();
        app.initializeGame(cardsAmount);
        game = app.getGame();
    }

    @Override
    public void changeGameState() {
        game.getRound().setRoundNumber(RoundNumber.ROUND_ONE);
    }
}
