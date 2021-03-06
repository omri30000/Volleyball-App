package com.example.volleyballclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataActivity extends MenuBaseActivity {

    SharedPreferences sp;
    //TextView tv;
    EditText etFirstName;
    EditText etLastName;
    Button btnSend;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tv = (TextView)findViewById(R.id.)
        etFirstName = (EditText) findViewById(R.id.etFname);
        etLastName = (EditText) findViewById(R.id.etLname);
        btnSend = (Button) findViewById(R.id.button);
        sp = this.getSharedPreferences("values", 0);
        etFirstName.setHint(sp.getString("first_name","empty"));
        etLastName.setHint(sp.getString("family_name","empty"));
        next = new Intent(this, HomeActivity.class);
        String first = etFirstName.getHint().toString();//.toLowerCase();
        String second = etLastName.getHint().toString();//.toLowerCase();

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String first = etFirstName.getText().toString().toLowerCase();
                first = first.length() ==0 ?etFirstName.getHint().toString() : first;
                String second = etLastName.getText().toString().toLowerCase();
                second = second.length() ==0?etLastName.getHint().toString() : second;
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

    @Override
    protected int getLayoutResourceId()
    {
        return R.layout.activity_data;
    }    protected int getIconId()
    {
        return R.id.profile_nav;
    }

    public void onBackPressed() { }

}
