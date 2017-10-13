package com.example.squid.huawei;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.example.squid.huawei.helper.ListProduction;
import com.example.squid.huawei.httpCookies.httpCookies;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

  Intent intent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    intent = new Intent(this, LoginActivity.class);

//    TableLayout TabP = (TableLayout) findViewById(R.id.relativeWarp);
//    new ListProduction(TabP, this);

    isLogin(intent);
  }


  protected void isLogin(Intent intent){
    Intent menuIntent = new Intent(this, MenuActivity.class);

    httpCookies loginStatus = new httpCookies(getString(R.string.request_url)+"/login", getSharedPreferences("session", 0));
    loginStatus.Http();
    try{
      JSONObject body = new JSONObject(loginStatus.body);
      if(body.get("status").equals("false")){
        startActivity(intent);
      }else{
        startActivity(menuIntent);
      }
    }catch (JSONException e){
      e.printStackTrace();
    }
//
//    startActivity(intent);

  }


}
