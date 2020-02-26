package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addMeBtn;
    private Button addFriendBtn;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        addMeBtn = (Button)findViewById(R.id.btnEnrMyself);
        addFriendBtn = (Button)findViewById(R.id.btnEnrFriend);

        addMeBtn.setOnClickListener(this);
        addFriendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == addMeBtn)
        {
            count++;
        }
        else if (v == addFriendBtn)
        {
            count++;
        }
    }
}
