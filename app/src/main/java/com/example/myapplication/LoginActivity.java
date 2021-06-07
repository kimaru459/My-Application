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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;



public class LoginActivity extends Activity {

    EditText email;
    EditText password;
    Button login;
    TextView register;


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


    }
    private void setupListeners(){
       login.setOnClickListener(v -> {
           Intent ProductsActivity = new Intent(LoginActivity.this, ProductsActivity.class);
           startActivity(ProductsActivity);
           checkEmail();

       });

            register.setOnClickListener(view -> {
                Intent RegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(RegisterActivity);

            });
        }

    void checkEmail(){
        boolean isValid = true;
        if (isEmpty(email)) {
            email.setError("You must enter your email to login");
            isValid = false;
        } else{
            if (!isEmail(email)){
                email.setError("Enter valid email");
                isValid = false;
            }
        }
        if (isEmpty(password)) {
            password.setError("You must enter password to login!");
            isValid = false;
        } else {
            if (password.getText().toString().length() < 4) {
                password.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }

        //check email and password

        if (isValid) {
            String emailValue = email.getText().toString();
            String passwordValue = password.getText().toString();
            if (emailValue.equals("you@gmail.com") && passwordValue.equals("password1234")) {
                //everything checked we open new activity
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                //we close this activity
                this.finish();
            } else {
                Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }




    }


