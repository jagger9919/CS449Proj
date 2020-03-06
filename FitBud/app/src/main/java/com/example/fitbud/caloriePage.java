package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


public class caloriePage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_page);
        TextView totalCals = findViewById(R.id.totalCals);
        TextView bmiType = findViewById(R.id.bmiType);
        TextView bmiNum = findViewById(R.id.bmi);
        Button addCals = findViewById(R.id.addCals);
        ImageButton userInfo = findViewById(R.id.userInfo);
        ImageButton macroButton = findViewById(R.id.macroButton);
        Intent dataIntent = getIntent();
        userData user = dataIntent.getParcelableExtra("user");

        int gWeight = user.getGoalWeight();
        int cWeight = user.getWeight();
        int height = user.getHeight();
        float waist = user.getWaist();
        String sex = user.getSex();
        double metricHeight = height * 0.0254;
        double metricWeight = cWeight * 0.453592;
        double heightSquared = Math.pow(metricHeight, 2);
        double bmi = metricWeight / heightSquared;
        bmi = Math.round(bmi * 100.0) / 100.0;
        String bmiRaiting = getBmiRaiting(bmi);

        int cals = getCals(gWeight, cWeight, bmiRaiting, sex);

        userInfo.setOnClickListener(this);
        addCals.setOnClickListener(this);
        macroButton.setOnClickListener(this);
        bmiType.setText(bmiRaiting);
        bmiNum.setText("BMI: " + bmi);
        totalCals.setText("Daily Caloric Intake: " + cals);


    }

    @Override
    public void onClick(View v) {
        Intent dataIntent = getIntent();
        userData user = dataIntent.getParcelableExtra("user");

        int gWeight = user.getGoalWeight();
        int cWeight = user.getWeight();
        int height = user.getHeight();
        String sex = user.getSex();
        double metricHeight = height * 0.0254;
        double metricWeight = cWeight * 0.453592;
        double heightSquared = Math.pow(metricHeight, 2);
        double bmi = metricWeight / heightSquared;
        bmi = Math.round(bmi * 100.0) / 100.0;
        String bmiRaiting = getBmiRaiting(bmi);

        EditText calsToAdd = findViewById(R.id.addCalories);
        String calsToAddString = calsToAdd.getText().toString();
        int calsAdd;
        if (!"".equals(calsToAddString)) {
            calsAdd = Integer.parseInt(calsToAddString);
        }
        else{
            calsAdd = 0;
        }

        ProgressBar calProgress = findViewById(R.id.progressBar);
        TextView calsAteOverCals = findViewById(R.id.calsAteCals);
        TextView warning = findViewById(R.id.warning);
        int calsAte = calProgress.getProgress();

        int cals = getCals(gWeight, cWeight, bmiRaiting, sex);
        user.setTotalCals(cals);
        switch (v.getId()) {
            case R.id.addCals:
                calsAte += calsAdd;
                if (calsAte <= cals) {
                    calsAteOverCals.setText("" + calsAte + "/" + cals);
                    calProgress.setMax(cals);
                    calProgress.setProgress(calsAte);
                    warning.setText("");
                }
                else{
                    calsAteOverCals.setText("" + calsAte + "/" + cals);
                    calProgress.setMax(cals);
                    calProgress.setProgress(calsAte);
                    calProgress.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    warning.setText("Over Daily Limit");
                }

                break;
            case R.id.userInfo:
                finish();
                break;

            case R.id.macroButton:
                Intent macros = new Intent(this, macroNutrients.class);
                macros.putExtra("user", user);
                startActivity(macros);
                break;
        }


    }

    public String getBmiRaiting(double bmi) {
        String bmiRate = "";
        if (bmi <= 18.5) {
            bmiRate = "Underweight";
        } else if (18.5 < bmi && bmi <= 24.9) {
            bmiRate = "Healthy Weight";
        } else if (25 <= bmi && bmi <= 29.9) {
            bmiRate = "Overweight";
        } else if (30 <= bmi) {
            bmiRate = "Obese";
        }
        return (bmiRate);
    }

    public int getCals(int gWeight, int cWeight, String bmiRaiting, String sex) {
        int cals;
        if (sex.equals("MALE")) {
            if (gWeight > cWeight) {
                int weightDiff = gWeight - cWeight;
                if (weightDiff >= 15 && gWeight >= 200) {
                    cals = 3500;
                } else if (weightDiff >= 15 && gWeight < 200) {
                    cals = 3000;
                } else if (weightDiff >= 10 && gWeight >= 200) {
                    cals = 3300;
                } else if (weightDiff >= 10 && gWeight < 200) {
                    cals = 2900;
                } else if (weightDiff >= 5 && gWeight >= 200) {
                    cals = 3000;
                } else if (weightDiff >= 5 && gWeight < 200) {
                    cals = 2800;
                } else if (gWeight >= 200) {
                    cals = 2900;
                } else {
                    cals = 2750;
                }

            }
            else if (gWeight < cWeight) {
                int weightDiff = cWeight - gWeight;
                weightDiff = Math.abs(weightDiff);
                if (weightDiff >= 15 && gWeight < 200){
                    cals = 2100;
                }
                else if (weightDiff >= 15 && gWeight >= 200){
                    cals = 2000;
                }
                else if(weightDiff >= 10 && gWeight < 200){
                    cals = 2200;
                }
                else if (weightDiff >= 10 && gWeight >= 200){
                    cals = 2100;
                }
                else if (weightDiff >= 5 && gWeight < 200){
                    cals = 2300;
                }
                else if (weightDiff >=5 && gWeight  >= 200){
                    cals = 2200;
                }
                else{
                    cals = 2400;
                }
            }
            else {
                if (cWeight > 200 && bmiRaiting.equals("Obese")) {
                    cals = 3000;
                } else if (cWeight > 200 && bmiRaiting.equals("Overweight")) {
                    cals = 2800;
                } else if (cWeight < 200 && !bmiRaiting.equals("Obese")) {
                    cals = 2400;
                }
                else {
                    cals = 2700;
                }
            }
        }
        else{
            if (gWeight > cWeight){
                int weightDiff = gWeight - cWeight;
                if (weightDiff >= 15 && gWeight >= 175) {
                    cals = 2700;
                } else if (weightDiff >= 15 && gWeight < 175) {
                    cals = 2600;
                } else if (weightDiff >= 10 && gWeight >= 175) {
                    cals = 2600;
                } else if (weightDiff >= 10 && gWeight < 175) {
                    cals = 2500;
                } else if (weightDiff >= 5 && gWeight >= 175) {
                    cals = 2500;
                } else if (weightDiff >= 5 && gWeight < 175) {
                    cals = 2400;
                } else if (gWeight >= 175) {
                    cals = 2500;
                } else {
                    cals = 2400;
                }
            }
            else if (gWeight < cWeight){
                int weightDiff = cWeight - gWeight;
                weightDiff = Math.abs(weightDiff);
                if (weightDiff >= 15 && gWeight < 175){
                    cals = 1600;
                }
                else if (weightDiff >= 15 && gWeight >= 175){
                    cals = 1500;
                }
                else if(weightDiff >= 10 && gWeight < 175){
                    cals = 1700;
                }
                else if (weightDiff >= 10 && gWeight >= 175){
                    cals = 1600;
                }
                else if (weightDiff >= 5 && gWeight < 175){
                    cals = 1800;
                }
                else if (weightDiff >=5 && gWeight  >= 175){
                    cals = 1700;
                }
                else{
                    cals = 1800;
                }

            }
            else {
                if (cWeight > 175 && bmiRaiting.equals("Obese")) {
                    cals = 2600;
                } else if (cWeight > 175 && bmiRaiting.equals("Overweight")) {
                    cals = 2400;
                } else if (cWeight < 175 && !bmiRaiting.equals("Obese")) {
                    cals = 2200;
                }
                else {
                    cals = 2000;
                }
            }
        }
        return cals;
    }

}






