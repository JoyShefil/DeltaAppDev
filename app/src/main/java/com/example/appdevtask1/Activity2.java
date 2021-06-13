package com.example.appdevtask1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

public class Activity2 extends AppCompatActivity {

    TextView question;
    Button button1, button2, button3, button4;
    Button button;
    LinearLayout linear1,linear2;
    TextView text7;
    int score=0;
    int Answer;
    String crctans;

    private final View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(((Button) findViewById(v.getId())).getText().toString().equals(crctans)){
                printQue();
                score+=3;
            }
            else {
                linear2.setVisibility(View.INVISIBLE);
                linear1.setVisibility(View.INVISIBLE);
                text7.setVisibility(View.VISIBLE);
                text7.setText(MainActivity.getName() + "'s Score is " + "\n" +
                        score);

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
        text7.setVisibility(View.INVISIBLE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.INVISIBLE);
            } {
                question.setText(generateRandomDate());
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

        return "Q. What Day of the Week is " + gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                (gc.get(GregorianCalendar.MONTH) +1) + "/" +
                gc.get(GregorianCalendar.YEAR);
    }

    private static int randbetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));

    }
     private final ArrayList<String> options=new ArrayList<>();
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
        question.setText(generateRandomDate());
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
            finishAffinity();
    }
}