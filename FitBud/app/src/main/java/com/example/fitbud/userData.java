package com.example.fitbud;



import android.os.Parcel;
import android.os.Parcelable;


public class userData implements Parcelable {
    private int weight;
    private int goalWeight;
    private int height;
    private int timeToWorkout;
    private String typeOfWorkout;
    private int calsBurned;
    private float waist;
    private String sex;
    private int totalCals;
    public userData(int weight, int goalWeight,int height, float waistSize, String sex){
        this.weight = weight;
        this.height = height;
        this.goalWeight = goalWeight;
        this.waist = waistSize;
        this.timeToWorkout = 30;
        this.typeOfWorkout = "";
        this.sex = sex;
    }

    protected userData(Parcel in) {
        weight = in.readInt();
        goalWeight = in.readInt();
        height = in.readInt();
        timeToWorkout = in.readInt();
        typeOfWorkout = in.readString();
        calsBurned = in.readInt();
        waist = in.readFloat();
        sex = in.readString();
        totalCals = in.readInt();
    }

    public static final Creator<userData> CREATOR = new Creator<userData>() {
        @Override
        public userData createFromParcel(Parcel in) {
            return new userData(in);
        }

        @Override
        public userData[] newArray(int size) {
            return new userData[size];
        }
    };

    public String getTypeOfWorkout(){
        return typeOfWorkout;
    }
    public int getHeight(){
        return this.height;
    }
    public int getWeight(){
        return this.weight;
    }
    public int getGoalWeight() {
        return this.goalWeight;
    }
    public int getTimeToWorkout(){
        return timeToWorkout;
    }
    public void setcalsBurned(int calories){
        this.calsBurned = calories;
    }
    public void setTimeToWorkout(int workoutTime){
        this.timeToWorkout = workoutTime;
    }
    public void setTypeOfWorkout(String type){
        this.typeOfWorkout = type;
    }
    public void setTotalCals(int cals){ this.totalCals = cals; }
    public float getWaist(){
        return this.waist;
    }
    public int getCalsBurned(){
        return this.calsBurned;
    }
    public String getTypeofWorkout(){
        return this.typeOfWorkout;
    }
    public String getSex(){ return this.sex; }
    public int getTotalCals(){ return this.totalCals;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(weight);
        dest.writeInt(goalWeight);
        dest.writeInt(height);
        dest.writeInt(timeToWorkout);
        dest.writeString(typeOfWorkout);
        dest.writeInt(calsBurned);
        dest.writeFloat(waist);
        dest.writeString(sex);
        dest.writeInt(totalCals);
    }
}
