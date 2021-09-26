package com.levinahr.asus.catdoption.activity.ui.cat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.levinahr.asus.catdoption.R;
import com.levinahr.asus.catdoption.activity.HomeActivity;
import com.levinahr.asus.catdoption.helper.Config;
import com.levinahr.asus.catdoption.model.ResponseErrorModel;
import com.levinahr.asus.catdoption.rest.ApiConfig;
import com.levinahr.asus.catdoption.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cat);

        TextView nameDetail = findViewById(R.id.name_detail_cat);
        TextView ageDetail = findViewById(R.id.age_detail_cat);
        TextView genderDetail = findViewById(R.id.gender_detail_cat);
        TextView breedDetail = findViewById(R.id.breed_detail_cat);
        TextView statusDetail = findViewById(R.id.status_detail_cat);
        TextView descriptionDetail = findViewById(R.id.desc_detail_cat);
        Button btnAdopt = findViewById(R.id.btn_adopt_cat);
        Button btnDelete = findViewById(R.id.btn_delete_cat);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String gender = getIntent().getStringExtra("gender");
        String breed = getIntent().getStringExtra("breed");
        String status = getIntent().getStringExtra("status");
        String desc = getIntent().getStringExtra("description");

        nameDetail.setText(name);
        ageDetail.setText(age);
        genderDetail.setText(gender);
        breedDetail.setText(breed);
        statusDetail.setText(status);
        descriptionDetail.setText(desc);

        if (status.equals("Adopted")) {
            btnAdopt.setVisibility(View.INVISIBLE);
        }

        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiConfig.getApiService();
                apiService.postUpdateCat(id)
                        .enqueue(new Callback<ResponseErrorModel>() {
                            @Override
                            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                                System.out.println("Response");
                                if (response.isSuccessful()){
                                    System.out.println("Success");
                                    Toast.makeText(DetailCatActivity.this, "Update success",
                                            Toast.LENGTH_SHORT).show();
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(),
                                            HomeActivity.class));
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                                System.out.println("Fail");
                                Toast.makeText(DetailCatActivity.this, "Update failed | "
                                                + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiConfig.getApiService();
                apiService.postDeleteCat(id)
                        .enqueue(new Callback<ResponseErrorModel>() {
                            @Override
                            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                                System.out.println("Response");
                                if (response.isSuccessful()){
                                    System.out.println("Success");
                                    Toast.makeText(DetailCatActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(),
                                            HomeActivity.class));
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                                System.out.println("Fail");
                                Toast.makeText(DetailCatActivity.this, "Delete failed | "
                                                + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}