package com.example.root.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by root on 9/11/14.
 */
public class Deck {

    public Deck() {

    }

    List<Card> cards = new ArrayList<Card>();

    void addCard(Card card, boolean atTop){
        if (atTop){
            cards.add(0, card);
        }
        else {
            cards.add(card);
        }
    }




    public Card drawRandomCard(){
        Card randomCard;

        if (cards.size() > 0){
            Random rand = new Random();
            int index = rand.nextInt(cards.size());
            randomCard = cards.get(index);
            cards.remove(index);
            return randomCard;
        }
        return cards.get(0);
    }

}
