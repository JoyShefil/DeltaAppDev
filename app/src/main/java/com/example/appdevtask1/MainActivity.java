package com.example.appdevtask1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        public Button button;
        EditText editText;
        private static String name;
        TextView textView12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.startBtn);
        textView12 = findViewById(R.id.textView12);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int high = sharedPreferences.getInt("highScore",0);
        textView12.setText("HighScore: " + high);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()==0){
                    button.setVisibility(View.GONE);
                }
                 else {
                     button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= editText.getText().toString();
                Intent intent= new Intent(MainActivity.this,Activity2.class);
                startActivity(intent);
            }
        });
    }
    public static String getName(){
        return name;
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}