package com.example.squid.huawei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.squid.huawei.helper.ListProduction;
import com.example.squid.huawei.httpCookies.httpCookies;

import org.json.JSONArray;
import org.json.JSONException;

public class ProductionsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_productions);
    LinearLayout wrap = (LinearLayout) findViewById(R.id.wrap);
    httpCookies productions = new httpCookies(getString(R.string.request_url)+"/production/index", getSharedPreferences("session", 0));
    productions.Http();
    try {
      new ListProduction(wrap, this, new JSONArray(productions.body));
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }
}
