package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            Intent next = new Intent(this,SendingActivity.class);//TODO:fix the problem
            if(splitStr.length  == 2) {
                val = splitStr[0] + '_' + splitStr[1];
            }
            next.putExtra("name",val);
            startActivity(next);
        }
    }
    //public void onBackPressed() { }

}
