package com.example.squid.huawei;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

//    WebView myWebView = (WebView) findViewById(R.id.webview);
//    myWebView.loadUrl("http://www.qq.com");
//    WebSettings webSettings = myWebView.getSettings();
//    webSettings.setJavaScriptEnabled(true);
//    myWebView.setWebViewClient(new WebViewClient());


    Thread t = new Thread() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          final String path = getString(R.string.request_url);
          URL url = new URL(path);
          HttpURLConnection conn = (HttpURLConnection) url
            .openConnection();

          SharedPreferences sp = getSharedPreferences("session", 0);
          String session_s = sp.getString("session","");
          conn.setRequestProperty("Cookie", session_s.substring(0, session_s.indexOf(";")));
          conn.setReadTimeout(5000);
          conn.setReadTimeout(5000);
          if (conn.getResponseCode() == 200) {
            Map<String, List<String>> ck = conn.getHeaderFields();

            List<String> cookies = ck.get("Set-Cookie");
            SharedPreferences.Editor spe =sp.edit();
            spe.putString("session", cookies.get(0));
            spe.commit();



            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();

            System.out.println(line.toString());

            String aa = "222";
          }
        } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        super.run();
      }

    };
    t.start();
  }
}
