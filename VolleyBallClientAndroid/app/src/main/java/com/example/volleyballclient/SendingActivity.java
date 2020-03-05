package com.example.volleyballclient;

import android.content.Intent;
import android.icu.text.BreakIterator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class SendingActivity extends AppCompatActivity implements View.OnClickListener{
    private int val;
    private EditText ipEt;
    Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button sendBtn;
        Log.d("val here",Integer.toString(val));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        thisIntent = this.getIntent();

        sendBtn = (Button)findViewById(R.id.sendBtnIp);
        this.ipEt = (EditText)findViewById(R.id.ipEt);

        sendBtn.setOnClickListener(this);
    }


    private void sendMessage(final String name,final String ip)
    {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg =  "$100#" + name + "$";
                    Log.d("msg sent",msg);
                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    Log.d("im here","im arrived here before sockets");
                    Socket s = new Socket();//("176.230.142.214", 2019);
                    s.connect(new InetSocketAddress(ip, 2019),1000);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    //st = input.readLine()
                    String st = input.readLine();//$200";
                    st= input.readLine();//$200";
                    Log.d("msg_from_server",st);
                    if(st.contains("$200"))
                    {
                        val = 200;
                    }
                    output.close();
                    out.close();
                    s.close();
                    Log.d("end", Integer.toString(val));
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
    }


    public void onBackPressed() { }

    @Override
    public void onClick(View v) {
        String name = Objects.requireNonNull(thisIntent.getExtras()).getString("name");
        if (name != null)
            Log.d("name arrived", name);

        val = 300;

        String ipVal = "176.230.142.";
        String input =  ipEt.getText().toString();

        ipVal += input;

        sendMessage(name,ipVal);

        Intent next = new Intent(v.getContext(),FifthActivity.class);
        Log.d("val here",Integer.toString(val));
        next.putExtra("code",val);
        startActivity(next);
    }
}
