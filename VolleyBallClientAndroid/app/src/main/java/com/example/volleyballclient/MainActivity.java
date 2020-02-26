package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private Button btnSend;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText)findViewById(R.id.etName);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String friendName = etName.getText().toString();
        if (v == btnSend)
        {
            count++;
        }
    }
}
