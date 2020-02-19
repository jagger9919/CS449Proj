package com.example.fitbud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class caloricGoals extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloric_goals);
        ImageButton forwardButton = findViewById(R.id.forwardBtn);
        ImageButton backButton = findViewById(R.id.backBtn);

        forwardButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        EditText heightVal = findViewById(R.id.height);
        EditText goalWeightVal = findViewById(R.id.goalWeight);
        EditText currWeightVal = findViewById(R.id.weight);
        EditText waistVal = findViewById(R.id.waistSize);

        String sHeight = heightVal.getText().toString();
        String gWeight = goalWeightVal.getText().toString();
        String cWeight = currWeightVal.getText().toString();
        String sWaist = waistVal.getText().toString();

        switch(v.getId()){
            case R.id.backBtn:
                finish();
                break;

            case R.id.forwardBtn:
                if (!"".equals(sHeight)) {
                    int Height = Integer.parseInt(sHeight);
                    if (!"".equals(gWeight)){
                        int GoalW = Integer.parseInt(gWeight);
                        if (!"".equals(cWeight)){
                            int CurrW = Integer.parseInt(cWeight);
                            if (!"".equals(sWaist)){
                                float Waist = Integer.parseInt(sWaist);

                            }
                            else{
                                showAlertDialog("waist");

                            }
                        }
                        else{
                            showAlertDialog("Cweight");
                        }
                    }
                    else{
                        showAlertDialog("Gweight");
                    }

            }
            else{
                showAlertDialog("height");
                }
                break;
        }
    }
    public void showAlertDialog(String problem){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (problem.equals("Cweight")){
            alert.setTitle("Please enter your current weight");
        }
        else if (problem.equals("height")){
            alert.setTitle("Please enter your height");
        }
        else if (problem.equals("Gweight")){
            alert.setTitle("Please enter your goal weight");
        }
        else if (problem.equals("waist")){
            alert.setTitle("Please enter your waist size");
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
