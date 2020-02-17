package com.example.timesup.model;

import com.example.timesup.enums.TurnOfTeam;

import java.util.List;

import lombok.Data;

@Data
public class Turn {

    private TurnOfTeam turnOfTeam;
    private List<String> availableCards;
    private List<String> correctCards;
    private List<String> incorrectCards;
}
