package com.example.fitbud;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class macroNutrients extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_nutrients);
        Intent intent = getIntent();
        userData user = intent.getParcelableExtra("user");
        int totalCals = user.getTotalCals();

        ImageButton backarrow = findViewById(R.id.backArrow);
        backarrow.setOnClickListener(this);
        TextView protein = findViewById(R.id.chickenMacros);
        TextView carbs = findViewById(R.id.carbMacros);
        TextView fats = findViewById(R.id.fatMacros);

        double totalCarbs = getCarbs(totalCals);
        double totalProtein = getProtein(totalCals);
        double totalFats = getFat(totalCals);

        protein.setText("Food from Proteins(g): " + totalProtein);
        carbs.setText("Food from Carbs(g): "+ totalCarbs);
        fats.setText("Food from Fats(g): "+ totalFats);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.backArrow:
                finish();
                break;
        }
    }

    public double getCarbs(int cal){
        double carbs;
        double percent;
        if (cal > 2750){
            percent = 0.50;
        }
        else if (cal > 2400){
            percent = 0.45;
        }
        else if (cal > 2000){
            percent = 0.40;
        }
        else{
            percent = 0.40;
        }
        carbs = cal*percent/4;
        return carbs;
    }
    public double getProtein(int cal){
        double protein;
        double percent;
        if (cal > 2750){
            percent = 0.35;
        }
        else if (cal > 2400){
            percent = 0.35;
        }
        else if (cal > 2000){
            percent = 0.30;
        }
        else{
            percent = 0.30;
        }
        protein = cal*percent/4;
        return protein;
    }
    public double getFat(int cal){
        double fats;
        double percent;
        if (cal > 2750){
            percent = 0.15;
        }
        else if (cal > 2400){
            percent = 0.20;
        }
        else if (cal > 2000){
            percent = 0.30;
        }
        else{
            percent = 0.30;
        }
        fats = cal*percent/9;
        return fats;
    }

}
