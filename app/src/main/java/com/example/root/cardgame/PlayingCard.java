package com.example.root.cardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by root on 9/11/14.
 */
public class PlayingCard extends Card {
    public int rank;
    public int redBlackColor;
    public String suit;

    void setContents() {
        contents = rankStrings()[rank] + suit;
    }

    public String rankReturn = rankStrings()[rank];


    public static String[] rankStrings() {
        String[] s = {"?", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return s;
    }


    public static String[] validSuit() {
        String[] s = {"\u2665", "\u2666", "\u2663", "\u2660"};
        return s;
    }


    String getContents() {
        return rankStrings()[rank] + suit;
    }

    public boolean isFaceUp(){
        return this.faceUp;
    }


    public int compareCardsReturnScore(PlayingCard cardToCompare) {
        int FLIP_COST = 1;
        int RANK_MATCH_BONUS = 4;
        int SUIT_MATCH_BONUS = 2;
        int MISMATCH_PENALTY = 1;
        int score = 0;

        PlayingCard card1 = this;

        if (card1.suit.equals(cardToCompare.suit)) {
            score += SUIT_MATCH_BONUS;
            card1.unplayable = true;
            this.unplayable = true;
        } else score -= MISMATCH_PENALTY;

        if (card1.rankReturn.equals(cardToCompare.rankReturn)) {
            score += RANK_MATCH_BONUS;
            card1.unplayable = true;
            this.unplayable = true;
        } else score -= MISMATCH_PENALTY;

        return score;
    }
}