package com.example.ecotracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FractionsActivity extends AppCompatActivity implements View.OnClickListener{
    ToggleButton toggleButton;
    TextView textView;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractions);

        ListView listView = findViewById(R.id.list_view);

        ArrayList<String> dataList = new ArrayList<>();
        Collections.addAll(dataList, "PET/01", "HDPE/02", "LDPE/04", "PP/05", "PS/06", "OTHER/07", "PAP/21-23", "GL/70-74", "FE/40", "ALU/41");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.text_view, dataList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                view = super.getView(position, convertView, parent);

                toggleButton = view.findViewById(R.id.toggle_button);
                textView = view.findViewById(R.id.text_view);


                toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    String item = dataList.get(position);
                    dataList.remove(item);
                    dataList.add(0, item);
                    notifyDataSetChanged();

                    if (isChecked) {
                        toggleButton.bringToFront();
                    }
                });

                return view;
            }
        };
        listView.setAdapter(adapter);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);
    }

    private final SparseBooleanArray checkedItems = new SparseBooleanArray();

    private boolean isItemChecked(int position) {
        return checkedItems.get(position);
    }

    private void setItemChecked(int position, boolean isChecked) {
        checkedItems.put(position, isChecked);
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