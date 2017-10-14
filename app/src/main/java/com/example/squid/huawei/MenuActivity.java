package com.example.squid.huawei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    final Intent importProductIntent  = new Intent(this, ImportProductActivity.class);;
    final Intent sellIntent = new Intent(this, SellActivity.class);
    final Intent productsIntent = new Intent(this, ProductionsActivity.class);

    final Button importProduct = (Button) findViewById(R.id.importProduct);
    final Button sell = (Button) findViewById(R.id.sell);
    final Button products = (Button) findViewById(R.id.products);
    importProduct.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(importProductIntent);
      }
    });
    sell.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        startActivity(sellIntent);
      }
    });
    products.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(productsIntent);
      }
    });
  }

}
