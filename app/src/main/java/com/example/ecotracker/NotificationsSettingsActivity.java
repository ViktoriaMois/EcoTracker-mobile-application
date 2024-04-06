package com.example.ecotracker;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

public class NotificationsSettingsActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationssettings);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);

        SwitchCompat switchCompat1 = findViewById(R.id.switch_awards);
        SwitchCompat switchCompat2 = findViewById(R.id.switch_news);
        SwitchCompat switchCompat3 = findViewById(R.id.switch_events);
        SwitchCompat switchCompat4 = findViewById(R.id.switch_friend_requests);

        int activeColor = ContextCompat.getColor(this, R.color.turquoise);
        int inactiveColor = ContextCompat.getColor(this, R.color.pink);

        ColorStateList thumbColors = new ColorStateList(
                new int[][] {
                        new int[] { android.R.attr.state_checked },
                        new int[] { -android.R.attr.state_checked }
                },
                new int[] { activeColor, inactiveColor }
        );

        switchCompat1.setThumbTintList(thumbColors);
        switchCompat2.setThumbTintList(thumbColors);
        switchCompat3.setThumbTintList(thumbColors);
        switchCompat4.setThumbTintList(thumbColors);

        ColorStateList thumbTintListInactive = ColorStateList.valueOf(Color.parseColor("#D9D9D9"));
        switchCompat1.setTrackTintList(thumbTintListInactive);
        switchCompat2.setTrackTintList(thumbTintListInactive);
        switchCompat3.setTrackTintList(thumbTintListInactive);
        switchCompat4.setTrackTintList(thumbTintListInactive);
    }

    @Override
    public void onClick(View v) {
        HashMap<Integer, Runnable> actions = new HashMap<>();
        actions.put(R.id.calendar, () -> startActivity(new Intent(this, CalendarActivity.class)));
        actions.put(R.id.profile_btn, () -> startActivity(new Intent(this, ProfileActivity.class)));
        actions.put(R.id.main_btn, () -> startActivity(new Intent(this, MainActivity.class)));

        Runnable action = actions.get(v.getId());
        if (action != null) {
            action.run();
        }
    }
}
