package com.example.squid.huawei;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ImportProductActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_import_product);

    Button save = (Button) findViewById(R.id.save);
    final EditText productName = (EditText) findViewById(R.id.productName);
    final EditText salePrice = (EditText) findViewById(R.id.salePrice);
    final EditText costPrice = (EditText) findViewById(R.id.costPrice);
    final EditText weight = (EditText) findViewById(R.id.weight);
    final EditText taste = (EditText) findViewById(R.id.taste);
    final EditText addStockNum = (EditText) findViewById(R.id.addStockNum);
    final EditText factory = (EditText) findViewById(R.id.factory);

    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final JSONObject productInfo = new JSONObject();
        try{
          productInfo.put("productName",productName.getText());
          productInfo.put("salePrice",salePrice.getText());
          productInfo.put("costPrice",costPrice.getText());
          productInfo.put("weight",weight.getText());
          productInfo.put("taste",taste.getText());
          productInfo.put("addStockNum",addStockNum.getText());
          productInfo.put("factory",factory.getText());
        }catch (JSONException e){
          e.printStackTrace();
        }


        Thread t = new Thread() {
          @Override
          public void run() {
            try {
              String path = getString(R.string.request_url);
              URL url = new URL(path);
              HttpURLConnection conn = (HttpURLConnection) url
                .openConnection();

              SharedPreferences sp = getSharedPreferences("session", 0);
              String session_s = sp.getString("session","");
              conn.setRequestProperty("Cookie", session_s.substring(0, session_s.indexOf(";")));
              conn.setRequestMethod("POST");

              conn.setDoOutput(true);
              conn.setReadTimeout(5000);
              conn.setReadTimeout(5000);

              OutputStream out = new BufferedOutputStream(conn.getOutputStream());
              String test = "阿斯蒂芬";
//              out.out(test.getBytes());

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

        String aa ="123";
      }
    });
  }
}
