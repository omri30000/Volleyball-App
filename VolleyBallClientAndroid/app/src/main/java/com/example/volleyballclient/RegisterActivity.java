package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText etFirstName;
    EditText etLastName;
    Button btnSend;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFirstName = (EditText) findViewById(R.id.etFname);
        etLastName = (EditText) findViewById(R.id.etLname);
        btnSend = (Button) findViewById(R.id.button);
        sp = this.getSharedPreferences("values", 0);
        next = new Intent(this, HomeActivity.class);
        //if olready registered
        if(sp.getString("name",null) != null)
        {
            startActivity(next);
        }
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String first = etFirstName.getText().toString().toLowerCase();
                String second = etLastName.getText().toString().toLowerCase();
                String name =first+ "_" + second;

                SharedPreferences.Editor editor= sp.edit();
                editor.putString("first_name",first);
                editor.putString("family_name",second);
                editor.putString("name",name);
                editor.apply();

                Log.d("name put to shared",name);
                startActivity(next);
            }
        });
    }

    public void onBackPressed() { }

}
