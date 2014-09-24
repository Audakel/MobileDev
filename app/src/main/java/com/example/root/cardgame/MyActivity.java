package com.example.root.cardgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import static android.view.View.OnClickListener;
//Button button1 = (Button) findViewById(R.id.button1);
//        Button button2 = (Button) findViewById(R.id.button2);
//        Button button3 = (Button) findViewById(R.id.button3);
//        Button button4 = (Button) findViewById(R.id.button4);
//        Button button5 = (Button) findViewById(R.id.button5);
//        Button button6 = (Button) findViewById(R.id.button6);
//        Button button7 = (Button) findViewById(R.id.button7);
//        Button button8 = (Button) findViewById(R.id.button8);
//        Button button9 = (Button) findViewById(R.id.button9);
//        Button button10 = (Button) findViewById(R.id.button10);
//        Button button11 = (Button) findViewById(R.id.button11);
//        Button button12 = (Button) findViewById(R.id.button12);
//        Button button13 = (Button) findViewById(R.id.button13);
//        Button button14 = (Button) findViewById(R.id.button14);
//        Button button15 = (Button) findViewById(R.id.button15);
//        Button button16 = (Button) findViewById(R.id.button16);

public class MyActivity extends Activity {
    Button button1 ;
    Button button2 ;
    Button button3 ;
    Button button4 ;
    Button button5 ;
    Button button6 ;
    Button button7 ;
    Button button8 ;
    Button button9 ;
    Button button10 ;
    Button button11 ;
    Button button12 ;
    Button button13 ;
    Button button14 ;
    Button button15 ;
    Button button16 ;
    TextView flipCountView;
    TextView scoreCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        flipCountView = (TextView) findViewById(R.id.flipCount);
        scoreCountView = (TextView) findViewById(R.id.scoreView);
        drawDeck();
    }

        Button[] listOfButtons = {
                button1, button2, button3, button4, button5, button6, button7, button8, button9, button10,
                button11, button12, button13, button14, button15, button16
        };


        public HashMap<Button, PlayingCard> cardHashMap;
        private int score = 0;

        public void drawDeck(){
        PlayingCardDeck playingCardDeck = new PlayingCardDeck();
        cardHashMap = new HashMap<Button, PlayingCard>();
        for (int i = 0; i < 16; i++){
            PlayingCard randomCard = (PlayingCard) playingCardDeck.drawRandomCard();
            cardHashMap.put(listOfButtons[i], randomCard);
        }
    }

        int flipCount = 0;
        public int numberOfCardsToCompare = 2;
        int countCardsUp = 0;

        public int flipCard(View v) {
        Button tempButton = (Button) findViewById(v.getId());
        PlayingCard drawnCard = new PlayingCard();
        drawnCard = cardHashMap.get(tempButton);
//        drawnCard.faceUp = true;
        Button cardImageButton = (Button) findViewById(v.getId());
        cardImageButton.setBackgroundResource(R.drawable.blankplayingcard);
//        if (drawnCard.redBlackColor == 0){
//            cardImageButton.setTextColor(Color.BLACK);
//        } else {
//            cardImageButton.setTextColor(Color.RED);
//        }
        cardImageButton.setText(drawnCard.getContents());
        flipCount++;

        for (int i = 0; i < 16; i++)
            if ((cardHashMap.get(listOfButtons[i])).faceUp) countCardsUp++;
        return countCardsUp;

    }





        public ArrayList<Integer> compareCards(){

        ArrayList<Integer> cardUpPositions = new ArrayList<Integer>();
        for (int i = 0; i < 16; i++){
            if (cardHashMap.get(listOfButtons[i]).faceUp && !cardHashMap.get(listOfButtons[i]).unplayable) {
                cardUpPositions.add(i);
            }
        }
        int positionFirstCard;
        int positionSecondCard;
        int positionThirdCard = 0;
        positionFirstCard = cardUpPositions.get(0);
        positionSecondCard = cardUpPositions.get(1);
        if (numberOfCardsToCompare == 3) positionThirdCard = cardUpPositions.get(2);

        int FLIP_COST = 1;
        int RANK_MATCH_BONUS = 4;
        int SUIT_MATCH_BONUS = 1;
        int MISMATCH_PENALTY = 1;

        PlayingCard card1 = cardHashMap.get(listOfButtons[positionFirstCard]);
        PlayingCard card2 = cardHashMap.get(listOfButtons[positionSecondCard]);
        if (numberOfCardsToCompare == 3){
            PlayingCard card3 = cardHashMap.get(listOfButtons[positionThirdCard]);
        }

        if (card1.suit.equals(card2.suit)){
            score += SUIT_MATCH_BONUS;
            card1.unplayable = true;
        }
        else score -= MISMATCH_PENALTY;
        if (card1.rankReturn.equals(card2.rankReturn)){
            score += RANK_MATCH_BONUS;
            card1.unplayable = true;
        }
        else score -= MISMATCH_PENALTY;
        scoreCountView.setText("Score: " + score);

        return cardUpPositions;

    }


        public void flipFaceDown(ArrayList<Integer> cardPositionsToFlip){

        int positionFirstCard = cardPositionsToFlip.get(0);
        int positionSecondCard = cardPositionsToFlip.get(1);
        int positionThirdCard = 0;
        if (numberOfCardsToCompare == 3) positionThirdCard = cardPositionsToFlip.get(2);

        Button cardView1 = listOfButtons[positionFirstCard];
        Button cardView2 = listOfButtons[positionSecondCard];
        Button cardView3 = listOfButtons[positionThirdCard];

        if (numberOfCardsToCompare == 3)
            cardView1.setBackgroundResource(R.drawable.bluecard);
        cardView1.setText("");
        cardView2.setBackgroundResource(R.drawable.bluecard);
        cardView2.setText("");
        if (numberOfCardsToCompare == 3){
            cardView3.setBackgroundResource(R.drawable.bluecard);
            cardView3.setText("");
        }
        countCardsUp = 0;



    }



    public void flipOneCard(View v) {
        int numberCardsFaceUp = 0;
        Button tempButton = (Button) findViewById(v.getId());
        numberCardsFaceUp = flipCard(tempButton);
        if (numberCardsFaceUp == numberOfCardsToCompare){
            ArrayList<Integer> cardsToFlipDown;
            cardsToFlipDown = compareCards();
            flipFaceDown(cardsToFlipDown);
        }

    }
}
