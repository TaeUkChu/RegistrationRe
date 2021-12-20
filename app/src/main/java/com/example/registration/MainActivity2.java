package com.example.registration;

import static java.net.URLEncoder.encode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public static String userID ;
    public static String userperiod;
    private AlertDialog dialog; // 알림창

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userID = getIntent().getStringExtra("userID");
        userperiod = getIntent().getStringExtra("userPeriod");
        ProgressBar progressbar = findViewById(R.id.progressbar);
        //final Button LogoutButton = findViewById(R.id.logoutbutton);
        final Button CongestionButton = findViewById(R.id.CongestionButton);
        final Button PeriodButton = findViewById(R.id.PeriodButton);
        final Button CategoryButton = findViewById(R.id.CategoryButton);
        //final Button testbutton = findViewById(R.id.testbutton);

        CongestionButton.setEnabled(true);
        PeriodButton.setEnabled(false);
        CategoryButton.setEnabled(true);

        CongestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent로 메인액티비티로 넘겨줌
                Intent CongestionIntent = new Intent(MainActivity2.this, MainActivity.class);
                CongestionIntent.putExtra("userID",userID);
                MainActivity2.this.startActivity(CongestionIntent);
                finish();
            }
        });


        CategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent로 메인액티비티로 넘겨줌
                Intent CategoryIntent = new Intent(MainActivity2.this, MainActivity3.class);
                CategoryIntent.putExtra("userID",userID);
                MainActivity2.this.startActivity(CategoryIntent);
                finish();
            }
        });




        /*
        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {   //해당 결과 받아옴
                            TextView startText = findViewById(R.id.registerDay);
                            TextView finishText = findViewById(R.id.expirationDay);
                            TextView remainText = findViewById(R.id.remainDay);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("response");
                            JSONObject object = jsonArray.getJSONObject(0);
                            String userRegisterday = object.getString("userRegisterday");
                            String userExpirationday = object.getString("userExpirationday");
                            String remainingPeriod = object.getString("remainingPeriod");
                            startText.setText(userRegisterday);
                            finishText.setText(userExpirationday);
                            remainText.setText(remainingPeriod);
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                            dialog = builder.setMessage("테스트.")
                                    .setPositiveButton("확인", null)
                                    .create();
                            dialog.show();  //다이얼로그 실행


                        } catch (Exception e)  //예외처리
                        {
                            e.printStackTrace();
                        }
                    }
                };
                // EntranceRequest 클래스의 queue 형태로 DB에 전달
                PeriodRequest periodRequest = new PeriodRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);  //큐에 담음
                queue.add(periodRequest);
            }
        });
*/
        new BackgroundTask2().execute();
}

class BackgroundTask2 extends AsyncTask<Void, Void, String>
{
    String target;
    protected void onPreExecute(){

        try {
            if (userID == null){
                userID = MainActivity.userID;
            }
            target = "http://musclejava.cafe24.com/UserPeriod.php?userID=" + encode(MainActivity.userID,"UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //target = "http://musclejava.cafe24.com/UserPeriod.php?userID=1" ;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try{
            URL url = new URL(target);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            while((temp = bufferedReader.readLine()) != null){
                stringBuilder.append(temp+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onProgressUpdate(Void... values) {
        super.onProgressUpdate();
    }

    @Override
    public void onPostExecute(String result){
        try{
            TextView startText = findViewById(R.id.registerDay);
            TextView finishText = findViewById(R.id.expirationDay);
            TextView remainText = findViewById(R.id.remainDay);
            ProgressBar progressBar = findViewById(R.id.progressbar);

            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            JSONObject object = jsonArray.getJSONObject(0);

            String userRegisterday = object.getString("userRegisterday");
            String userExpirationday = object.getString("userExpirationday");
            String userPeriod = object.getString("userPeriod");
            String remainingPeriod = object.getString("remainingPeriod");
            float remainpercent = (float)(Integer.parseInt(remainingPeriod) / Integer.parseInt(userPeriod));

            startText.setText("등록 날짜: " + userRegisterday);
            finishText.setText("만료 날짜: "+ userExpirationday);
            remainText.setText("남은 기간: "+ remainingPeriod +"\n" + remainpercent +  "(%)");

            progressBar.setProgress(Integer.parseInt(remainingPeriod));
            progressBar.setMax(Integer.parseInt(userPeriod));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}


    @Override
    public void onBackPressed() {
        Intent backintent = new Intent(MainActivity2.this, MainActivity.class);
        backintent.putExtra("userID",userID);
        MainActivity2.this.startActivity(backintent);
        finish();
    }
/* 뒤로가기 테스트
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setMessage("종료?");
        builder.setPositiveButton("아니오",(dialog,which) -> {dialog.cancel();});
        builder.setNegativeButton("예",(dialog,which) -> {finish();});
        builder.show();

    }*/
}