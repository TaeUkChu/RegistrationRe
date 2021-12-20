package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {


    private Context mContext;
    private CheckBox cb_save;
    String id, pw;
    private AlertDialog dialog ; // 알림창
    private Thread loginThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton= (Button) findViewById(R.id.loginButton);
        TextView registerButton = (TextView) findViewById(R.id.registerButton);

        cb_save = (CheckBox) findViewById(R.id.cb_save);
        mContext = this;

        //boo로 로그인 정보 체크 박스 유무
        final boolean boo = PreferenceManager.getBoolean(mContext,"check");

        if(boo){
            idText.setText(PreferenceManager.getString(mContext,"id"));
            passwordText.setText(PreferenceManager.getString(mContext,"pw"));
            cb_save.setChecked(true);
        }

/*      자동 로그인 쓰레드
        loginThread = new Thread(){

            @Override
            public void run() {

                if(idText != null && passwordText != null) {
                    LoginActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LoginActivity.this, "자동로그인완료", Toast.LENGTH_SHORT).show();
                    }
                });
                      Intent loginintent =new Intent(LoginActivity.this,MainActivity.class);
                      startActivity(loginintent);
                      finish();
                }
            }
        };
        loginThread.start();
*/
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        //Text 라이브러리 + 버튼 라이브러리 등록

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                //결과 출력
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {   //해당 결과 받아옴
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){  //로그인에 성공하면
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인에 성공했습니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();  //다이얼로그 실행
                                //Intent로 메인액티비티로 넘겨줌
                                Intent Loginintent = new Intent(LoginActivity.this, MainActivity.class);
                                //(개별추가)

                                PreferenceManager.setString(mContext,"id", idText.getText().toString());
                                PreferenceManager.setString(mContext, "pw", passwordText.getText().toString());

                                Loginintent.putExtra("userID",userID);

                                String checkId = PreferenceManager.getString(mContext, "id");
                                String checkpw = PreferenceManager.getString(mContext, "pw");

                                if(TextUtils.isEmpty(checkId) || TextUtils.isEmpty(checkpw)){
                                }
                                else{
                                    Loginintent.putExtra("id",checkId);
                                    Loginintent.putExtra("pw",checkpw);
                                }
                                LoginActivity.this.startActivity(Loginintent);
                                finish();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인에 실패했습니다. 계정을 다시 확인하세요")
                                        .setNegativeButton("다시 시도",null)
                                        .create();
                                passwordText.setText("");
                                dialog.show();  //다이얼로그 실행
                            }
                        }
                        catch (Exception e)  //예외처리
                        {
                            e.printStackTrace();

                        }
                    }
                };

                //실제 로그인 요청 --> 결과 : Response --> Json 전달
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);  //큐에 담음
                queue.add(loginRequest);

            }

        });

        cb_save.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    PreferenceManager.setString(mContext, "id", idText.getText().toString());
                    PreferenceManager.setString(mContext, "pw", passwordText.getText().toString());
                    PreferenceManager.setBoolean(mContext, "check", cb_save.isChecked());
                    Toast.makeText(LoginActivity.this, "로그인 정보 저장.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "로그인 정보 초기화.", Toast.LENGTH_SHORT).show();
                    PreferenceManager.setBoolean(mContext, "check", cb_save.isChecked());
                    PreferenceManager.clear(mContext);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("종료하시겠습니까?");
        builder.setPositiveButton("예",(dialog,which) -> {dialog.cancel();});
        builder.setNegativeButton("아니오",(dialog,which) -> {finish();});
        builder.show();

    }

    }


    /*
    //딱히 필요하지 않은 부분
    @Override
    protected  void onStop(){  //현재 액티비티가 종료 되었다면
        super.onStop();
        if(dialog != null)  //다이얼로그가 켜져있을 때
        {
            dialog.dismiss();  //함부로 종료하지 않도록
            dialog = null;
        }

    }*/

