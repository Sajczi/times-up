package com.example.timesup.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.timesup.TimesUpApplication;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;

import lombok.Getter;

public class BaseViewModel extends AndroidViewModel {

    @Getter
    protected Game game;

    public BaseViewModel(Application app) {
        super(app);
        this.game = ((TimesUpApplication) app).getGame();
    }

    public RoundNumber getCurrentRoundNumber() {
        return game.getCurrentRoundNumber();
    }

    public void changeGameState() {}
}
