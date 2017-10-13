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
  JSONArray testJson;
  public ListProduction(final LinearLayout ParentNode, final Activity CurrentActivity){
    this.ParentNode = ParentNode;
    String testString = "[{'barcode':'123345','name':'薯片'},{'barcode':'12333333','name':'水'}]";
    try {
      this.testJson = new JSONArray(testString);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    LinearLayout.LayoutParams layParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT);

    for( int i = 0;i <testJson.length();i ++){
      final JSONObject currentJson;
      try {
        currentJson = testJson.getJSONObject(i);
        final String currentName = currentJson.get("name").toString();
        String currentBarCode = currentJson.get("barcode").toString();

        final LinearLayout WrapLayout = new LinearLayout(CurrentActivity);
        Button testBtn = new Button(CurrentActivity);
        testBtn.setText("删除");
        testBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            AlertDialog dialog = new AlertDialog.Builder(CurrentActivity)
              .setTitle("提示")
              .setMessage("确认删除"+currentName.toString())
              .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  ParentNode.removeView(WrapLayout);
                }
              })
              .show();
          }
        });

        TextView barcode = new TextView(CurrentActivity);
        barcode.setText(currentBarCode.toString());

        WrapLayout.addView(barcode);
        WrapLayout.addView(testBtn);

        ParentNode.addView(WrapLayout, layParams);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }
}
