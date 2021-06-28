package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;


public class RegisterActivity extends Activity implements View.OnClickListener {

        EditText username,email,phonenumber,password;
        Button signup;
        String s_username,s_email,s_phonenumber,s_password;
        FirebaseDatabase rootNode;
        DatabaseReference reference;

        private ProgressDialog progressDialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);

            username= findViewById(R.id.username);
            email = findViewById(R.id.email);
            phonenumber = findViewById(R.id.phonenumber);
            password = findViewById(R.id.password);
            signup = findViewById(R.id.signup);

            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            signup.setOnClickListener(this);
        }

        boolean isEmail(EditText text) {
            CharSequence email = text.getText().toString();
            return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        }

        boolean isEmpty(EditText text) {
            CharSequence str = text.getText().toString();
            return TextUtils.isEmpty(str);
        }

        private boolean checkDataEntered() {
            if (isEmpty(username)) {
                Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            }
            else {
                if (isEmpty(email)) {
                    Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!isEmail(email)) {
                        email.setError("Enter valid email!");
                    }
                    else {
                        if (isEmpty(phonenumber)) {
                            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (isEmpty(password)) {
                                Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (password.getText().toString().length() < 4) {
                                    password.setError("Password must be at least 4 characters long!");
                                }
                                else {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

    @Override
    public void onClick(View view) {
        if (view.equals(signup)){
            //Get all values
            s_username = username.getText().toString();
            s_email = email.getText().toString();
            s_phonenumber = phonenumber.getText().toString();
            s_password = password.getText().toString();

            if (checkDataEntered()){
                progressDialog.show();
                registerUser();
            }

        }
    }

    private void registerUser() {

        String key = reference.push().getKey();
        UserHelperClass dummyStudent = new UserHelperClass(
                s_username,
                s_email,
                s_phonenumber,
                s_password
        );

        reference.child(key).setValue(dummyStudent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressDialog.hide();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                    Toast.makeText(RegisterActivity.this, "Successful sign up", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.hide();
                    Toast.makeText(RegisterActivity.this, "Failed to add user", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}






