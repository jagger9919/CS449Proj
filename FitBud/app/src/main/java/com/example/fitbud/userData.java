package com.example.fitbud;

public class userData {
    private int weight;
    private int age;
    private int timeToWorkout;
    private String typeOfWorkout;
    private int calsBurned;
    public userData(int weight, int age, int timeToWorkout, String typeOfWorkout){
        this.weight = weight;
        this.age = age;
        this.timeToWorkout = timeToWorkout;
        this.typeOfWorkout = typeOfWorkout;
    }
    public String getTypeOfWorkout(){
        return typeOfWorkout;
    }
    public int getAge(){
        return age;
    }
    public int getWeight(){
        return weight;
    }
    public int getTimeToWorkout(){
        return timeToWorkout;
    }
    public void setcalsBurned(int calories){
        this.calsBurned = calories;
    }
    public int getCalsBurned(){
        return calsBurned;
    }
}
