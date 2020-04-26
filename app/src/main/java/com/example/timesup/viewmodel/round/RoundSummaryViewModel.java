package com.example.timesup.viewmodel.round;

import android.app.Application;

import com.example.timesup.enums.MessageCode;
import com.example.timesup.enums.RoundNumber;
import com.example.timesup.view.game.GameSummaryActivity;
import com.example.timesup.view.round.RoundStartActivity;
import com.example.timesup.viewmodel.BaseViewModel;

import java.util.ArrayList;

public class RoundSummaryViewModel extends BaseViewModel {

    public RoundSummaryViewModel(Application app) {
        super(app);
    }

    public MessageCode getRoundNumberMessageCode() {
        MessageCode messageCode;
         switch (game.getCurrentRoundNumber()) {
            case ROUND_ONE: messageCode = MessageCode.ROUND_ONE_START; break;
            case ROUND_TWO: messageCode = MessageCode.ROUND_TWO_START; break;
            case ROUND_THREE: messageCode = MessageCode.ROUND_THREE_START; break;
            default: throw new RuntimeException("ERROR PARSING ROUND NUMBER");
        }
        return messageCode;
    }

    public long getTeamAScore() {
        long score;
        switch (game.getCurrentRoundNumber()) {
            case ROUND_ONE: score = game.getScore().getTeamARoundOneScore(); break;
            case ROUND_TWO: score = game.getScore().getTeamARoundTwoScore(); break;
            case ROUND_THREE: score = game.getScore().getTeamARoundThreeScore(); break;
            default: throw new RuntimeException("ERROR PARSING ROUND NUMBER");
        }
        return score;
    }

    public long getTeamBScore() {
        long score;
        switch (game.getCurrentRoundNumber()) {
            case ROUND_ONE: score = game.getScore().getTeamBRoundOneScore(); break;
            case ROUND_TWO: score = game.getScore().getTeamBRoundTwoScore(); break;
            case ROUND_THREE: score = game.getScore().getTeamBRoundThreeScore(); break;
            default: throw new RuntimeException("ERROR PARSING ROUND NUMBER");
        }
        return score;
    }

    public void summarizeRound() {
        if(RoundNumber.ROUND_ONE.equals(game.getCurrentRoundNumber()) ||
                RoundNumber.ROUND_TWO.equals(game.getCurrentRoundNumber())) {
            game.getRound().setRoundNumber(RoundNumber.next(game.getCurrentRoundNumber()));
            game.shuffle();
            game.getRound().setAvailableCards(new ArrayList(game.getCards()));
        }
    }

    public Class getNextScreen() {
        return RoundNumber.ROUND_THREE.equals(game.getCurrentRoundNumber()) ? GameSummaryActivity.class : RoundStartActivity.class;
    }
}
