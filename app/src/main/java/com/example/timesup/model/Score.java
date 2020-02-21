package com.example.timesup.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Score implements Parcelable {

    private Long teamARoundOneScore = 0L;
    private Long teamARoundTwoScore = 0L;
    private Long teamARoundThreeScore = 0L;

    private Long teamBRoundOneScore = 0L;
    private Long teamBRoundTwoScore = 0L;
    private Long teamBRoundThreeScore = 0L;

    public void incrementTeamARoundOneScore(int score){
        this.teamARoundOneScore += score;
    }

    public void incrementTeamARoundTwoScore(int score){
        this.teamARoundTwoScore += score;
    }

    public void incrementTeamARoundThreeScore(int score){
        this.teamARoundThreeScore += score;
    }

    public void incrementTeamBRoundOneScore(int score){
        this.teamBRoundOneScore += score;
    }

    public void incrementTeamBRoundTwoScore(int score){
        this.teamBRoundTwoScore += score;
    }

    public void incrementTeamBRoundThreeScore(int score){
        this.teamBRoundThreeScore += score;
    }

    public Long sumTeamAScore() {
        return teamARoundOneScore + teamARoundTwoScore + teamARoundThreeScore;
    }

    public Long sumTeamBScore() {
        return teamBRoundOneScore + teamBRoundTwoScore +  teamBRoundThreeScore;
    }

    protected Score(Parcel in) {
        teamARoundOneScore = in.readLong();
        teamARoundTwoScore = in.readLong();
        teamARoundThreeScore = in.readLong();
        teamBRoundOneScore = in.readLong();
        teamBRoundTwoScore = in.readLong();
        teamBRoundThreeScore = in.readLong();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(teamARoundOneScore);
        dest.writeLong(teamARoundTwoScore);
        dest.writeLong(teamARoundThreeScore);
        dest.writeLong(teamBRoundOneScore);
        dest.writeLong(teamBRoundTwoScore);
        dest.writeLong(teamBRoundThreeScore);
    }
}
