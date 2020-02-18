package com.example.timesup.controller;

import android.content.Context;
import android.content.Intent;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.Game;
import com.example.timesup.view.GameStartActivity;
import com.example.timesup.view.round.RoundStartActivity;

public class GameController {

    public Game initGame(Long cardsAmount, Context context) {
        return new Game(cardsAmount, context);
    }

    /**
    public void startRoundOne(GameStartActivity gameStartActivity) {
    }
     **/
}
