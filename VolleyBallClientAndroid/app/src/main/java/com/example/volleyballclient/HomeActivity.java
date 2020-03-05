package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView mainSv;
    private LinearLayout internalLayout;
    private Button[] enrollMe;
    private Button[] enrollFriend;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Get training list from server
        //For now, number of trainings will be constant
        loadTrainings(5);


    }

    public void  loadTrainings(int trainingsAmount)
    {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        this.mainSv = new ScrollView(this);
        this.mainSv.setLayoutParams(layoutParams);

        this.internalLayout = new LinearLayout(this);
        this.internalLayout.setLayoutParams(layoutParams);



        this.enrollMe = new Button[trainingsAmount];
        this.enrollFriend = new Button[trainingsAmount];

        for (int i = 0; i < trainingsAmount; i++)
        {
            String textOnButton = "@string/enroll_myself";
            this.enrollMe[i] = new Button(this);
            this.enrollMe[i].setText(textOnButton);
            this.enrollMe[i].setId("");

            textOnButton = "@string/enroll_a_friend";
            this.enrollFriend[i] = new Button(this);
            this.enrollFriend[i].setText(textOnButton);
        }
    }

    @Override
    public void onClick(View v) {

    }
    public void onBackPressed() { }

    /*
    private Button addMeBtn;
    private Button addFriendBtn;
    private Button editDatabtn;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addMeBtn = (Button)findViewById(R.id.btnEnrMyself);
        addFriendBtn = (Button)findViewById(R.id.btnEnrFriend);
        editDatabtn = (Button)findViewById(R.id.btnEditData);

        addMeBtn.setOnClickListener(this);
        addFriendBtn.setOnClickListener(this);
        editDatabtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == editDatabtn)
        {
            Intent back = new Intent(this,DataActivity.class);
            back.putExtra("Edit","True");
            startActivity(back);
        }
        if (v == addMeBtn)
        {
            SharedPreferences sp = v.getContext().getSharedPreferences("values",0);
            String name = sp.getString("name",null);
            if(name != null)
            {
                Log.d("name entered", name);
                Intent next = new Intent(this, SendingActivity.class);
                next.putExtra("name", name);
                startActivity(next);
            }
        }
        else if (v == addFriendBtn)
        {
            Intent next = new Intent(this,EnrollFriendActivity.class);
            startActivity(next);
        }
    }
    public void onBackPressed() { }*/

}
