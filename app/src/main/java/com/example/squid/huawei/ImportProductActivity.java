package com.example.squid.huawei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.squid.huawei.httpCookies.httpCookies;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

import static android.R.attr.data;

public class ImportProductActivity extends AppCompatActivity {

  private EditText barcode ;
  private EditText productName ;
  private EditText salePrice ;
  private EditText costPrice;
  private EditText weight ;
  private EditText taste ;
  private EditText addStockNum;
  private EditText factory ;
  private EditText remainStock ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    setContentView(R.layout.activity_import_product);

    Button save = (Button) findViewById(R.id.save);
    barcode = (EditText) findViewById(R.id.barcode);
    productName = (EditText) findViewById(R.id.productName);
     salePrice = (EditText) findViewById(R.id.salePrice);
     costPrice = (EditText) findViewById(R.id.costPrice);
     weight = (EditText) findViewById(R.id.weight);
     taste = (EditText) findViewById(R.id.taste);
     addStockNum = (EditText) findViewById(R.id.addStockNum);
     factory = (EditText) findViewById(R.id.factory);
    remainStock = (EditText) findViewById(R.id.remainStock);
    final httpCookies http = new httpCookies(getString(R.string.request_url)+"/new_production", getSharedPreferences("session", 0));

    final Button scanBarcode = (Button) findViewById(R.id.scanBarcode);


    scanBarcode.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new IntentIntegrator(ImportProductActivity.this).initiateScan(); // `this` is the current Activity
      }
    });


    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(barcode.getText().toString().equals("") || productName.getText().toString().equals("")){
          return;
        }
        final JSONObject productInfo = new JSONObject();
        try{
          productInfo.put("barcode",barcode.getText());
          productInfo.put("productName",productName.getText());
          productInfo.put("salePrice", Double.parseDouble(salePrice.getText().toString()));
          productInfo.put("costPrice", Double.parseDouble(costPrice.getText().toString()));
          productInfo.put("weight",weight.getText());
          productInfo.put("taste",taste.getText());
          productInfo.put("addStockNum", Integer.parseInt(addStockNum.getText().toString()));
          productInfo.put("factory",factory.getText());
        }catch (JSONException e){
          e.printStackTrace();
        }
        http.setMethod("POST");
        http.setSetBody("productInfo="+productInfo.toString());
        http.Http();
        try {
          JSONObject body = new JSONObject(http.body);
          if((Boolean) body.get("productStatus") == true){
            AlertDialog dialog = new AlertDialog.Builder(ImportProductActivity.this)
              .setTitle("提示")
              .setMessage("录入成功")
              .show();
            barcode.setText("");
            productName.setText("");
            salePrice.setText("");
            costPrice.setText("");
            weight.setText("");
            taste.setText("");
            remainStock.setText("0");
            addStockNum.setText("0");
            factory.setText("");
          }else{AlertDialog dialog = new AlertDialog.Builder(ImportProductActivity.this)
            .setTitle("提示")
            .setMessage("录入失败失败")
            .show();
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if(result != null) {
      if(result.getContents() == null) {

      } else {
        final httpCookies queryOne = new httpCookies(getString(R.string.request_url)+"/production?barcode="
          +result.getContents(), getSharedPreferences("session", 0));
        queryOne.Http();
        try {

          if(queryOne.body == null){
            barcode.setText(result.getContents());
            productName.setText("");
            salePrice.setText("");
            costPrice.setText("");
            weight.setText("");
            taste.setText("");
            remainStock.setText("0");
            addStockNum.setText("0");
            factory.setText("");
          }else{
            JSONObject body = new JSONObject(queryOne.body);
            barcode.setText(body.get("barcode").toString());
            productName.setText(body.get("productName").toString());
            salePrice.setText(body.get("salePrice").toString());
            costPrice.setText(body.get("costPrice").toString());
            weight.setText(body.get("weight").toString());
            taste.setText(body.get("taste").toString());
            remainStock.setText(body.get("addStockNum").toString());
            factory.setText(body.get("factory").toString());
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
