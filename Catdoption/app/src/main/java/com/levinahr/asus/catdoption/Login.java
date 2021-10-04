package com.levinahr.asus.catdoption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.levinahr.asus.catdoption.activity.HomeActivity;
import com.levinahr.asus.catdoption.model.UserModel;
import com.levinahr.asus.catdoption.rest.ApiConfig;
import com.levinahr.asus.catdoption.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText username_input,password_input;
    Button register, btnLogin;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.userName);
        password_input = findViewById(R.id.loginPassword);
        register = findViewById(R.id.register);
        btnLogin = findViewById(R.id.btnLogin);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });

        //when someone clicks on login
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

    private void validateUserData() {

        //first getting the values
        final String username = username_input.getText().toString();
        final String password = password_input.getText().toString();

        //checking if username is empty
        if (TextUtils.isEmpty(username)) {
            username_input.setError("Please enter your username");
            username_input.requestFocus();
            // Vibrate for 100 milliseconds
            v.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }

        //checking if password is empty
        if (TextUtils.isEmpty(password)) {
            password_input.setError("Please enter your password");
            password_input.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }

        //Login User if everything is fine
        loginUser(username,password);
    }

    private void loginUser(String username, String password) {

        //call retrofit
        //making api call
        ApiService apiService = ApiConfig.getApiService();
        Call<UserModel> login = apiService.login(username,password);

        login.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.body().getSuccess() == 1) {
                    //get username
                    String user = response.body().getUsername();

                    //storing the user in shared preferences
                    SharedPref.getInstance(Login.this).storeUserName(user);
                    //Toast.makeText(MainActivity.this,response.body().getUsername(),Toast.LENGTH_LONG).show();

                    startActivity(new Intent(Login.this, HomeActivity.class));

                } else {
                    Toast.makeText(Login.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}