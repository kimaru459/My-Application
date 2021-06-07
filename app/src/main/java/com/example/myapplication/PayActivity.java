package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PayActivity  extends Activity {
    EditText SAccNumber;
    EditText RAccNumber;
    EditText price;
    EditText productName;
    EditText pay, sendMoney, save, invest, borrow;
    CheckBox Pcheck, Scheck, Sacheck, Icheck, Bcheck;
    Button proceed;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        SAccNumber = findViewById(R.id.SAccNumber);
        RAccNumber = findViewById(R.id.RAccNumber);
        price = findViewById(R.id.price);
        productName = findViewById(R.id.productName);
        pay = findViewById(R.id.pay);
        sendMoney = findViewById(R.id.sendMoney);
        save = findViewById(R.id.save);
        invest = findViewById(R.id.invest);
        borrow = findViewById(R.id.borrow);
        Pcheck = findViewById(R.id.Pcheck);
        Scheck = findViewById(R.id.Scheck);
        Sacheck = findViewById(R.id.Sacheck);
        Icheck = findViewById(R.id.Icheck);
        Bcheck = findViewById(R.id.Bcheck);
        proceed = findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PhoneActivity = new Intent(PayActivity.this, PhoneActivity.class);
                startActivity(PhoneActivity);


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("payments");

                //Get all values

                SAccNumber.getText().toString();
                String SAccNumber = new String();
                RAccNumber.getText().toString();
                String RAccNumber = new String();
                price.getText().toString();
                String price = new String();


                PaymentsHelper helperClass = new PaymentsHelper(SAccNumber, RAccNumber, price);
                reference.child(price).setValue(helperClass);


            }
        });



    }

}
