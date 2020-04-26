package com.example.timesup.viewmodel.turn;

import android.app.Application;

import com.example.timesup.enums.MessageCode;
import com.example.timesup.viewmodel.BaseViewModel;

import java.util.ArrayList;

public class TurnStartViewModel extends BaseViewModel {

    public TurnStartViewModel(Application app) {
        super(app);
    }

    public MessageCode getTeamPlaying() {
        return game.getCurrentTurnOfTeam().getCode();
    }

    @Override
    public void changeGameState() {
        game.getRound().getTurn().setAvailableCards(new ArrayList(game.getRound().getAvailableCards()));
        game.getRound().getTurn().shuffle();
        game.getRound().getTurn().setUsedCards(new ArrayList());
    }
}
