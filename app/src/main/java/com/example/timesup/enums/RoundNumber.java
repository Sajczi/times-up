package com.example.timesup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RoundNumber {
    ROUND_ONE("Round 1"), ROUND_TWO("Round 2"), ROUND_THREE("Round 3");

    @Getter
    private String text;

    public static RoundNumber parse(String text) {
        for (RoundNumber item : values()) {
            if (item.getText().equals(text)) {
                return item;
            }
        }
        return null;
    }

    public static RoundNumber next(RoundNumber currentRoundNumber) {
        return ROUND_ONE.equals(currentRoundNumber) ?  ROUND_TWO : ROUND_THREE;
    }
}
