package com.example.lit15146664.myapplication;

/**
 * Created by lit15146664 on 07/10/2015.
 */
public class ScoreObject {

    private String name;
    private Integer score;
    private long timestamp;

    public ScoreObject(String name, int score, long timestamp){
        this.name = name;
        this.timestamp = timestamp;
        this.score = score;
    }

    public String getName(){
        return name;
    }
    public long isTime(){
        return timestamp;
    }
    public int getScore(){
        return score;
    }

}
