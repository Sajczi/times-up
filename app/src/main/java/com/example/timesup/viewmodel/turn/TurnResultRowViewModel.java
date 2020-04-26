package com.example.timesup.viewmodel.turn;

import android.app.Application;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.model.UsedCard;
import com.example.timesup.viewmodel.BaseViewModel;

public class TurnResultRowViewModel extends BaseViewModel {
    public TurnResultRowViewModel(Application app) {
        super(app);
    }

    public boolean isFirstRound() {
        return RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber());
    }

    public boolean isThirdRound() {
        return RoundNumber.ROUND_THREE.equals(game.getCurrentRoundNumber());
    }

    public void replaceCard(UsedCard usedCard) {
        game.replaceCard(usedCard);
    }
}
