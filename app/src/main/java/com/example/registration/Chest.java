package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Chest extends AppCompatActivity {
String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);

        if (userID == null) {
            userID = MainActivity.userID;
        }

        ListView listView = findViewById(R.id.listview);

        SingleAdapter adapter = new Chest.SingleAdapter();

        adapter.addItem(new SingleItem("Level1. (인클라인)체스트 프레스 머신", "12~15회/4세트/휴식시간/60~90초", R.drawable.chestpressmachine));
        adapter.addItem(new SingleItem("Level2.1 (인클라인)벤치 프레스 - 저중량", "12~15회/4세트/휴식시간/60~90초", R.drawable.inclinebenchpress));
        adapter.addItem(new SingleItem("Level2.2 (인클라인)덤벨 프레스 - 저중량", "12~15회/4세트/휴식시간/60~90초", R.drawable.dumbelpress));
        adapter.addItem(new SingleItem("Level3 머신 딥 & 덤벨 플라이", "각 12~15회/4세트/휴식시간/60~90초", R.drawable.deeps));
        adapter.addItem(new SingleItem("Level4.1 벤치 프레스 - 고중량 ", "10~15회/5세트/휴식시간/90~120초", R.drawable.benchpress));
        adapter.addItem(new SingleItem("Level4.2 케이블 크로스 오버", "15~20회/4세트/휴식시간/90~120초", R.drawable.cablecross));
        adapter.addItem(new SingleItem("Level5 팩덱 플라이 머신 - 고중량 ", "12~20회/3~4세트/휴식시간/90~120초", R.drawable.packdeck));

        listView.setAdapter(adapter);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chestintent = new Intent(getApplicationContext(), ChestActivity.class);
                // putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값
                chestintent.putExtra("profile", Integer.toString(data.get(position).getProfile()));
                chestintent.putExtra("info", data.get(position).getInfo());
                chestintent.putExtra("phone", data.get(position).getPhone());
                startActivity(intent);
            }
        });*/
    }


    @Override
    public void onBackPressed() {
        Intent backintent = new Intent(Chest.this, MainActivity3.class);
        backintent.putExtra("userID",userID);
        Chest.this.startActivity(backintent);
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

