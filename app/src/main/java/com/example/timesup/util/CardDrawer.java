package com.example.timesup.util;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.timesup.model.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CardDrawer extends ContextWrapper {

    private static final String FILENAME = "hasla.txt";

    public CardDrawer(Context base) {
        super(base);
    }

    public List<String> drawCards(Long cardsAmount) {
        List<Card> cards = readFile();
        List<Card> chosenCards = chooseCards(cards, cardsAmount);
        updateUsedCount(cards, chosenCards);
        saveFile(cards, FILENAME);
        return chosenCards.stream().map(card -> card.getText()).collect(Collectors.toList());
    }

    private static List<Card> chooseCards(List<Card> cards, Long cardsAmount) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getUsedCounter() - c2.getUsedCounter();
            }
        });
        Collections.shuffle(cards);
        return cards.subList(0, cardsAmount.intValue());
    }

    private void updateUsedCount(List<Card> allCards, List<Card> cards) {
        List<String> chosenCardTexts = cards.stream().map(card -> card.getText()).collect(Collectors.toList());
        List<Card> updatedCards = allCards.stream()
                .map(card -> {
                    if(chosenCardTexts.contains(card.getText())) {
                        return Card.builder().text(card.getText()).usedCounter(card.getUsedCounter() +1).build();
                    }
                    return card;
                })
                .collect(Collectors.toList());
        saveFile(updatedCards, FILENAME);
    }

    private List<Card> readFile() {
        List<Card> result = new ArrayList();
        try {
            String row;
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open(FILENAME)));
            while ((row = reader.readLine()) != null) {
                String[] rowData = row.split(";");
                result.add(Card.builder().text(rowData[0]).usedCounter(Integer.valueOf(rowData[1])).build());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not read file.");
            e.printStackTrace();
        }
        return result;
    }

    private void saveFile(List<Card> updatedCards, String filename) {
        // nie można  nadpisywać assetsów -  trzeba znaleźć workaround
    }
}
