package com.example.squid.huawei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.squid.huawei.httpCookies.httpCookies;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);


    Button loginBtn = (Button) findViewById(R.id.loginBtn);

    final EditText account = (EditText) findViewById(R.id.account);
    final EditText password = (EditText) findViewById(R.id.password);

    final Intent muneIntent = new Intent(this, MenuActivity.class);
    loginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String parmas = "?account="+account.getText()+"&password="+password.getText();
        httpCookies loginHttp = new httpCookies(getString(R.string.request_url)+"/login"+parmas, getSharedPreferences("session", 0));
        loginHttp.Http();
        try {
          JSONObject loginStatus = new JSONObject(loginHttp.body);
          if(loginStatus.has("loginStatus")){
            AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
              .setTitle("错误信息")
              .setMessage(loginStatus.get("loginStatus").toString())
              .show();
          }
          else{
            startActivity(muneIntent);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    });

//    WebView myWebView = (WebView) findViewById(R.id.webview);
//    myWebView.loadUrl("http://www.qq.com");
//    WebSettings webSettings = myWebView.getSettings();
//    webSettings.setJavaScriptEnabled(true);
//    myWebView.setWebViewClient(new WebViewClient());

  }
}
