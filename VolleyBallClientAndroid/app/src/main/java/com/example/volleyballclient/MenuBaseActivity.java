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

public abstract class MenuBaseActivity extends AppCompatActivity {
    protected BottomNavigationView menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(this.getIconId());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Intent next;

                switch (item.getItemId()) {
                    case R.id.home_nav:
                        next = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(next);
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.enroll_nav:
                        next = new Intent(getApplicationContext(),EnrollActivity.class);
                        startActivity(next);
                        overridePendingTransition(0,0);

                        return true;

                    case R.id.stats_nav:
                        next = new Intent(getApplicationContext(),StatsActivity.class);
                        startActivity(next);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile_nav:
                        next = new Intent(getApplicationContext(),DataActivity.class);
                        next.putExtra("Edit","True");
                        startActivity(next);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
        }


    });


    }
    protected abstract int getLayoutResourceId();
    protected abstract int getIconId();
}
