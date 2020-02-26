package com.example.volleyballclient;

import android.content.Intent;
import android.icu.text.BreakIterator;
import android.os.Bundle;
import android.os.Handler;
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

    int val = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Intent this_intent = this.getIntent();
        sendMessage(this_intent.getExtras().getString("name"));
        Intent next = new Intent(this,FifthActivity.class);
        next.putExtra("code",val);
        startActivity(next);
    }


    private void sendMessage(final String msg)
    {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                int val = 300;
                try {
                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    Socket s = new Socket("176.230.142.214", 2019);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();
                    if(st.contains("200"))
                    {
                        val = 200;
                    }
                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
