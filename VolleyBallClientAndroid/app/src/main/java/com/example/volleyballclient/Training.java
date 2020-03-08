package com.example.volleyballclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Training extends AppCompatActivity {
    private int trainingId;
    private String date;
    private String time;
    private String place;

    //Example- "[1243,25.2.2030,13:40,Goshen]"
    Training(String initMsg)
    {
        String msg = initMsg;//.substring(1); // start from first digit of id
        this.trainingId = Integer.parseInt(msg.substring(0, msg.indexOf(',')));
        msg = msg.substring(msg.indexOf(',') + 1); //start from first digit of date

        this.date = msg.substring(0, msg.indexOf(','));
        msg = msg.substring(msg.indexOf(',') + 1); //start from first digit of time

        this.time = msg.substring(0, msg.indexOf(','));
        msg = msg.substring(msg.indexOf(',') + 1); //start from first digit of place

        this.place = msg;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }
}
