package com.example.ecotracker;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ListView listView = findViewById(R.id.list_view);

        String[] data = {"1 plastic bottle", "test"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.list_note, R.id.text_view1, data);
        listView.setAdapter(adapter);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                String title = "Creating Note";
                String message = "Do you want to create a note?";
                String button1String = "Yes";
                String button2String = "No";


                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this, R.style.AlertDialogCustom);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentYES = new Intent(CalendarActivity.this, NotesActivity.class);
                        intentYES.putExtra("selectedDate", selectedDate);
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
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(83,0,0));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(0,96,90));
            }
        });

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);
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
