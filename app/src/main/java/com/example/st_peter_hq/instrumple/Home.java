package com.example.st_peter_hq.instrumple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        //Assign clickables to their respective layouts

        LinearLayout keyboard = (LinearLayout) findViewById(R.id.keyboards);
        LinearLayout strings = (LinearLayout) findViewById(R.id.strings);
        LinearLayout percussion = (LinearLayout) findViewById(R.id.percussion);

        //Define click listeners
        keyboard.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(Home.this, KeyboardActivity.class);
                startActivity(numbersIntent);
            }
        });

        strings.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(Home.this, StringsActivity.class);
                startActivity(familyIntent);
            }
        });

        percussion.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(Home.this, PercussionActivity.class);
                startActivity(colorsIntent);
            }
        });
    }
}
