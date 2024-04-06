package com.example.ecotracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

public class PrivateSettingsActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privatesettings);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);

        SwitchCompat switchCompat1 = findViewById(R.id.switch_email);
        SwitchCompat switchCompat2 = findViewById(R.id.switch_awards);
        SwitchCompat switchCompat3 = findViewById(R.id.switch_experience);

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

        ColorStateList thumbTintListInactive = ColorStateList.valueOf(Color.parseColor("#D9D9D9"));
        switchCompat1.setTrackTintList(thumbTintListInactive);
        switchCompat2.setTrackTintList(thumbTintListInactive);
        switchCompat3.setTrackTintList(thumbTintListInactive);

        Button mBtnDelete = findViewById(R.id.delete);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Deleting account";
                String message = "Do you want to delete your account?";
                String button1String = "Yes";
                String button2String = "No";
                AlertDialog.Builder builder = new AlertDialog.Builder(PrivateSettingsActivity.this, R.style.AlertDialogCustom);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentYES = new Intent(PrivateSettingsActivity.this, LoginActivity.class);
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
