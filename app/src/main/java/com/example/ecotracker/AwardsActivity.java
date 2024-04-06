package com.example.ecotracker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class AwardsActivity extends AppCompatActivity implements View.OnClickListener{

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);

        ImageButton mBtnCalendar = findViewById(R.id.calendar);
        mBtnCalendar.setOnClickListener(this);

        ImageButton mBtnProfile = findViewById(R.id.profile_btn);
        mBtnProfile.setOnClickListener(this);

        ImageButton mBtnMain = findViewById(R.id.main_btn);
        mBtnMain.setOnClickListener(this);

        TextView promo = findViewById(R.id.textView3);

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        promo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text;
                text = promo.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                clipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                return true;
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
