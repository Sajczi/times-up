package com.example.timesup.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.enums.TurnOfTeam;
import com.example.timesup.util.CardDrawer;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Game implements Parcelable {

    private List<String> cards;

    private Round round;
    private Score score;

    public Game(Long cardsAmount, Context context) {
        drawCards(cardsAmount, context);
        round = new Round(cards, RoundNumber.ROUND_ONE);
        score = new Score();
    }

    protected Game(Parcel in) {
        cards = in.createStringArrayList();
        round = in.readParcelable(Round.class.getClassLoader());
        score = in.readParcelable(Score.class.getClassLoader());
    }

    public RoundNumber getCurrentRoundNumber() {
        return round.getRoundNumber();
    }

    public TurnOfTeam getCurrentTurnOfTeam() {
        return round.getTurn().getTurnOfTeam();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    private void drawCards(Long cardsAmount, Context context) {
        this.cards =  new CardDrawer(context).drawCards(cardsAmount);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.cards);
        dest.writeParcelable(this.round, flags);
        dest.writeParcelable(this.score, flags);
    }
}
