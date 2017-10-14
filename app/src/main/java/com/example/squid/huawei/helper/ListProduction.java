package com.example.squid.huawei.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.squid.huawei.MainActivity;
import com.example.squid.huawei.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by squid on 17/10/5.
 */


public class ListProduction extends AppCompatActivity {


  private LinearLayout ParentNode ;
  JSONArray products;
  public ListProduction(final LinearLayout ParentNode, final Activity CurrentActivity, JSONArray products){
    this.ParentNode = ParentNode;
    this.products = products;


    ViewGroup.LayoutParams layParams = new LinearLayout.LayoutParams(
      ViewGroup.LayoutParams.WRAP_CONTENT,
      ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);

    for( int i = 0;i <products.length();i ++){
      final JSONObject currentJson;
      try {
        currentJson = products.getJSONObject(i);
        final LinearLayout WrapLayout = new LinearLayout(CurrentActivity);

        final String productName = currentJson.get("productName").toString();
        String barcode = currentJson.get("barcode").toString();
        String salePrice = currentJson.get("salePrice").toString();
        String weight = currentJson.get("weight").toString();
        String addStockNum = currentJson.get("addStockNum").toString();

        TextView productNameView = new TextView(CurrentActivity);
        productNameView.setText(productName.toString());
        productNameView.setLayoutParams(layParams);;
        WrapLayout.addView(productNameView);

//        TextView barcodeView = new TextView(CurrentActivity);
//        barcodeView.setText(barcode.toString());
//        barcodeView.setWidth(200);
//        WrapLayout.addView(barcodeView);

        TextView sellPriceView = new TextView(CurrentActivity);
        sellPriceView.setText(salePrice.toString());
        sellPriceView.setLayoutParams(layParams);;
        WrapLayout.addView(sellPriceView);

//        TextView weightView = new TextView(CurrentActivity);
//        weightView.setText(weight.toString());
//        weightView.setWidth(10);
//        WrapLayout.addView(weightView);

        TextView addStockNumView = new TextView(CurrentActivity);
        addStockNumView.setText(addStockNum.toString());
        addStockNumView.setLayoutParams(layParams);;
        WrapLayout.addView(addStockNumView);



//        Button delBtn = new Button(CurrentActivity);
//        delBtn.setText("删除");
//        delBtn.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            AlertDialog dialog = new AlertDialog.Builder(CurrentActivity)
//              .setTitle("提示")
//              .setMessage("确认删除"+productName.toString())
//              .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                  ParentNode.removeView(WrapLayout);
//                }
//              })
//              .show();
//          }
//        });

//        WrapLayout.addView(delBtn);
        ParentNode.addView(WrapLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }
}
