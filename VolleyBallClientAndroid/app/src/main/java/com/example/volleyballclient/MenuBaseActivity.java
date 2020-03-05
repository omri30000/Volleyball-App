package com.example.volleyballclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemWrapperICS;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuBaseActivity extends AppCompatActivity {
    private BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState,int resLayout) {
        super.onCreate(savedInstanceState);
        setContentView(resLayout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Intent next;

                switch (item.getItemId()) {
                    case R.id.home_nav:
                        next = new Intent(getApplicationContext(),HomeActivity.class);
                        break;
                    case R.id.enroll_nav:
                        next = new Intent(getApplicationContext(),EnrollActivty.class);
                        break;
                    case R.id.stats_nav:
                        next = new Intent(getApplicationContext(),StatsActivity.class);
                        break;
                }
                return true;
        }
    }

    }

    protected void ManageMenu()
    {

    }




}
