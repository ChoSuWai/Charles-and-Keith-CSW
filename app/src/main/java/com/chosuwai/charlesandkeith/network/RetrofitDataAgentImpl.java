package com.chosuwai.charlesandkeith.network;

import android.text.GetChars;

import com.chosuwai.charlesandkeith.events.ApiErrorEvent;
import com.chosuwai.charlesandkeith.events.SuccessForceRefreshGetNewProductsEvent;
import com.chosuwai.charlesandkeith.events.SuccessGetNewProductsEvent;
import com.chosuwai.charlesandkeith.utils.NewProductsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewProductsDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;
    private NewProductsApi mTheApi;

    private RetrofitDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewProductsConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(NewProductsApi.class);
    }

    public static RetrofitDataAgentImpl getInstance() {
        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadNewProductsList(int page, String accessToken, final boolean isForceRefresh) {
        Call<GetNewProductsResponse> loadNewProductsCall = mTheApi.loadNewProductsList(accessToken, page);
        loadNewProductsCall.enqueue(new Callback<GetNewProductsResponse>() {
            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {
                GetNewProductsResponse newProductsResponse = response.body();

                if (newProductsResponse != null && newProductsResponse.isResponseOk()) {
                    if(isForceRefresh){
                        SuccessForceRefreshGetNewProductsEvent event=new SuccessForceRefreshGetNewProductsEvent(newProductsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }else{
                        SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }

                } else {
                    if (newProductsResponse == null) {
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(newProductsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
