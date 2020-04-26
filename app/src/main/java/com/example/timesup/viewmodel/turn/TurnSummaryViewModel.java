package com.example.timesup.viewmodel.turn;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.model.UsedCard;
import com.example.timesup.viewmodel.BaseViewModel;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class TurnSummaryViewModel extends BaseViewModel {

    @Getter
    private final MutableLiveData<Long> correctAnswersObservable;
    @Getter
    private final MutableLiveData<Long> incorrectAnswersObservable;

    public TurnSummaryViewModel(Application app) {
        super(app);
        correctAnswersObservable = new MutableLiveData(game.getRound().getTurn().getUsedCards().stream().filter(card -> card.isCorrectAnswer()).count());
        incorrectAnswersObservable = new MutableLiveData(game.getRound().getTurn().getUsedCards().stream().filter(card -> !card.isCorrectAnswer()).count());
    }

    public List<UsedCard> getTurnCards() {
        return game.getRound().getTurn().getUsedCards();
    }

    public void refreshAnswers() {
        correctAnswersObservable.setValue(game.getRound().getTurn().getUsedCards().stream().filter(card -> card.isCorrectAnswer()).count());
        incorrectAnswersObservable.setValue(game.getRound().getTurn().getUsedCards().stream().filter(card -> !card.isCorrectAnswer()).count());
    }

    public void sumUpScores() {
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
        passCardsToAnotherTeam();
    }

    private void passCardsToAnotherTeam() {
        game.getRound().getTurn().setTurnOfTeam(TurnOfTeam.next(game.getCurrentTurnOfTeam()));
    }

    public boolean anyCardsLeft() {
        return game.getRound().getAvailableCards().isEmpty();
    }
}
