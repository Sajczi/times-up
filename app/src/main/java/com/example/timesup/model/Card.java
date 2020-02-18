package com.example.timesup.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {

    private String text;
    private int usedCounter;

}
