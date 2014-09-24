package com.example.root.cardgame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity {
    // Constants

    private static final int THREE_CARD_GAME_MATCH_COUNT = 3;
    private static final int TWO_CARD_GAME_MATCH_COUNT = 2;

    // Instance variables

    private Map<Button, PlayingCard> cardMap;
    private int countCardsUp = 0;
    private int flipCount = 0;
    private int mNumberOfCardsToCompare = TWO_CARD_GAME_MATCH_COUNT;
    private int score = 0;
    int count;

    // "Outlets"

    private List<Button> cardButtons;
    private TextView flipCountView;
    private TextView scoreCountView;
    private TextView activityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        cardButtons = new ArrayList<Button>();

        ViewGroup buttonGrid = (ViewGroup) findViewById(R.id.gridLayout);
        int count = buttonGrid.getChildCount();

        for (int i = 0; i < count; i++) {
            cardButtons.add((Button) buttonGrid.getChildAt(i));
        }

        flipCountView = (TextView) findViewById(R.id.flipCount);
        scoreCountView = (TextView) findViewById(R.id.scoreView);
        activityView = (TextView) findViewById(R.id.activityView);
        drawDeck(count);
    }

    public void drawDeck(int numberOfCards) {
        PlayingCardDeck playingCardDeck = new PlayingCardDeck();

        cardMap = new HashMap<Button, PlayingCard>();

        for (int i = 0; i < numberOfCards; i++) {
            cardMap.put(cardButtons.get(i), (PlayingCard) playingCardDeck.drawRandomCard());
        }
    }


    private void updateUI() {
    // Loop over all cards, configure each
        PlayingCard currentCard;
        Button cardImageButton;

        for (int i = 0; i < 16; i++){
            currentCard = cardMap.get(cardButtons.get(i));
            cardImageButton = (Button) cardButtons.get(i);

        //if faceup
            if (currentCard.faceUp){
                cardImageButton.setBackgroundResource(R.drawable.blankplayingcard);
                cardImageButton.setText(currentCard.getContents());

                if (currentCard.redBlackColor == 0){cardImageButton.setTextColor(Color.BLACK);}
                    else {cardImageButton.setTextColor(Color.RED);}
                }

        //if facedown
            if (!currentCard.faceUp) {
                cardImageButton.setBackgroundResource(R.drawable.bluecard);
                cardImageButton.setText("");
            }

        //if unplayable
            if (currentCard.unplayable){
                cardImageButton.setBackgroundResource(R.drawable.blankplayingcard);
                cardImageButton.setText(currentCard.getContents());
                cardImageButton.setAlpha(128);

                if (currentCard.redBlackColor == 0){cardImageButton.setTextColor(Color.BLACK);}
                else {cardImageButton.setTextColor(Color.RED);}
            }
            // Configure activity label (report of last action)
            activityView.setText("You chose: " + currentCard.getContents());
        }

        // Configure flip-count label
            flipCountView.setText("Flips: " + flipCount);
        // Configure score label
            scoreCountView.setText("Score: " + score);


    }


    public void flipOneCard(View v) {
        Button clickedButton = (Button) v;

        // use clickedButton to get the Model card object for this button
        PlayingCard card = cardMap.get(clickedButton);

        card.faceUp = (!card.isFaceUp());
        ++flipCount;
        updateUI();

        // Modify model, then call updateUI()
    }
}
