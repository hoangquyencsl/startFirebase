package com.example.hoangthequyen_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;


import com.example.hoangthequyen_project.Fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView.SearchAutoComplete fab;
    private BottomNavigationView navigationView;
    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        navigationView = findViewById(R.id.navigation);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_bottom:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.search_bottom:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.noti_bottom:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.profile_bottom:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent_setting = new Intent(this, SettingsActivity.class);
                startActivity(intent_setting);
                return true;
            case R.id.action_peport:
                Intent intent_report = new Intent(this, ReportActivity.class);
                startActivity(intent_report);
                return true;
            case R.id.close:
                System.exit(0);
                //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

