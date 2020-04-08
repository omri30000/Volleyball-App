package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.MemoryFile;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends MenuBaseActivity
{
    Training next;
    String[] players;
    TextView[] playersTexts;

    private ScrollView mainSv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        get_nearest_training();
        if(this.next != null)
        {
            loadNextTraining();
            Log.d("hihi","not heree");
        }
        else
        {
            Log.d("debug","im here");
        }
    }

    private void loadNextTraining() {

        LinearLayout l = (LinearLayout) findViewById(R.id.linearLayout1);
        this.playersTexts = new TextView[this.players.length -1 ];

        for (int i = 0; i < this.players.length; i++) {
            if(true)//players(i) == )
            {
                String text = players[i];
            this.playersTexts[i] = new TextView(this);
            this.playersTexts[i].setText(text);
            this.playersTexts[i].setTag("player" + i);

            this.playersTexts[i].setVisibility(View.VISIBLE);
            l.addView(this.playersTexts[i]);
            }
        }
    }

    protected void get_nearest_training()
    {
        SharedPreferences sp =this.getSharedPreferences("values",0);
        String msg = "$250#" +sp.getString("name",null);
        String response = Helper.sendMessage(msg);
        if(!response.startsWith("$251"))
        {
            this.next = null;
        }
        else
        {
            response = response.replace("$", "");
            String[] parts = response.split("#");
            this.next = new Training(parts[1]);
            players = parts[2].split(",");
            for (int i = 0; i < players.length; i++) {
                players[i] = Helper.decode(players[i]);
            }
        }
    }


    @Override
    protected  int getLayoutResourceId()
    {
        return R.layout.activity_home;
    }
    protected  int getIconId()
    {
        return R.id.home_nav;
    }
}

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
    public void onBackPressed() { }

}
*/