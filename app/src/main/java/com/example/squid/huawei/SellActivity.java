package com.example.squid.huawei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class SellActivity extends AppCompatActivity {
//    private static final String TAG = MainActivity.class.getSimpleName();
//    private DecoratedBarcodeView barcodeView;
//    private BeepManager beepManager;
//    private String lastText;
//
//    long IntervalTime = System.currentTimeMillis() / 1000;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
//
//        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
//        barcodeView.decodeContinuous(callback);
//
//        beepManager = new BeepManager(this);
    }

//    private BarcodeCallback callback = new BarcodeCallback() {
//        @Override
//        public void barcodeResult(BarcodeResult result) {
////            if(result.getText() == null || result.getText().equals(lastText)) {
////                // Prevent duplicate scans
////                return;
////            }
//            if(result.getText() == null || System.currentTimeMillis() /1000 - IntervalTime < 2 ) {
//                // Prevent duplicate scans
//                return;
//            }
//            IntervalTime = System.currentTimeMillis() / 1000;
//            lastText = result.getText();
//            beepManager.playBeepSoundAndVibrate();
//
//            //Added preview of scanned barcode
//            TextView textView = (TextView) findViewById(R.id.ProductNum);
//            textView.setText(lastText);
////            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));
//        }
//
//        @Override
//        public void possibleResultPoints(List<ResultPoint> resultPoints) {
//        }
//    };
//
//    @Override
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        barcodeView.resume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        barcodeView.pause();
//    }
//
//    public void pause(View view) {
//        barcodeView.pause();
//    }
//
//    public void resume(View view) {
//        barcodeView.resume();
//    }
//
//    public void triggerScan(View view) {
//        barcodeView.decodeSingle(callback);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
//    }
//
//
//    public void scanBarcode(View view) {
//
//        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
//        barcodeView.decodeContinuous(callback);
//
//        beepManager = new BeepManager(this);
//    }
//
//
//
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
}
