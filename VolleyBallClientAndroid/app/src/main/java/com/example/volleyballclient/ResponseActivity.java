package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ResponseActivity extends AppCompatActivity {
    TextView tvMes;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int code = 300;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        tvMes = (TextView)findViewById(R.id.SERVER_VALUE);
        btnBack = (Button)findViewById(R.id.button_back);
        Intent prev = this.getIntent();
        try
        {
            code = Objects.requireNonNull(prev.getExtras()).getInt("code");
        }
        catch (NullPointerException ex)
        {
            code = 300;
        }

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent BackIntent = new Intent(v.getContext(),SecondActivity.class);
                startActivity(BackIntent);
            }
        });
        if(code == 200)
        {
            this.getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            tvMes.setText("SUCCESS");
        }
        else
        {
            this.getWindow().getDecorView().setBackgroundColor(Color.RED);
            tvMes.setText("FAIL");

        }
    }
    public void onBackPressed() {

    }

}
