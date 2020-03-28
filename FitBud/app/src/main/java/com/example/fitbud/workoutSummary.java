package com.example.fitbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class workoutSummary extends AppCompatActivity implements View.OnClickListener {
    public  HashMap<String, Integer> workoutMap = new HashMap<>();
    public boolean random = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_summary);

        ImageButton backBtn = findViewById(R.id.back);
        Button rando = findViewById(R.id.randomize);

        backBtn.setOnClickListener(this);
        rando.setOnClickListener(this);

        Intent intent = getIntent();
        userData user = intent.getParcelableExtra("workoutInfo");
        String workoutType = user.getTypeOfWorkout();
        int workoutTime = user.getTimeToWorkout();
        makeWorkout(workoutType, workoutTime);
        TableLayout table = findViewById(R.id.displayTable);
        int c = 1;
        TableRow row1 = new TableRow(this);
        TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row1.setLayoutParams(lp1);
        TextView w1 = new TextView(this);
        TextView t1 = new TextView(this);
        w1.setText(" Workout Type");
        t1.setText("  Time(Min.)");
        w1.setTextSize(28);
        t1.setTextSize(28);
        w1.setTextColor(Color.BLACK);
        t1.setTextColor(Color.BLACK);
        row1.addView(w1);
        row1.addView(t1);
        table.addView(row1, 0);
        for(Map.Entry<String, Integer> entry : workoutMap.entrySet()){
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView w = new TextView(this);
            TextView t = new TextView(this);
            w.setText("  "+ entry.getKey());
            t.setText("   "+entry.getValue());
            w.setTextSize(20);
            t.setTextSize(20);
            w.setTextColor(Color.BLACK);
            t.setTextColor(Color.BLACK);
            row.addView(w);
            row.addView(t);
            table.addView(row, c);
            c++;
        }

    }
    @Override
    public void onClick(View v){
        TableLayout table = findViewById(R.id.displayTable);
        Intent intent = getIntent();
        userData user = intent.getParcelableExtra("workoutInfo");
        String workoutType = user.getTypeOfWorkout();
        int workoutTime = user.getTimeToWorkout();

        switch(v.getId()){
            case R.id.randomize:
                random = true;
                workoutMap.clear();
                makeWorkout(workoutType, workoutTime);
                int c = 1;
                int count = table.getChildCount();

                for (int i = 0; i < count; i++){
                    View child = table.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }
                table.removeAllViews();


                TableRow row1 = new TableRow(this);
                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row1.setLayoutParams(lp1);
                TextView w1 = new TextView(this);
                TextView t1 = new TextView(this);
                w1.setText(" Workout Type");
                t1.setText("Time(Min.)");
                w1.setTextSize(28);
                t1.setTextSize(28);
                w1.setTextColor(Color.BLACK);
                t1.setTextColor(Color.BLACK);
                row1.addView(w1);
                row1.addView(t1);
                table.addView(row1, 0);
                for(Map.Entry<String, Integer> entry : workoutMap.entrySet()){
                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    TextView w = new TextView(this);
                    TextView t = new TextView(this);
                    w.setText("  "+entry.getKey());
                    t.setText("    "+entry.getValue());
                    w.setTextSize(20);
                    t.setTextSize(20);
                    w.setTextColor(Color.BLACK);
                    t.setTextColor(Color.BLACK);
                    row.addView(w);
                    row.addView(t);
                    table.addView(row, c);
                    c++;
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    public void makeWorkout(String workout, int workoutTime){
        int addedTime = 0;
        int count = 0;
        String nextW = "";
        while (addedTime <= workoutTime) {
            int timeLeft = workoutTime - addedTime;
            String nextworkout = getWorkout(workout, timeLeft, count);
            String[] parts = nextworkout.split(":");
            nextW = parts[0];

            if (!nextW.equals("Choose your Own")) {
                String time = parts[1];
                int t = Integer.parseInt(time);
                addedTime += t;
                workoutMap.put(nextW, t);
            }
            else{
                int tLeft = workoutTime - addedTime;
                workoutMap.put("Choose your Own", tLeft);
                break;
            }


            }
        random = false;

    }

    public String getWorkout(String workout, int timeLeft, int count) {
        String[] cardioAbs = new String[]{"Box Jumps(6x10):8", "30 Sec. Plank(6x1):4", "Up-Downs(4x10):6", "Stair Runs(5x1):5", "Crunches(5x25):5", "Leg Lifts(4xME):5", "Penguins(4x20):4",
                "Run:10", "Bike Ride:15", "Jumping Jacks(6x25):4", "30 Sec. Side Plank(3x1 each):4", "Side Crunches(3x10 each):4", "30 Sec. Jump Rope(6x1):5", "Row Machine:8", "Incline Walk:15"};
        String[] lowerBod = new String[]{"Squat(5x5):12", "Dead Lift(5x5):12", "Leg Extensions(5x10):8", "Leg Curls(5x10):8", "Dumbbell Lunges(3x10 each):8",
                "RDL's(3x10):6", "Cannonball Squats(3x10):6", "Calf Raises(4x15):4", "Weighted Step-Ups(4x12):6", "Leg Press(5x10):7", "Front Squats(3x10):5", "1 Min. Wall Sit(3x1):3",
                "Kettlebell Swing(4x15):5", "Jump Squat(5x8):6", "Barbell Hip Thrust(4x12):6"};
        String[] upperBod = new String[]{"Diamond Push-ups(3xME):4", "Wide Push-Ups(3xME):4", "Push-ups(5xME):5", "Pull-ups(5xME):7", "One Arm Tricep Ext. (5x10):8", "Bench Press(5x5):10", "Skull Crushers(4x20):8",
                "Hammer Curls(4x12):6", "Preacher Curls(5x10):7", "Seated Rows(5x15):8", "Dips(5xME):5", "Butterfly Machine(4x15):6", "Tricep Extension(5x20):8", "Cable Bicep Curls(4x12):5", "Forearm Curls(5x20):7"};
        String[] bodyWeight = new String[]{"Air Squats(5xME):5", "Jumping Jacks(6x25):4", "30 Sec. Plank(6x1):4", "Up-Downs(4x10):6", "Dips(5xME):5",
                "Calf Raises(4x15):4", "Push-ups(5xME):5", "Tuck Jump(3x6):3", "Mountain Climbers(3x20):4", "30 Sec. Wall Sit(4x1):3", "Walking Lunges(3x15 each):7", "Diamond Push-ups(3xME):4",
                "Wide Push-Ups(3xME):4", "30 Sec. Air Squat(4xME):3", "Pull-ups(5xME):7"};
        String nextW;
        String time;
        int t;
        int rnd = new Random().nextInt(15);
        String nextWorkout;
        if (count <= 30) {
            if (workout.equals("Cardio/Abs")) {
                nextWorkout = cardioAbs[rnd];
                String[] parts = nextWorkout.split(":");
                nextW = parts[0];
                time = parts[1];
                t = Integer.parseInt(time);
                if (timeLeft - t > 0 && !workoutMap.containsKey(nextW)) {
                    return nextWorkout;
                } else {
                    count++;
                    return getWorkout(workout, timeLeft, count);
                }
            } else if (workout.equals("Lower Body")) {
                nextWorkout = lowerBod[rnd];
                String[] parts = nextWorkout.split(":");
                nextW = parts[0];
                time = parts[1];
                t = Integer.parseInt(time);
                if (timeLeft - t > 0 && !workoutMap.containsKey(nextW)) {
                    return nextWorkout;
                } else {
                    count++;
                    return getWorkout(workout, timeLeft, count);
                }
            } else if (workout.equals("Upper Body")) {
                nextWorkout = upperBod[rnd];
                String[] parts = nextWorkout.split(":");
                nextW = parts[0];
                time = parts[1];
                t = Integer.parseInt(time);
                if (timeLeft - t > 0 && !workoutMap.containsKey(nextW)) {
                    return nextWorkout;
                } else {
                    count++;
                    return getWorkout(workout, timeLeft, count);
                }
            } else if (workout.equals("Body Weight")) {
                nextWorkout = bodyWeight[rnd];
                String[] parts = nextWorkout.split(":");
                nextW = parts[0];
                time = parts[1];
                t = Integer.parseInt(time);
                if (timeLeft - t > 0 && !workoutMap.containsKey(nextW)) {
                    return nextWorkout;
                } else {
                    count++;
                    return getWorkout(workout, timeLeft, count);
                }
            }
        }
        String last = "Choose your Own";
        return last;
    }

}
