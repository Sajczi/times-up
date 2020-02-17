package com.example.timesup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timesup.enums.RoundNumber;
import com.example.timesup.util.CardDrawer;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Game implements Parcelable {

    private List<String> cards;

    private Round round;
    private Score score;

    public Game(Long cardsAmount) {
        drawCards(cardsAmount);
        round = new Round(cards);
        score = new Score();
    }

    protected Game(Parcel in) {
        cards = in.createStringArrayList();
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

    private void drawCards(Long cardsAmount) {
        this.cards =  CardDrawer.drawCards(cardsAmount);
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
        String s = "s";
    }
}
