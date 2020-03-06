package com.example.fitbud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class userQuestionnaire extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_questionare);
        Button bodyWeight = findViewById(R.id.bodyWeight);
        Button upperBody = findViewById(R.id.upperBody);
        Button lowerBody = findViewById(R.id.lowerBody);
        Button cardioAbs = findViewById(R.id.cardioAbs);
        ImageButton forwardButton = findViewById(R.id.forwardBtn);
        ImageButton backButton = findViewById(R.id.backBtn);

        bodyWeight.setOnClickListener(this);
        upperBody.setOnClickListener(this);
        lowerBody.setOnClickListener(this);
        cardioAbs.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        TextView workoutType = findViewById(R.id.workoutType);
        EditText workoutLength = findViewById(R.id.workoutTime);
        String wLength = workoutLength.getText().toString();
        switch (v.getId()){
            case R.id.bodyWeight:
                workoutType.setText("Body Weight");
                break;
            case R.id.upperBody:
                workoutType.setText("Upper Body");
                break;
            case R.id.lowerBody:
                workoutType.setText("Lower Body");
                break;
            case R.id.cardioAbs:
                workoutType.setText("Cardio/Abs");
                break;
            case R.id.forwardBtn:
                if (!"".equals(wLength)){
                    int workoutTime = Integer.parseInt(wLength);
                    if(!"".equals(workoutType.getText().toString())){
                        String typeOfWorkout = workoutType.getText().toString();

                    }
                    else{
                        showAlertDialog("type");
                    }
                }
                else{
                    showAlertDialog("time");
                }
                break;
            case R.id.backBtn:
                finish();
                break;

        }
    }
    public void showAlertDialog(String problem){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (problem.equals("type")){
            alert.setTitle("Please select a type of workout");
        }
        else if (problem.equals("time")){
            alert.setTitle("Please enter how long you can workout");
        }
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create().show();
    }
}
