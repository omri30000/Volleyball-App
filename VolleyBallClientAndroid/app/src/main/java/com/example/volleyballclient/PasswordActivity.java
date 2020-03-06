package com.example.volleyballclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
    private Button sendPassBtn;
    private EditText passValEt;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        sendPassBtn = (Button)findViewById(R.id.btnSendPass);
        passValEt = (EditText) findViewById(R.id.passEt);

        sp = this.getSharedPreferences("values", 0);
        next = new Intent(this, RegisterActivity.class);
        if (sp.getBoolean("pass", false))
        {
            startActivity(next);
        }
        sendPassBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Log.d("fly",v.toString());
        if (v == sendPassBtn) {
            String value = passValEt.getText().toString();
            Log.d("password entered 0", value);
            if (value.equals("4242")) {
                Log.d("password entered", value);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("pass", true);
                editor.apply();
                startActivity(next);
            }
        }
    }
    public void onBackPressed() { }

}
