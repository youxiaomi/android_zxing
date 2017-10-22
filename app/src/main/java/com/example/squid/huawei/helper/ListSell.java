package com.example.squid.huawei.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.squid.huawei.R;
import com.example.squid.huawei.SellActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by squid on 17/10/12.
 */


public class ListSell extends SellActivity {

  private LinearLayout ParentNode ;
  private JSONObject production ;

  Activity CurrentActivity;

  TextView totalPriceView,totalQualityView;
  public double totalPrice = 0;
  public int totalQuality = 0;

  public JSONArray productionlist = new JSONArray();

  public ListSell(final LinearLayout ParentNode, final Activity CurrentActivity){
    this.ParentNode = ParentNode;
    this.CurrentActivity =  CurrentActivity;

  }
  public void addView(final JSONObject production){
    this.production =  production;

    LinearLayout.LayoutParams layParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT);
    try {
      final LinearLayout WrapLayout = new LinearLayout(CurrentActivity);

      final String name = production.get("productName").toString();
      final double price = Float.parseFloat(production.get("salePrice").toString());
      final String barcode = production.get("barcode").toString();


      Button delBtn = new Button(CurrentActivity);
      delBtn.setText("删除");
      delBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          AlertDialog dialog = new AlertDialog.Builder(CurrentActivity)
            .setTitle("提示")
            .setMessage("确认删除"+ name)
            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                totalPrice -= price;
                --totalQuality;
                for (int i = 0; i < productionlist.length(); i++) {
                  try {
                    if(productionlist.get(i).equals(barcode)){
                      productionlist.remove(i);
                      break;
                    }
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                }

                updateTotal();
                ParentNode.removeView(WrapLayout);
              }
            })
            .show();
        }
      });
      ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
        150,
        ViewGroup.LayoutParams.WRAP_CONTENT);

      delBtn.setLayoutParams(new LinearLayout.LayoutParams(
        50,
        ViewGroup.LayoutParams.WRAP_CONTENT));
//      delBtn.setWidth(10);
      delBtn.setTextSize(10);
      delBtn.setPadding(0,0,0,0);

      TextView productName = new TextView(CurrentActivity);
      productName.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT,1f));
      productName.setText(production.get("productName").toString());
      productName.setWidth(0);
      WrapLayout.addView(productName);


      TextView weight = new TextView(CurrentActivity);
      weight.setLayoutParams(params);
      weight.setGravity(Gravity.CENTER);
      weight.setText(production.get("weight").toString()+"g");
      WrapLayout.addView(weight);

      TextView addStockNum = new TextView(CurrentActivity);
      addStockNum.setLayoutParams(new LinearLayout.LayoutParams(
        60,
        ViewGroup.LayoutParams.WRAP_CONTENT));
      addStockNum.setText(production.get("addStockNum").toString());
      addStockNum.setGravity(Gravity.CENTER);
      WrapLayout.addView(addStockNum);


      TextView taste = new TextView(CurrentActivity);
      taste.setLayoutParams(params);
      taste.setGravity(Gravity.CENTER);
//      taste.setWidth(100);
      taste.setText(production.get("taste").toString());
      WrapLayout.addView(taste);

      TextView salePrice = new TextView(CurrentActivity);
      salePrice.setLayoutParams(params);
      salePrice.setGravity(Gravity.CENTER);

//      salePrice.setWidth(20);
      salePrice.setText(production.get("salePrice").toString()+"元");
      WrapLayout.addView(salePrice);


      totalPrice+= Float.parseFloat(production.get("salePrice").toString());
      ++totalQuality;
      productionlist.put(production.get("barcode"));
      updateTotal();

      WrapLayout.addView(delBtn);

      ParentNode.addView(WrapLayout, layParams);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void updateTotal(){
    totalPriceView = (TextView) CurrentActivity.findViewById(R.id.totalPrice);
    totalQualityView = (TextView) CurrentActivity.findViewById(R.id.totalQuality);

    DecimalFormat decimalFormat=new DecimalFormat(".00");

    totalPriceView.setText(String.valueOf(decimalFormat.format(totalPrice)));
    totalQualityView.setText(String.valueOf(totalQuality));

  }
}
