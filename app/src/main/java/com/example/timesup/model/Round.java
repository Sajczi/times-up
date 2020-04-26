package com.example.timesup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timesup.enums.RoundNumber;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Round implements Parcelable {

    private RoundNumber roundNumber;
    private List<String> availableCards;
    private Turn turn;

    protected Round(Parcel in) {
        turn = in.readParcelable(Turn.class.getClassLoader());
        availableCards = in.createStringArrayList();
        roundNumber = RoundNumber.parse(in.readString());
    }

    public static final Creator<Round> CREATOR = new Creator<Round>() {
        @Override
        public Round createFromParcel(Parcel in) {
            return new Round(in);
        }

        @Override
        public Round[] newArray(int size) {
            return new Round[size];
        }
    };

    public Round(List<String> cards, RoundNumber roundNumber) {
        this.availableCards = new ArrayList(cards);
        this.roundNumber = roundNumber;
        this.turn = new Turn();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.turn, flags);
        dest.writeStringList(availableCards);
        dest.writeString(roundNumber.getText());
    }

}
