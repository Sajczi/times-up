package com.example.timesup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timesup.enums.TurnOfTeam;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Turn implements Parcelable {

    private TurnOfTeam turnOfTeam;
    private List<String> availableCards;
    private List<String> correctCards;
    private List<String> incorrectCards;

    protected Turn(Parcel in) {
        availableCards = in.createStringArrayList();
        correctCards = in.createStringArrayList();
        incorrectCards = in.createStringArrayList();
        turnOfTeam = TurnOfTeam.parse(in.readString());
    }

    public String drawCard() {
        return availableCards.remove(0);
    }

    public static final Creator<Turn> CREATOR = new Creator<Turn>() {
        @Override
        public Turn createFromParcel(Parcel in) {
            return new Turn(in);
        }

        @Override
        public Turn[] newArray(int size) {
            return new Turn[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(availableCards);
        dest.writeStringList(correctCards);
        dest.writeStringList(incorrectCards);
        dest.writeString(turnOfTeam.getCode().getCode());
    }
}
