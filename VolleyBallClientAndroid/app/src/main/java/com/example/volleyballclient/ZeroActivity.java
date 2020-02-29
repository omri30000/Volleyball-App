package com.example.volleyballclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ZeroActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
    private Button sendPassBtn;
    private EditText passValEt;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sendPassBtn = (Button)findViewById(R.id.btnEnrMyself);
        passValEt = (EditText) findViewById(R.id.btnSendPass);
        sp = this.getSharedPreferences("values", 0);
        Intent next = new Intent(this, FirstActivity.class);
        if (sp.getBoolean("pass", false))
        {
            startActivity(next);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == sendPassBtn) {
            String value = passValEt.getText().toString();
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
