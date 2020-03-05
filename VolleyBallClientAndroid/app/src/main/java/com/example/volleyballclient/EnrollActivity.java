package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView mainSv;
    private LinearLayout internalLayout;
    private Button[] enrollMe;
    private Button[] enrollFriend;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

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
            this.enrollMe[i].setTag("Me" + i);

            textOnButton = "@string/enroll_a_friend";
            this.enrollFriend[i] = new Button(this);
            this.enrollFriend[i].setText(textOnButton);
            this.enrollFriend[i].setTag("Friend" + i);

            this.enrollMe[i].setOnClickListener(this);
            this.enrollFriend[i].setOnClickListener(this);
        }
    }

    /*
    name parameter is in structure "fName_Lname"
     */
    private void sendMessage(final String name)
    {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg =  "$100#" + name + "$";

                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    Socket s = new Socket();//("176.230.142.214", 2019);
                    s.connect(new InetSocketAddress("@string/server_ip", Integer.parseInt("@string/server_port")),1000);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    String st = "";
                    st = input.readLine();//$200";
                    Log.d("msg_from_server",st);

                    if(st.contains("$101$"))
                    {
                        Toast.makeText(getApplicationContext(), "@string/success", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "@string/fail", Toast.LENGTH_SHORT).show();
                    }

                    output.close();
                    out.close();
                    s.close();
                }
                catch (Exception e)
                {
                    Log.d("help","im here");
                    //e.printStackTrace();
                }
            }
        });

        thread.start();

        try
        {
            thread.join();
        }
        catch(Exception ex)
        {
            Log.d("crash","dss");
        }
    }

    /*
    structure of tag (type string): "Me/Friend<trainingId>"
    examples- "Me156", "Friend617"
     */
    @Override
    public void onClick(View v) {
        int trainingId = 0;
        if (v.getTag().toString().substring(0,2).equals("Me")) // enroll me was clicked
        {
            trainingId = Integer.parseInt(v.getTag().toString().substring(2));

            //Enroll him to training
            SharedPreferences sp = v.getContext().getSharedPreferences("values",0);
            String name = sp.getString("name",null);

            if(name != null)
            {
                sendMessage(name);
            }
        }
        else
        {
            trainingId = Integer.parseInt(v.getTag().toString().substring(6));

            //Enroll him to training
            Intent next = new Intent(this, EnrollFriendActivity.class);
            startActivity(next);
        }

    }
    public void onBackPressed() { }

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
    public void onBackPressed() { }*/
