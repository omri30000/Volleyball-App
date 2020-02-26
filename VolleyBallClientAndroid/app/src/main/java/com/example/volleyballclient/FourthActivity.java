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

public class FourthActivity extends AppCompatActivity{
    private int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("val here",Integer.toString(val));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        val = 300;
        Intent this_intent = this.getIntent();
        String name = this_intent.getExtras().getString("name");
        Log.d("name arrived", name);

        sendMessage(name);

        Intent next = new Intent(this,FifthActivity.class);
        Log.d("val here",Integer.toString(val));
        next.putExtra("code",val);
        startActivity(next);
    }


    private void sendMessage(final String name)
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
                    Socket s = new Socket("176.230.142.214", 2019);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = "$2001";
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
                catch (IOException e)
                {
                    Log.d("help","im here");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
