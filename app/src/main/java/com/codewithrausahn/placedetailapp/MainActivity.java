package com.codewithrausahn.placedetailapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cashfree.pg.CFPaymentService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button pay_button;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pay_button = findViewById(R.id.payment);
        listView=findViewById(R.id.listview);
        List<String> titale=new ArrayList<>();
        List<String> price=new ArrayList<>();
        List<String> quntity=new ArrayList<>();
        titale.add("CAMPUS SHOES");
        titale.add("SONATA Analog Watch");
        titale.add("Headphone Bluetooth ");
        List<Integer> image=new ArrayList<>();
        image.add(R.drawable.shoes);
        image.add(R.drawable.watch);
        image.add(R.drawable.hedphone);
        price.add("₹500");
        price.add("₹400");
        price.add("₹499");
        quntity.add("1");
        quntity.add("1");
        quntity.add("1");
        MyAddapter myAddapter =new MyAddapter(this,titale,image,quntity,price);
        listView.setAdapter(myAddapter);
        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> params = new HashMap<>();
                params.put(CFPaymentService.PARAM_APP_ID, "100045e72e2811d684fe47b2af540001");
                params.put(CFPaymentService.PARAM_ORDER_ID, "1204");
                params.put(CFPaymentService.PARAM_ORDER_AMOUNT, "1399");
                params.put(CFPaymentService.PARAM_ORDER_NOTE, "Order for Product");
                params.put(CFPaymentService.PARAM_CUSTOMER_NAME, "Raushan Kumar");
                params.put(CFPaymentService.PARAM_CUSTOMER_PHONE, "8340447787");
                params.put(CFPaymentService.PARAM_CUSTOMER_EMAIL, "raushan7117@gmail.com");
                params.put(CFPaymentService.PARAM_ORDER_CURRENCY, "INR");
                params.put(CFPaymentService.PARAM_NOTIFY_URL, "https://your.backend.webhook");

                String token = "C69JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.9KQfikDNmRWNykDM1kTNxYjI6ICdsF2cfJCLygjM1MDO1MjNxojIwhXZiwiIS5USiojI5NmblJnc1NkclRmcvJCL5kzMxojI05Wdv1WQyVGZy9mIsICNwITMiojIklkclRmcvJye.Huu-YzuUcYHLBV-6rZRte1ncB99PX2bweJ_rfCQ_V4B_i88ooHHLBjA1hmYtS1KyYO";
                CFPaymentService.getCFPaymentServiceInstance().doPayment(MainActivity.this, params, token, "TEST");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CFPaymentService.REQ_CODE && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
               showresponse(transformBundleToString(bundle));
        }

    }

    private String transformBundleToString(Bundle bundle) {
        String response = "";
        for (String  key  :  bundle.keySet()) {
            response=response.concat(String.format("%s: %s\n",key,bundle.getString(key)));
        }

     return response;
    }


    private void showresponse(String response) {
        new MaterialAlertDialogBuilder(this)
                .setMessage(response)
                .setTitle("Payment Response")
                .setPositiveButton("Ok",(dialog1,which)->{
                    dialog1.dismiss();
                }).show();

    }
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("close Alert");
        builder.setMessage("do you want exit");
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                builder.setCancelable(true);
            }
        });
        builder.show();
    }

}