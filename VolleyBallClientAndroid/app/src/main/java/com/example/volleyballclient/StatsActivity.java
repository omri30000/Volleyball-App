package com.example.volleyballclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class StatsActivity extends MenuBaseActivity {

    SharedPreferences sp;
    //TextView tv;
    EditText etFirstName;
    EditText etLastName;
    Button btnSend;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_stats;
    }

    @Override
    protected int getIconId() {
        return R.id.stats_nav;
    }

    public void onBackPressed() { }

}
