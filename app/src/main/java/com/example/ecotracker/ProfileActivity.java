package com.example.ecotracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        Button mBtnSettings = findViewById(R.id.userSettings);
        mBtnSettings.setOnClickListener(this);

        Button mBtnFriends = findViewById(R.id.userFriends);
        mBtnFriends.setOnClickListener(this);

        Button mBtnFractions = findViewById(R.id.userFractions);
        mBtnFractions.setOnClickListener(this);

        Button mBtnAwards = findViewById(R.id.userAwards);
        mBtnAwards.setOnClickListener(this);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);

        Button mBtnExit = findViewById(R.id.exit);
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Leaving account";
                String message = "Do you want to leave your account?";
                String button1String = "Yes";
                String button2String = "No";
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this, R.style.AlertDialogCustom);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentYES = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intentYES);
                    }
                });
                builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(83, 0, 0));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(0, 96, 90));
            }
    });
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        HashMap<Integer, Runnable> actions = new HashMap<>();
        actions.put(R.id.userSettings, () -> startActivity(new Intent(this, SettingsActivity.class)));
        actions.put(R.id.userAwards, () -> startActivity(new Intent(this, AwardsActivity.class)));
        actions.put(R.id.userFriends, () -> startActivity(new Intent(this, FriendsActivity.class)));
        actions.put(R.id.userFractions, () -> startActivity(new Intent(this, FractionsActivity.class)));

        actions.put(R.id.calendar, () -> startActivity(new Intent(this, CalendarActivity.class)));
        actions.put(R.id.profile_btn, () -> startActivity(new Intent(this, ProfileActivity.class)));
        actions.put(R.id.main_btn, () -> startActivity(new Intent(this, MainActivity.class)));

        Runnable action = actions.get(v.getId());
        if (action != null) {
            action.run();
        }
    }
}
