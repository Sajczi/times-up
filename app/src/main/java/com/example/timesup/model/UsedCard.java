package com.example.timesup.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UsedCard implements Parcelable {

    private String text;
    private boolean correctAnswer;

    protected UsedCard(Parcel in) {
        text = in.readString();
        correctAnswer = in.readByte() != 0;
    }

    public static final Creator<UsedCard> CREATOR = new Creator<UsedCard>() {
        @Override
        public UsedCard createFromParcel(Parcel in) {
            return new UsedCard(in);
        }

        @Override
        public UsedCard[] newArray(int size) {
            return new UsedCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeByte((byte) (correctAnswer ? 1 : 0));
    }
}
