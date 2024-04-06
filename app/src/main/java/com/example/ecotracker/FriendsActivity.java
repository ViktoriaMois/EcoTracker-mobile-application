package com.example.ecotracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FriendsActivity extends AppCompatActivity implements View.OnClickListener{

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);

        ListView friends_list = findViewById(R.id.list_friends);
        ListView other_users_list = findViewById(R.id.list_other_users);

        ArrayList<String> dataList = new ArrayList<>();
        Collections.addAll(dataList, "Test1", "Test2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_friends, R.id.username, dataList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                view = super.getView(position, convertView, parent);

                ImageButton delete = view.findViewById(R.id.delete);
                ImageButton view_page = view.findViewById(R.id.view_user);
                TextView username = view.findViewById(R.id.username);
                TextView email = view.findViewById(R.id.email);

                return view;
            }
        };

        friends_list.setAdapter(adapter);

        ArrayList<String> dataList2 = new ArrayList<>();
        Collections.addAll(dataList2, "Test3", "Test4");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_other_users, R.id.username, dataList2) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                view = super.getView(position, convertView, parent);

                ImageButton add_user = view.findViewById(R.id.add_user);
                ImageButton view_page = view.findViewById(R.id.view_user);
                TextView username = view.findViewById(R.id.username);
                TextView experience = view.findViewById(R.id.experience);

                return view;
            }
        };

        other_users_list.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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