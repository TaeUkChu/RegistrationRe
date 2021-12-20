package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

        if (userID == null) {
            userID = MainActivity.userID;
        }

        CongestionButton.setEnabled(true);
        PeriodButton.setEnabled(true);
        CategoryButton.setEnabled(false);

        ListView listView = findViewById(R.id.listview);

        SingleAdapter adapter = new SingleAdapter();
        adapter.addItem(new SingleItem("blackFish", "010-1000-1000", R.drawable.refact_layout));
        adapter.addItem(new SingleItem("whiteFish", "010-1000-1001", R.drawable.ic_baseline_fitness_center_24));
        adapter.addItem(new SingleItem("whiteGroup", "010-1000-1002", R.drawable.ic_baseline_fitness_center_24));
        adapter.addItem(new SingleItem("blackGroup", "010-1000-1003", R.drawable.ic_baseline_fitness_center_24));
        adapter.addItem(new SingleItem("blackFish", "010-1000-1000", R.drawable.ic_baseline_fitness_center_24));
        adapter.addItem(new SingleItem("whiteFish", "010-1000-1001", R.drawable.ic_baseline_fitness_center_24));
        adapter.addItem(new SingleItem("whiteGroup", "010-1000-1002", R.drawable.ic_baseline_fitness_center_24));
        adapter.addItem(new SingleItem("blackGroup", "010-1000-1003", R.drawable.ic_baseline_fitness_center_24));
        listView.setAdapter(adapter);

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

    class SingleAdapter extends BaseAdapter {
        //데이터가 들어가있지 않고, 어떻게 담을지만 정의해뒀다.
        ArrayList<SingleItem> items = new ArrayList<SingleItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingleItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 어댑터가 데이터를 관리하고 뷰도 만듬
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingleItemView  singleItemView = null;
            // 코드를 재사용할 수 있도록
            if (convertView == null) {
                singleItemView = new SingleItemView(getApplicationContext());
            } else {
                singleItemView = (SingleItemView) convertView;
            }
            SingleItem item = items.get(position);
            singleItemView.setName(item.getName());
            singleItemView.setMobile(item.getMobile());
            singleItemView.setImage(item.getResId());
            return  singleItemView;
        }

    }

}

