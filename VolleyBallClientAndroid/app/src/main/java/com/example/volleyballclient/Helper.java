package com.example.volleyballclient;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public  class Helper {
    private static String st = "";
    /*
name parameter is in structure "fName_Lname"
returns the response of the server
 */
    public static String sendMessage(final String msg)
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
                    s.connect(new InetSocketAddress("192.168.1.25",2019),1000);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));


                    st = input.readLine();//$200";
                    st = input.readLine();//$200";
                    Log.d("msg_from_server", st);



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
        Log.d("string",st);
        return st;
    }


    //function will count times that a char appears in a string
    public static int countAppearances(String str, char ch)
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


    public static String decode(String name) {
        String[] parts = name.split("_");
        if(parts.length == 2)
            return parts[0] + " " + parts[1];
        return "Unknown";
    }
}
