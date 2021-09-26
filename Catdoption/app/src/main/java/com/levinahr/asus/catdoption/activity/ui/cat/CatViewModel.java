package com.levinahr.asus.catdoption.activity.ui.cat;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.levinahr.asus.catdoption.model.CatModel;
import com.levinahr.asus.catdoption.rest.ApiConfig;
import com.levinahr.asus.catdoption.rest.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatViewModel extends ViewModel {
    private String TAG = "retrofit";
    private MutableLiveData<List<CatModel>> catModelMutableLiveData;

    public LiveData<List<CatModel>> getCat(){
        if (catModelMutableLiveData == null){
            catModelMutableLiveData = new MutableLiveData<List<CatModel>>();
            loadDataCat();
        }

        return catModelMutableLiveData;
    }

    private void loadDataCat() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.getAllCatList()
                .enqueue(new Callback<List<CatModel>>() {
                    @Override
                    public void onResponse(Call<List<CatModel>> call, Response<List<CatModel>> response) {
                        if (response.isSuccessful()) {
                            catModelMutableLiveData.setValue(response.body());
                            Log.d(TAG, "onGetResponse: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CatModel>> call, Throwable t) {
                        Log.d(TAG, "onGetFailure: " + t.getLocalizedMessage() + "|" + t.getMessage());
                    }
                });
    }
}
