package com.example.timesup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TurnOfTeam {
    TEAM_A(MessageCode.TEAM_A), TEAM_B(MessageCode.TEAM_B);

    @Getter
    private MessageCode code;

    public static TurnOfTeam parse(String text) {
        for (TurnOfTeam item : values()) {
            if (item.getCode().getCode().equals(text)) {
                return item;
            }
        }
        return null;
    }

    public static TurnOfTeam next(TurnOfTeam turnOfTeam) {
        return TEAM_A.equals(turnOfTeam) ? TEAM_B  : TEAM_A;
    }

}
