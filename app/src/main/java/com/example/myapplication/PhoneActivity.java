package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PhoneActivity extends Activity {
    ImageView imageView;
    EditText phoneNo;
    Button button;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);

        imageView = findViewById(R.id.imageView);
        phoneNo = findViewById(R.id.phoneNo);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent OTPActivity = new Intent(PhoneActivity.this, OTPActivity.class);
                startActivity(OTPActivity);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("phone");

                //Get all values

                phoneNo.getText().toString();
                String phoneNo = new String();

                Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                intent.putExtra("phoneNo",phoneNo);
                startActivity(intent);




                PhoneHelper helperClass = new PhoneHelper(phoneNo);
                reference.child(phoneNo).setValue(helperClass);


            }

        });
    }

}