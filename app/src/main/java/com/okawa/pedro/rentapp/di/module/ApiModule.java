package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.database.PaginationRepository;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.ApiManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by pokawa on 27/01/16.
 */
@Module
public class ApiModule {

    private static final String URL = "https://api.daft.com/";
    private static final String VERSION = "v2/";
    private static final String TYPE = "json/";
    private static final String BASE_URL = URL.concat(VERSION).concat(TYPE);

    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }

    @Singleton
    @Provides
    public ApiInterface providesApiInterface(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    @Singleton
    @Provides
    public ApiManager providesApiManager(ApiInterface apiInterface, PaginationRepository paginationRepository) {
        return new ApiManager(apiInterface, paginationRepository);
    }

}
