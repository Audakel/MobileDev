package com.example.root.cardgame;

import java.util.Random;

/**
 * Created by root on 9/11/14.
 */
public class PlayingCard extends Card {
    public int rank;
    public static String[] rankStrings() {
        String[] s = {"?", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return s;
    }



    public String suit;
    public static String[] validSuit() {
        String[] s = {"\u2665", "\u2666", "\u2663", "\u2660"};
        return s;
    }

    public int redBlackColor;

    void setContents() {
        contents = rankStrings()[rank] + suit;
    }
    public String rankReturn = rankStrings()[rank];
    String getContents() {
        //drawablws go here
        return rankStrings()[rank]  + suit;

    }




}
