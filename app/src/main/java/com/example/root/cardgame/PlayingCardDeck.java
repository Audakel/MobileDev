package com.example.root.cardgame;

import java.util.Random;

/**
 * Created by root on 9/11/14.
 */
public class PlayingCardDeck extends Deck {

    public PlayingCardDeck() {
        super();
        Random rand=new Random();
        int x;

        int color;
        for (String suit : PlayingCard.validSuit())
        {
            for (int i = 1; i < PlayingCard.rankStrings().length; i++)
            {
                PlayingCard card = new PlayingCard();
                card.suit = suit;
                card.rank = i;

                x = rand.nextInt(10);
                if (x < 5){
                    card.redBlackColor = 1;
                }else card.redBlackColor = 0;

                addCard(card, true);
            }
        }
    }


    {



    }




}
