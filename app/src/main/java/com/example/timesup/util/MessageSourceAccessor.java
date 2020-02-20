package com.example.timesup.util;

import com.example.timesup.enums.MessageCode;

import java.util.HashMap;
import java.util.Map;

public class MessageSourceAccessor {

    private static Map<String, String> messageMap = new HashMap() {{
        put(MessageCode.ROUND_ONE_START, "RUNDA 1");
        put(MessageCode.ROUND_TWO_START, "RUNDA 2");
        put(MessageCode.ROUND_THREE_START, "RUNDA 3");
        put(MessageCode.ROUND_ONE_START_DESCRIPTION, "Osoba opowiadająca może używać dowolnych słów, niebędących wyrazami bliskobrzmiącymi ani angielskimi tłumaczeniami hasła. Drużyna grająca może zgadywać hasło dowolną ilość razy.");
        put(MessageCode.ROUND_TWO_START_DESCRIPTION, "Te same hasła do w rundzie 1, ale osoba opowiadająca może powiedzieć tylko jedno słowo. Drużyna grająca może głośno się naradzać, ale liczy się tylko jedna odpowiedź - pierwsza która padnie po uderzeniu ręką w stół przez członka drużyny grającej. Osoba opowiadająca nie może reagować na dyskusje drużyny grającej.");
        put(MessageCode.ROUND_THREE_START_DESCRIPTION, "Te same hasła do w rundzie 1, ale osoba opowiadająca pokazuje hasło. Może wydawać dźwięki, ale nie może mówić. Drużyna grająca może głośno się naradzać, ale liczy się tylko jedna odpowiedź - pierwsza która padnie po uderzeniu ręką w stół przez członka drużyny grającej. Osoba opowiadająca nie może reagować na dyskusje drużyny grającej.");
        put(MessageCode.TEAM_A, "Drużyna A");
        put(MessageCode.TEAM_B, "Drużyna B");
        put(MessageCode.TURN_START_NOW_PLAYING, "Teraz gra");
        put(MessageCode.GAME_RESULT_DRAW, "Remis!");
        put(MessageCode.GAME_RESULT_TEAM_A_WIN, "Wygrywa drużyna A!");
        put(MessageCode.GAME_RESULT_TEAM_B_WIN, "Wygrywa drużyna B!");
        put(MessageCode.TURN_CARDS_LEFT, "Pozostało kart: %d");
        put(MessageCode.TURN_SUMMARY_CORRECT_CARDS, "Odgadnięte hasła: %d");
        put(MessageCode.TURN_SUMMARY_INCORRECT_CARDS, "Niedgadnięte hasła: %d");
    }};

    public static String getMessage(MessageCode code) {
        return getMessage(code, null);
    }

    public static String getMessage(MessageCode code, Object... params) {
        return String.format(messageMap.get(code), params);
    }
}
