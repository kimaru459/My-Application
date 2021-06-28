package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;


public class LoginActivity extends Activity {

    EditText email;
    EditText password;
    Button login;
    TextView register;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private ValueEventListener mDBListener;

    private List<UserHelperClass> mUser;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setupUI();
        setupListeners();
    }

    private void setupUI() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    }
    private void setupListeners(){
       login.setOnClickListener(v -> {
           if (checkEmail()){
               loginUser();
           }
       });

        register.setOnClickListener(view -> {
            Intent RegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(RegisterActivity);

        });
        }

    private boolean checkEmail(){
        if (isEmpty(email)) {
            email.setError("You must enter your email to login");
        } else{
            if (!isEmail(email)){
                email.setError("Enter valid email");
            }
            else {
                if (isEmpty(password)) {
                    password.setError("You must enter password to login!");
                } else {
                    if (password.getText().toString().length() < 4) {
                        password.setError("Password must be at least 4 chars long!");
                    }
                    else {
                        progressDialog.show();
                        return true;
                    }
                }
            }
        }

        return false;
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private void loginUser(){
        final String currentUserEmail = email.getText().toString();

        Query passwordVerification = reference.orderByChild("email").equalTo(currentUserEmail);
        passwordVerification.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String userPassword;
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            userPassword = postSnapshot.child("password").getValue(String.class);
                            Log.e("PostSnapshot.", "Value is: " + userPassword);

                            assert userPassword != null;
                            if (userPassword.equals(password.getText().toString())){
                                startActivity(new Intent(LoginActivity.this,ProductsActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Invalid email/password", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.hide();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("TAG", "Failed to read value.", databaseError.toException());
                        progressDialog.hide();
                    }
                });
    }

}


