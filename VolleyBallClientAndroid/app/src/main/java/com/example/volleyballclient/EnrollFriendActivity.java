package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class EnrollFriendActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private Button btnSend;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        etName = (EditText)findViewById(R.id.etName);
        btnSend = (Button)findViewById(R.id.btnSend);
        etName.setHint("Name");
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String friendName = etName.getText().toString();
        String[] splitStr = friendName.trim().toLowerCase().split("\\s+");
        String val = "helo_helo";
        if (v == btnSend)
        {
            if(splitStr.length  == 2) {
                val = splitStr[0] + '_' + splitStr[1];
            }

            enrollTraining(friendName, Objects.requireNonNull(this.getIntent().getExtras()).getString("trainingID"));
        }
    }

    /*
    the function will enroll a player to a training by it's ID
    input: the name of the player in the template: fName_lName, the id of the training
    output: none
     */
    private void enrollTraining(final String name, String trainingId)
    {
        String msg =  "$100#" + trainingId + "#" + name + "$\n";

        String response = Helper.sendMessage(msg);

        Log.d("msg_from_server",response);

        if(response.contains("$101$"))
        {
            Toast.makeText(getApplicationContext(), getString(R.string.success), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.fail), Toast.LENGTH_SHORT).show();
        }
    }
    //public void onBackPressed() { }

}
