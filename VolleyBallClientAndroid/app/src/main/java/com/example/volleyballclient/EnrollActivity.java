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

public class EnrollActivity extends MenuBaseActivity implements View.OnClickListener {

    private int trainingsAmount;
    private ScrollView mainSv;
    private LinearLayout internalLayout;
    private Button[] enrollMe;
    private Button[] enrollFriend;
    private Training[] trainings;
    private String msg;
    private String st;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_enroll);

        getOptionalTrainings();
        //Get training list from server
        //For now, number of trainings will be constant
        loadTrainings();
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_enroll;
    }

    @Override
    protected int getIconId() {
        return R.id.enroll_nav;
    }

    public void  loadTrainings()
    {
        getOptionalTrainings();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        this.mainSv = new ScrollView(this);
        this.mainSv.setLayoutParams(layoutParams);

        this.internalLayout = new LinearLayout(this);
        this.internalLayout.setLayoutParams(layoutParams);



        this.enrollMe = new Button[this.trainingsAmount];
        this.enrollFriend = new Button[this.trainingsAmount];

        for (int i = 0; i < this.trainingsAmount; i++)
        {
            //#todo: add details to layout of each training

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
    returns the response of the server
     */
    private String sendMessage(final String msg)
    {
        //final String st = "";
        final Handler handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    Socket s = new Socket();//("176.230.142.214", 2019);
                    s.connect(new InetSocketAddress("176.230.142.108", 2019),1000);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));


                    st = input.readLine();//$200";
                    st = input.readLine();//$200";
                    Log.d("msg_from_server",st);


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
        try {

            thread.join();
        }
        catch(Exception ex)
        {
            Log.d("crash","dss");
        }

        return st;
    }


    private void enrollTraining(final String name, int trainingId)
    {
        String msg =  "$100#" + name + "#" + trainingId + "$";

        String response = sendMessage(msg);

        Log.d("msg_from_server",response);

        if(response.contains("$101$"))
        {
            Toast.makeText(getApplicationContext(), "@string/success", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "@string/fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void getOptionalTrainings()
    {
        String msg = "$200$";
        String singleTraining = "";

        String response = sendMessage(msg);

        if (response.contains("$201")) // got list of trainings
        {
            response = response.replace("$","");
            this.trainingsAmount = countAppearances(response, '#');
            Log.d("cnt",Integer.toString(this.trainingsAmount));
            trainings = new Training[this.trainingsAmount];

            //Example- "$201#[1243,25.2.2030,13:40,Goshen]#[1432,22.4.2040,13:50,beach]$"
            for (int i = 0 ; i < this.trainingsAmount; i++)
            {
                Log.d("from server",response);

                response = response.substring(response.indexOf('#') + 1);
                singleTraining = response;

                singleTraining = singleTraining.indexOf('#') != -1 ? singleTraining.substring(0, singleTraining.indexOf('#')) :singleTraining ;
                Log.d("substring",singleTraining);
                trainings[i] = new Training(singleTraining);
            }
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
                enrollTraining(name, trainingId);
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
    //deny returning back
    public void onBackPressed() { }


    //function will count times that a char appears in a string
    public int countAppearances(String str, char ch)
    {
        int cnt = 0;
        for(int i =0;i<str.length();i++)
        {
            if(str.charAt(i)== ch)
            {
                cnt++;
            }
        }
        return cnt;
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
    public void onBackPressed() { }*/
