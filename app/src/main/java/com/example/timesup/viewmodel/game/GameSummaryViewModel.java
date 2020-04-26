package com.example.timesup.viewmodel.game;

import android.app.Application;

import com.example.timesup.enums.MessageCode;
import com.example.timesup.viewmodel.BaseViewModel;

public class GameSummaryViewModel extends BaseViewModel {

    public GameSummaryViewModel(Application app) {
        super(app);
    }

    @Override
    public void changeGameState() {

    }

    public MessageCode getWinningTeamMessage() {
        long teamAScore = game.getScore().sumTeamAScore();
        long teamBScore = game.getScore().sumTeamBScore();
        return teamAScore == teamBScore ? MessageCode.GAME_RESULT_DRAW
                : teamAScore > teamBScore ? MessageCode.GAME_RESULT_TEAM_A_WIN
                : MessageCode.GAME_RESULT_TEAM_B_WIN;
    }

    public String getTeamAScore() {
        return game.getScore().sumTeamAScore().toString();
    }

    public String getTeamBScore() {
        return game.getScore().sumTeamBScore().toString();
    }
}
