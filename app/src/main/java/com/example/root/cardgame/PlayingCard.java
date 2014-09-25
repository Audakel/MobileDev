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
        String[] s = {"6", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
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

        if (this.suit.equals(cardToCompare.suit)) {
            score += SUIT_MATCH_BONUS;
            cardToCompare.unplayable = true;
            this.unplayable = true;
        } else score -= MISMATCH_PENALTY;

        if (this.rank == (cardToCompare.rank)) {
            score += RANK_MATCH_BONUS;
            cardToCompare.unplayable = true;
            this.unplayable = true;
        }

        if (!(this.rank == (cardToCompare.rank)) && !(this.suit.equals(cardToCompare.suit))){
            score -= MISMATCH_PENALTY;
            //this.faceUp = false;
            //cardToCompare.faceUp = false;
            cardToCompare.unplayable = false;
            this.unplayable = false;
        }

        return score;
    }
}