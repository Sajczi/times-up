package com.example.timesup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MessageCode {

    ROUND_ONE_START("round.one.start"),
    ROUND_TWO_START("round.two.start"),
    ROUND_THREE_START("round.three.start"),
    ROUND_ONE_START_DESCRIPTION("round.one.start.desciption"),
    ROUND_TWO_START_DESCRIPTION("round.two.start.desciption"),
    ROUND_THREE_START_DESCRIPTION("round.three.start.desciption"),
    TEAM_A("team.a"),
    TEAM_B("team.b"),
    TURN_START_NOW_PLAYING("turn.start.now.playing"),
    GAME_RESULT_DRAW("game.result.draw"),
    GAME_RESULT_TEAM_A_WIN("game.result.team.a.win"),
    GAME_RESULT_TEAM_B_WIN("game.result.team.b.win"),
    TURN_CARDS_LEFT("turn.cards.left"),
    TURN_SUMMARY_CORRECT_CARDS("turn.summary.correct.cards"),
    TURN_SUMMARY_INCORRECT_CARDS("turn.summary.incorrect.cards");

    @Getter
    private String code;

}
