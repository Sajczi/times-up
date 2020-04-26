package com.example.timesup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.timesup.enums.TurnOfTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Turn implements Parcelable {

    private TurnOfTeam turnOfTeam;
    private List<String> availableCards;
    private List<UsedCard> usedCards;
    private UsedCard currentCard;

    protected Turn(Parcel in) {
        availableCards = in.createStringArrayList();
        turnOfTeam = TurnOfTeam.parse(in.readString());
        usedCards = new ArrayList();
        in.readTypedList(usedCards, UsedCard.CREATOR);
    }

    public void shuffle() {
        Collections.shuffle(availableCards);
    }

    public UsedCard drawCard() {
        currentCard = new UsedCard(availableCards.remove(0), false);
        return currentCard;
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
        dest.writeString(turnOfTeam.getCode().getCode());
        dest.writeTypedList(usedCards);
    }
}
