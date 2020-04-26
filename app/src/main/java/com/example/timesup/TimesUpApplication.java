package com.example.timesup;

import android.app.Application;

import com.example.timesup.model.Game;

import lombok.Getter;

public class TimesUpApplication extends Application {

    @Getter
    private static Game game;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void initializeGame(long cardsAmount) {
        game = new Game(cardsAmount, getApplicationContext());
    }
}
