package com.example.hoangthequyen_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_report);
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