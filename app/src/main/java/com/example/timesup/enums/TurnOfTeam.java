package com.example.timesup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TurnOfTeam {
    TEAM_A("A"), TEAM_B("B");

    @Getter
    private String text;

    public static TurnOfTeam parse(String text) {
        for (TurnOfTeam item : values()) {
            if (item.getText().equals(text)) {
                return item;
            }
        }
        return null;
    }
}
