package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.myapplication.UserHelperClass;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends Activity {

        EditText username;
        EditText email;
        EditText phonenumber;
        EditText password;
        Button signup;

        FirebaseDatabase rootNode;
        DatabaseReference reference;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);

            username= findViewById(R.id.username);
            email = findViewById(R.id.email);
            phonenumber = findViewById(R.id.phonenumber);
            password = findViewById(R.id.password);
            signup = findViewById(R.id.signup);

            signup.setOnClickListener(view -> {
                Intent LoginActivity = new Intent(RegisterActivity.this, com.example.myapplication.LoginActivity.class);
                startActivity(LoginActivity);
                checkDataEntered();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

               //Get all values
               username.getText().toString();
                String username = new String();
                email.getText().toString();
                String email = new String();
                phonenumber.getText().toString();
                String phonenumber = new String();
                password.getText().toString();
                String password= new String();

                UserHelperClass helperClass = new UserHelperClass(username, email, phonenumber, password);
                reference.child(phonenumber).setValue(helperClass);

                Toast.makeText(RegisterActivity.this, "Your account has been successfully created!", Toast.LENGTH_SHORT).show();
            });
        }

        boolean isEmail(EditText text) {
            CharSequence email = text.getText().toString();
            return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        }

        boolean isEmpty(EditText text) {
            CharSequence str = text.getText().toString();
            return TextUtils.isEmpty(str);
        }

        void checkDataEntered() {
            if (isEmpty(username)) {
                Toast t = Toast.makeText(this, "You must enter username to sign up!", Toast.LENGTH_SHORT);
                t.show();
            }

            if (!isEmail(email)) {
                email.setError("Enter valid email!");
            }

        }

    }






