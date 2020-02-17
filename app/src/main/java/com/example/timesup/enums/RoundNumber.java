package com.example.timesup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RoundNumber {
    ROUND_ONE("Round 1"), ROUND_TWO("Round 2"), ROUND_THREE("Round 3");

    @Getter
    private String text;
}
