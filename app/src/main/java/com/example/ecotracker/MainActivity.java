package com.example.ecotracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);

        Button mBtnMap = findViewById(R.id.go_to_map);
        mBtnMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HashMap<Integer, Runnable> actions = new HashMap<>();
        actions.put(R.id.calendar, () -> startActivity(new Intent(this, CalendarActivity.class)));
        actions.put(R.id.profile_btn, () -> startActivity(new Intent(this, ProfileActivity.class)));
        actions.put(R.id.main_btn, () -> startActivity(new Intent(this, MainActivity.class)));
        actions.put(R.id.go_to_map, () -> startActivity(new Intent(this, MapActivity.class)));

        Runnable action = actions.get(v.getId());
        if (action != null) {
            action.run();
        }
    }

}
