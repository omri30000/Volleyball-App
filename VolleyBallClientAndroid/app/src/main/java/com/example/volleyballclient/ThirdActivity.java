package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private Button btnSend;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etName = (EditText)findViewById(R.id.etName);
        btnSend = (Button)findViewById(R.id.btnSend);

        etName.setHint("Name");
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String friendName = etName.getText().toString();
        String[] splitStr = friendName.trim().toLowerCase().split("\\s+");
        if (v == btnSend)
        {
            Intent next = new Intent(this,FourthActivity.class);
            next.putExtra("name",splitStr[0] + '_' + splitStr[1]);
            startActivity(next);
        }
    }
}
