package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton caloricHelperButton = findViewById(R.id.calHelperBtn);
        ImageButton workoutGenButton = findViewById(R.id.workoutGenBtn);
        ImageButton infoButton = findViewById(R.id.infoBtn);

        infoButton.setOnClickListener(this);
        caloricHelperButton.setOnClickListener(this);
        workoutGenButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.calHelperBtn:
                Intent intent1 = new Intent(this, caloricGoals.class);
                startActivity(intent1);
                break;

            case R.id.workoutGenBtn:
                Intent intent2 = new Intent(this, userQuestionnaire.class);
                startActivity(intent2);
                break;

            case R.id.infoBtn:
                Intent intent3 = new Intent(this, infoPage.class);
                startActivity(intent3);
                break;
        }
    }
}
