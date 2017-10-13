package com.example.squid.huawei.httpCookies;

import android.content.SharedPreferences;

import com.example.squid.huawei.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by squid on 17/10/2.
 */

public class httpCookies  {
  String url;
  SharedPreferences sp;
  HttpURLConnection conn;
  public String body;
  private String method  =  "GET";
  private boolean requestComplete = false;

  public boolean isRequestComplete() {
    return requestComplete;
  }

  public void setRequestComplete(boolean requestComplete) {
    this.requestComplete = requestComplete;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getSetBody() {
    return SetBody;
  }

  public void setSetBody(String setBody) {
    SetBody = setBody;
  }

  private String SetBody = "";

  public httpCookies(String url, SharedPreferences sp) {
    this.url = url;
    this.sp = sp;
  }

  public httpCookies(String url, SharedPreferences sp, String Method, String SetBody){
    this.url = url;
    this.sp = sp;
    this.method = Method;
    this.SetBody = SetBody;
  }
  public void Http(){
    Thread t = new Thread() {
      @Override
      public void run() {
        super.run();
        try {
          final String path = url;
          URL url = new URL(path);
          conn = (HttpURLConnection) url.openConnection();
          SetCookie();
          if (conn.getResponseCode() == 200) {
            GetCookie();
            body = GetBody();
          }
        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };
    t.start();
    try{
      t.join();
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
  public void SetCookie(){
    String session_s = sp.getString("session","123;123");
    String session_g = sp.getString("session_sign","123;123");
    conn.setRequestProperty("Cookie", session_s.substring(0, session_s.indexOf(";"))+";"+
      session_g.substring(0, session_g.indexOf(";")));

    try {
      conn.setRequestMethod(method);
      if(method == "POST"){
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        out.write(SetBody.getBytes());
      }
      conn.setReadTimeout(10000);
      conn.setReadTimeout(10000);
    }catch (ProtocolException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }
  }
  public void GetCookie(){
    Map<String, List<String>> ck = conn.getHeaderFields();
    List<String> cookies = ck.get("Set-Cookie");
    if(cookies != null){
      SharedPreferences.Editor spe =sp.edit();
      spe.putString("session", cookies.get(0));
      spe.putString("session_sign", cookies.get(1));
      spe.commit();
    }
  }
  public String GetBody(){
    String line = null;
    try{
      InputStream is = conn.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      line = br.readLine();
    }catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return line;
  }
}
