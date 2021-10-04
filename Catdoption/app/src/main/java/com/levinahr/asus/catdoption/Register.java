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
import android.widget.Toast;

import com.levinahr.asus.catdoption.model.UserModel;
import com.levinahr.asus.catdoption.rest.ApiConfig;
import com.levinahr.asus.catdoption.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText username,password,cpassword;
    Button login, btnRegister;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.registerName);
        password = findViewById(R.id.registerPassword);
        cpassword = findViewById(R.id.confirmpassword);
        btnRegister = findViewById(R.id.btnRegister);
        login = findViewById(R.id.login);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validateUserData() {
        //find values
        final String reg_name = username.getText().toString();
        final String reg_password = cpassword.getText().toString();
        final String reg_cpassword = cpassword.getText().toString();

        //checking if username is empty
        if (TextUtils.isEmpty(reg_name)) {
            username.setError("Please enter username");
            username.requestFocus();
            // Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }

        //checking if password is empty
        if (TextUtils.isEmpty(reg_password)) {
            password.setError("Please enter password");
            password.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }

        //checking if password matches
        if (!reg_password.equals(reg_cpassword)) {
            password.setError("Password Does not Match");
            password.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }

        //After Validating we register User
        registerUser(reg_name,reg_password);
    }

    private void registerUser(String user_name, String user_pass) {
        final String reg_username = username.getText().toString();
        final String reg_password = cpassword.getText().toString();

        //call retrofit
        //making api call
        ApiService apiService = ApiConfig.getApiService();
        Call<UserModel> login = apiService.register(user_name, user_pass);

        login.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body().getSuccess() == 1) {
                    //get username
                    String user = response.body().getUsername();
                    startActivity(new Intent(Register.this, Login.class));

                } else {
                    Toast.makeText(Register.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(Register.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}