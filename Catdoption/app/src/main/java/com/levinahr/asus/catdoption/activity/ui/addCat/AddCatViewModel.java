package com.levinahr.asus.catdoption.activity.ui.addCat;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.levinahr.asus.catdoption.model.ResponseErrorModel;
import com.levinahr.asus.catdoption.rest.ApiConfig;
import com.levinahr.asus.catdoption.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCatViewModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<ResponseErrorModel> responseErrorModelMutableLiveData;

    public LiveData<ResponseErrorModel> postCatDatas(String name, String age, int gender, String breed, String description) {
        if (responseErrorModelMutableLiveData == null) {
            responseErrorModelMutableLiveData = new MutableLiveData<>();
            postCatData(name, age, gender, breed, description);
        }

        return responseErrorModelMutableLiveData;
    }

    private void postCatData(String name, String age, int gender, String breed, String description) {
        ApiService apiService = ApiConfig.getApiService();
        apiService.postAddCat(name, age, gender, breed, description)
                .enqueue(new Callback<ResponseErrorModel>() {
                    @Override
                    public void onResponse(Call<ResponseErrorModel> call,
                                           Response<ResponseErrorModel> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onAddResponse: Add Success | > " + response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                        Log.d(TAG, "onAddFailure: Add Failed | " + t.getMessage());
                    }
                });
    }

}
