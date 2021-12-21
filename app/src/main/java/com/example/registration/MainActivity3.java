package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final Button CongestionButton = findViewById(R.id.CongestionButton);
        final Button PeriodButton = findViewById(R.id.PeriodButton);
        final Button CategoryButton = findViewById(R.id.CategoryButton);
        final Button ChestButton = findViewById(R.id.imageButton3);

        if (userID == null) {
            userID = MainActivity.userID;
        }

        CongestionButton.setEnabled(true);
        PeriodButton.setEnabled(true);
        CategoryButton.setEnabled(false);

        ChestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChestIntent = new Intent(MainActivity3.this, Chest.class);
                ChestIntent.putExtra("userID",userID);
                MainActivity3.this.startActivity(ChestIntent);
                finish();
            }
        });


        CongestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent로 메인액티비티로 넘겨줌
                Intent CongestionIntent = new Intent(MainActivity3.this, MainActivity.class);
                CongestionIntent.putExtra("userID",userID);
                MainActivity3.this.startActivity(CongestionIntent);
                finish();
            }
        });

        PeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent로 메인액티비티로 넘겨줌
                Intent CategoryIntent = new Intent(MainActivity3.this, MainActivity2.class);
                CategoryIntent.putExtra("userID",userID);
                MainActivity3.this.startActivity(CategoryIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent backintent = new Intent(MainActivity3.this, MainActivity.class);
        backintent.putExtra("userID",userID);
        MainActivity3.this.startActivity(backintent);
        finish();
    }

}

