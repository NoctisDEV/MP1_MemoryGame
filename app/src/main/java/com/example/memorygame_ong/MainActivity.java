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
import android.os.CountDownTimer;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView indicator;
    ImageView b_11, b_12, b_13, b_21, b_22, b_23, b_31, b_32, b_33;
    Integer[] cardsArray = {101, 102, 103, 104, 105, 201, 202, 203, 204};
    int image101, image102, image103, image104, image105,
            image201, image202, image203, image204;
    int firstCard, secondCard;
    int firstClick, secondClick;
    int cardNumber = 1;
    int PlayerPoints = 0;
    CountDownTimer countDownTimer;
    private TextView timer;
    private static final long TIMER_DURATION = 60000; // 60 seconds
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second
    private boolean isTimerStarted = false;
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
        initAndStartTimer();

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
        if (!isTimerStarted) {
            initAndStartTimer();
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
            handler.postDelayed(() -> {
                if (checkMatch()){
                    handleMatch();
                }else {
                    handleNoMatch();
                }
            },1000);
        }

    };
    private void initAndStartTimer() {
        if (!isTimerStarted) {
            isTimerStarted = true;
            startTimer();
        }
    }
    private void handleGameLoss() {
        indicator.setText("You Lose!");
        timer.setText("");
        disableAllButtons();
        Handler handler = new Handler();
        handler.postDelayed(this::restartGame, 3000);
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(TIMER_DURATION, COUNTDOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                DecimalFormat df = new DecimalFormat("00");
                long seconds = millisUntilFinished / 1000;
                timer.setText(df.format(seconds) + " seconds");
            }

            public void onFinish() {
                timer.setText("");
                endGame();
            }
        }.start();
    }
    private void endGame() {
        indicator.setText("Times up! You Lose!");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                restartGame();
            }
        }, 6000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    private void disableAllButtons() {
        b_11.setEnabled(false);
        b_12.setEnabled(false);
        b_13.setEnabled(false);
        b_21.setEnabled(false);
        b_22.setEnabled(false);
        b_23.setEnabled(false);
        b_31.setEnabled(false);
        b_32.setEnabled(false);
        b_33.setEnabled(false);
    }
    private void restartGame() {
        // Reset game state
        PlayerPoints = 0;
        cardNumber = 1;
        firstCard = 0;
        secondCard = 0;
        firstClick = 0;
        secondClick = 0;

        // Shuffle cards again
        Collections.shuffle(Arrays.asList(cardsArray));

        // Reset all cards to face down
        resetAllCards();

        // Enable all buttons
        enableAllButtons();

        // Reset indicator text
        indicator.setText("Select a pair of boxes to reveal");

        // Reset timer
        startTimer();
    }

    private void hideMatchingCards(int firstClick, int secondClick) {
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
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flipBackCards();
                }
            }, 1000); // Delay before flipping back
        }
    }
    private boolean checkMatch(){
        return firstCard == secondCard;

    }
    private void handleNoMatch(){

        resetIndicatorText();
        flipBackCards();
    }
    private void handleMatch(){
        correctIndicatorText();
        hideMatchingCards(firstClick, secondClick);
        updateScore();
        checkEnd();
    }


    private void flipBackCards() {
        // Reset first card
        resetCard(firstClick);
        // Reset second card
        resetCard(secondClick);

        // Re-enable all buttons
        enableAllButtons();

        // Reset click count to allow new moves
        cardNumber = 1; // Allow new pairs to be selected again

    }
    private void resetIndicatorText() {
        indicator.setText("Box does not match! Try again!");
    }
    private void correctIndicatorText() {
            indicator.setText("Pair is correct, select new pair!");
    }

    private void resetCard(int cardIndex) {
        if (cardIndex == 0) {
            b_11.setImageResource(R.drawable.card_back);
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
    private void resetAllCards() {
        b_11.setImageResource(R.drawable.card_back);
        b_12.setImageResource(R.drawable.card_back);
        b_13.setImageResource(R.drawable.card_back);
        b_21.setImageResource(R.drawable.card_back);
        b_22.setImageResource(R.drawable.card_back);
        b_23.setImageResource(R.drawable.card_back);
        b_31.setImageResource(R.drawable.card_back);
        b_32.setImageResource(R.drawable.card_back);
        b_33.setImageResource(R.drawable.card_back);
    }
    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
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
    private void updateScore(){
        PlayerPoints++;


    }
    private void checkEnd(){
        if (PlayerPoints == 4){
            b_11.setVisibility(View.VISIBLE);
            b_12.setVisibility(View.VISIBLE);
            b_13.setVisibility(View.VISIBLE);
            b_21.setVisibility(View.VISIBLE);
            b_22.setVisibility(View.VISIBLE);
            b_23.setVisibility(View.VISIBLE);
            b_31.setVisibility(View.VISIBLE);
            b_32.setVisibility(View.VISIBLE);
            b_33.setVisibility(View.VISIBLE);

            indicator.setText("You Win!");
            timer.setText("");
            pauseTimer();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    restartGame();
                }
            }, 6000);

        }
    }

    private void frontCardsR() {
            image101 = R.drawable.image101;
            image102 = R.drawable.image102;
            image103 = R.drawable.image103;
            image104 = R.drawable.image104;
            image105 = R.drawable.image105;
            image201 = R.drawable.image201;
            image202 = R.drawable.image202;
            image203 = R.drawable.image203;
            image204 = R.drawable.image204;
        }
    }

