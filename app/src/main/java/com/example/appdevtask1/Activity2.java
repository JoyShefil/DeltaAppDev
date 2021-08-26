 package com.example.appdevtask1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Locale;

 public class Activity2 extends AppCompatActivity {

    TextView question, timer;
    Button button1, button2, button3, button4;
    Button button;
    LinearLayout linear1,linear2, linearLayout;
    TextView text7,text10;
    int score=0, highScore;
    int Answer,L1,L2,LL;
    String crctans, Qns, resultText= "",result="";
    Vibrator vibrator;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private long Start_Time = 30000;
    private static final long Start_Time_bg = 1000;
    private static final long Start_time_bgw = 1000;

    private CountDownTimer tCountDownTimer;
    private CountDownTimer bCountDownTimer;
    private boolean tTimerRunning;
    private boolean bTimerRunning;
    private long tTimeLeft;
    private long bTimeLeft = Start_Time_bg;
    private CountDownTimer wCountDownTimer;
    private long wTimeLeft = Start_time_bgw;

    private final View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(((Button) findViewById(v.getId())).getText().toString().equals(crctans)){
               /* resetTimer();
                printQue();
                score+=3;
                vibrator.vibrate(250);
                linear1.setBackgroundColor(Color.GREEN);
                startTimer(); */
                bgStartTimer();
                linear1.setBackgroundColor(Color.GREEN);
                vibrator.vibrate(250);

            }
            else {
                score-=1;
                if (score>highScore){
                    editor.putInt("highScore",score);
                    editor.commit();
                }
                /* linear2.setVisibility(View.INVISIBLE);
                linear1.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                text7.setText(MainActivity.getName() + "'s Score is " + "\n" + score);
                vibrator.vibrate(500);
                linearLayout.setBackgroundColor(Color.RED);
                tCountDownTimer.cancel(); */
                bgwStartTimer();
                linear1.setBackgroundColor(Color.RED);
                vibrator.vibrate(500);
                text7.setText(MainActivity.getName() + "'s Score: " + score);
                text10.setText("Number of Correct Answers: " + (score+1)/3
                        + "\n" + "Number of Wrong Answers: 1" + "\n"
                        + "Un-attempted Questions: 0");

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        question= findViewById(R.id.question);
        button= findViewById(R.id.button);
        linear1= findViewById(R.id.linear1);
        linear2= findViewById(R.id.linear2);
        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        button4= findViewById(R.id.button4);
        text7= findViewById(R.id.textView7);
        vibrator= (Vibrator)getSystemService(VIBRATOR_SERVICE);
        linearLayout= findViewById(R.id.linearlayout);
        timer= findViewById(R.id.timer);
        text10= findViewById(R.id.textView10);
        if (Qns == null){
            Qns = generateRandomDate();
        }
        //text7.setVisibility(View.INVISIBLE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        highScore= sharedPreferences.getInt("highScore",0);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startTimer();
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.INVISIBLE);
                tTimeLeft=Start_Time;
                startTimer();
                Qns = generateRandomDate();
                question.setText(Qns);
                getOptions();
                button1.setText(options.get(0));
                button2.setText(options.get(1));
                button3.setText(options.get(2));
                button4.setText(options.get(3));
                options.clear();

            }
        });
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);


    }

    ArrayList<String> optionList= new ArrayList<>();

    private String generateRandomDate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randbetween(1950,2100);
        gc.set(GregorianCalendar.YEAR, year);

        int dayOfYear = randbetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);

        Answer = gc.get(GregorianCalendar.DAY_OF_WEEK);

        /*Qns = "Q. What Day of the Week is " + gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                (gc.get(GregorianCalendar.MONTH) +1) + "/" +
                gc.get(GregorianCalendar.YEAR);*/

        return "Q. What Day of the Week is " + gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                (gc.get(GregorianCalendar.MONTH) +1) + "/" +
                gc.get(GregorianCalendar.YEAR);
    }

    private static int randbetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));

    }
     private ArrayList<String> options=new ArrayList<>();
    public void getOptions(){
        ArrayList<String> day=new ArrayList<>();
        day.add("SUNDAY");
        day.add("MONDAY");
        day.add("TUESDAY");
        day.add("WEDNESDAY");
        day.add("THURSDAY");
        day.add("FRIDAY");
        day.add("SATURDAY");
        crctans= day.get(Answer-1);
        day.remove(Answer-1);
        Collections.shuffle(day);
        options.add(crctans);
        for (int i=0;i<3;i++){
            options.add(day.get(i));
        }
        Collections.shuffle(options);
        optionList= options;
    }
    public void printQue(){
        //Qns = generateRandomDate();
        question.setText(Qns);
        getOptions();
        button1.setText(options.get(0));
        button2.setText(options.get(1));
        button3.setText(options.get(2));
        button4.setText(options.get(3));
        options.clear();
    }
    @Override
    public void onBackPressed(){
        if (text7.getVisibility()==View.VISIBLE)
            startActivity(new Intent(Activity2.this, MainActivity.class));
    }
    private void startTimer(){
        tCountDownTimer = new CountDownTimer(tTimeLeft, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                tTimeLeft=millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                vibrator.vibrate(500);
                bgwStartTimer();
                linear1.setBackgroundColor(Color.RED);
                text7.setText(MainActivity.getName() + "'s Score is " + score);
                text10.setText("Number of Correct Answers: " + score/3
                        + "\n" + "Number of Wrong Answers: 0" + "\n"
                        + "Un-attempted Questions: 1");
                if (score>highScore){
                    editor.putInt("highScore",score);
                    editor.commit();
                }

            }
        }.start();
        tTimerRunning = true;
    }
    private void resetTimer() {
        tTimeLeft = Start_Time;
        updateCountDown();
        tCountDownTimer.cancel();
    }

    private void updateCountDown() {
        int sec = (int) tTimeLeft / 1000;
        String timeLeft = String.format("Time Left: " + sec);
        timer.setText(timeLeft);
    }

    private void bgStartTimer(){
        bCountDownTimer=new CountDownTimer(bTimeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bTimeLeft = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                linear1.setBackgroundColor(Color.TRANSPARENT);
                resetTimer();
                Qns= generateRandomDate();
                printQue();
                score+=3;
                //vibrator.vibrate(250);
                //linear1.setBackgroundColor(Color.GREEN);
                startTimer();

            }
        }.start();
    }
    private void bgwStartTimer(){
        wCountDownTimer = new CountDownTimer(wTimeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                wTimeLeft= millisUntilFinished;
            }

            @Override
            public void onFinish() {
                linear2.setVisibility(View.INVISIBLE);
                linear1.setVisibility(View.INVISIBLE);
                linear1.setBackgroundColor(Color.TRANSPARENT);
                linearLayout.setVisibility(View.VISIBLE);
                //text7.setText(MainActivity.getName() + "'s Score is " + "\n" + score);
                //vibrator.vibrate(500);
                //linearLayout.setBackgroundColor(Color.RED);
                tCountDownTimer.cancel();

            }
        }.start();
    }

     @Override
     public void onSaveInstanceState(@NonNull Bundle outState) {
         super.onSaveInstanceState(outState);
         outState.putInt("Score", score);
         outState.putString("GRD", Qns);
         outState.putInt("Ans", Answer);
         outState.putLong("timer", tTimeLeft);
         outState.putStringArrayList("options",optionList);
         outState.putInt("L1",linear1.getVisibility());
         outState.putInt("L2",linear2.getVisibility());
         outState.putInt("LL",linearLayout.getVisibility());
         if (text7.getVisibility()==View.VISIBLE){
             outState.putString("Result1", text7.getText().toString());
             outState.putString("Result",text10.getText().toString());
         }


     }

     @Override
     protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
         super.onRestoreInstanceState(savedInstanceState);
         score= savedInstanceState.getInt("Score",0);
         Qns = savedInstanceState.getString("GRD");
         Answer = savedInstanceState.getInt("Ans");
         tTimeLeft = savedInstanceState.getLong("timer");
         Log.v("timer","" + tTimeLeft);
         //startTimer();
         optionList = savedInstanceState.getStringArrayList("options");
         printQue();
         /*if(Qns != ""){
             bCountDownTimer.cancel();
             Log.v("Shefil","Shefil");
         }*/
         Log.v("Shefil", Qns);
         //bCountDownTimer.cancel();
         //startTimer();
         //updateCountDown();
         linear2.setVisibility(savedInstanceState.getInt("L2"));
         linear1.setVisibility(savedInstanceState.getInt("L1"));
         linearLayout.setVisibility(savedInstanceState.getInt("LL"));
         if (text7.getVisibility()==View.VISIBLE){
             text7.setText(savedInstanceState.getString("Result1"));
             text10.setText(savedInstanceState.getString("Result"));
         }
         if (linear2.getVisibility()==View.VISIBLE){
             tTimeLeft=30000;
         }
         if (linear1.getVisibility()==View.VISIBLE){
             startTimer();
         }



     }
     @Override
     protected void onStop(){
        super.onStop();
        if (tCountDownTimer!=null){
        tCountDownTimer.cancel();
     }
    }
 }

