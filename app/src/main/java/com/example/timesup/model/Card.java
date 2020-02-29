package com.example.timesup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Card {

    private String text;
    private int usedCounter;

}
