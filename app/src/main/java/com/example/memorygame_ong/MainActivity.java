package com.example.memorygame_ong;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView indicator, timer;
    ImageView b_11, b_12, b_13, b_21, b_22, b_23, b_31, b_32, b_33;
    Integer[] cardsArray = {101, 102, 103, 104, 105, 201, 202, 203, 104};
    int image101, image102, image103, image104, image105,
            image201, image202, image203, image204;
    int firstCard, secondCard;
    int firstClick, secondClick;
    int cardNumber = 1;
    int PlayerPoints = 0, cpuPoints = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main_xml), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        indicator = (TextView) findViewById(R.id.indicator);
        timer = (TextView) findViewById(R.id.timer);

        b_11 = (ImageView) findViewById(R.id.b_11);
        b_12 = (ImageView) findViewById(R.id.b_12);
        b_13 = (ImageView) findViewById(R.id.b_13);

        b_21 = (ImageView) findViewById(R.id.b_21);
        b_22 = (ImageView) findViewById(R.id.b_22);
        b_23 = (ImageView) findViewById(R.id.b_23);

        b_31 = (ImageView) findViewById(R.id.b_31);
        b_32 = (ImageView) findViewById(R.id.b_32);
        b_33 = (ImageView) findViewById(R.id.b_33);

        b_11.setTag("0");
        b_12.setTag("1");
        b_13.setTag("2");
        b_21.setTag("3");
        b_22.setTag("4");
        b_23.setTag("5");
        b_31.setTag("6");
        b_32.setTag("7");
        b_33.setTag("8");

        frontCardsR();

        Collections.shuffle(Arrays.asList(cardsArray));

        b_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_11, theCard);
            }
        });
        b_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_12, theCard);
            }
        });
        b_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_13, theCard);
            }
        });
        b_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_21, theCard);
            }
        });
        b_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_22, theCard);
            }
        });
        b_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_23, theCard);
            }
        });
        b_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_31, theCard);
            }
        });
        b_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_32, theCard);
            }
        });
        b_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(b_33, theCard);
            }
        });

    }
    private void doStuff(ImageView b, int card){
        if (cardsArray[card] == 101){
            b.setImageResource(image101);
        } else if (cardsArray[card] == 102){
            b.setImageResource(image102);
        } else if (cardsArray[card] == 103){
            b.setImageResource(image103);
        } else if (cardsArray[card] == 104){
            b.setImageResource(image104);
        } else if (cardsArray[card] == 105){
            b.setImageResource(image105);
        } else if (cardsArray[card] == 201){
            b.setImageResource(image201);
        } else if (cardsArray[card] == 202){
            b.setImageResource(image202);
        } else if (cardsArray[card] == 203){
            b.setImageResource(image203);
        } else if (cardsArray[card] == 204){
            b.setImageResource(image204);
        }
        if (cardNumber == 1){
            firstCard = cardsArray[card];
            if (firstCard > 200){
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            firstClick = card;

            b.setEnabled(false);
        } else {
            secondCard = cardsArray[card];
            if (secondCard > 200){
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            secondClick = card;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ifEqual();

             }
            }, 1000);
        }

    };
    private void ifEqual() {
        if (firstCard == secondCard) {
            if(firstClick == 0){
                b_11.setVisibility(View.INVISIBLE);
            }
            if(firstClick == 1){
                b_12.setVisibility(View.INVISIBLE);
            }
            if (firstClick == 2){
                b_13.setVisibility(View.INVISIBLE);
            }if(firstClick == 3){
                b_21.setVisibility(View.INVISIBLE);
            }
            if(firstClick == 4){
                b_22.setVisibility(View.INVISIBLE);
            }
            if (firstClick == 5){
                b_23.setVisibility(View.INVISIBLE);
            }if(firstClick == 6){
                b_31.setVisibility(View.INVISIBLE);
            }
            if(firstClick == 7){
                b_32.setVisibility(View.INVISIBLE);
            }
            if (firstClick == 8){
                b_33.setVisibility(View.INVISIBLE);
            }

            if(secondClick == 0){
                b_11.setVisibility(View.INVISIBLE);
            }
            if(secondClick == 1){
                b_12.setVisibility(View.INVISIBLE);
            }
            if (secondClick == 2){
                b_13.setVisibility(View.INVISIBLE);
            }if(secondClick == 3){
                b_21.setVisibility(View.INVISIBLE);
            }
            if(secondClick == 4){
                b_22.setVisibility(View.INVISIBLE);
            }
            if (secondClick == 5){
                b_23.setVisibility(View.INVISIBLE);
            }if(secondClick == 6){
                b_31.setVisibility(View.INVISIBLE);
            }
            if(secondClick == 7){
                b_32.setVisibility(View.INVISIBLE);
            }
            if (secondClick == 8){
                b_33.setVisibility(View.INVISIBLE);
            }


        } else {
            indicator.setText("Box does not match! Try again!");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flipBackCards();

                }
            }, 500); // Delay before flipping back
        }
    }
    private void flipBackCards() {
        indicator.setText("Select a pair of box to reveal");
        // Reset first card
        resetCard(firstClick);
        // Reset second card
        resetCard(secondClick);

        // Re-enable all buttons
        enableAllButtons();

        // Reset click count to allow new moves
        cardNumber = 1; // Allow new pairs to be selected again
    }


    private void resetCard(int cardIndex) {
        if (cardIndex == 0) {
            b_11.setImageResource(R.drawable.card_back); // Replace with your front image
            b_11.setEnabled(true);
        } else if (cardIndex == 1) {
            b_12.setImageResource(R.drawable.card_back);
            b_12.setEnabled(true);
        } else if (cardIndex == 2) {
            b_13.setImageResource(R.drawable.card_back);
            b_13.setEnabled(true);
        } else if (cardIndex == 3) {
            b_21.setImageResource(R.drawable.card_back);
            b_21.setEnabled(true);
        } else if (cardIndex == 4) {
            b_22.setImageResource(R.drawable.card_back);
            b_22.setEnabled(true);
        } else if (cardIndex == 5) {
            b_23.setImageResource(R.drawable.card_back);
            b_23.setEnabled(true);
        } else if (cardIndex == 6) {
            b_31.setImageResource(R.drawable.card_back);
            b_31.setEnabled(true);
        } else if (cardIndex == 7) {
            b_32.setImageResource(R.drawable.card_back);
            b_32.setEnabled(true);
        } else if (cardIndex == 8) {
            b_33.setImageResource(R.drawable.card_back);
            b_33.setEnabled(true);
        }
    }

    private void enableAllButtons() {
        b_11.setEnabled(true);
        b_12.setEnabled(true);
        b_13.setEnabled(true);
        b_21.setEnabled(true);
        b_22.setEnabled(true);
        b_23.setEnabled(true);
        b_31.setEnabled(true);
        b_32.setEnabled(true);
        b_33.setEnabled(true);
    }

    private void frontCardsR() {
            image101 = R.drawable.image101;
            image102 = R.drawable.image102;
            image103 = R.drawable.image103;
            image104 = R.drawable.image104;
            image201 = R.drawable.image201;
            image202 = R.drawable.image202;
            image203 = R.drawable.image203;
            image204 = R.drawable.image204;
        }
    }

