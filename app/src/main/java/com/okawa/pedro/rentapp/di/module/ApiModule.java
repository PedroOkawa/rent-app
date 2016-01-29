package com.okawa.pedro.rentapp.di.module;

import com.crashlytics.android.Crashlytics;
import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.manager.ApiManager;
import com.okawa.pedro.rentapp.util.manager.ApiQueryManager;

import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    private static final String API_PROPERTIES = "api.properties";
    private static final String API_KEY = "key";

    @Singleton
    @Provides
    public ApiInterface providesApiInterface() {
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    @Singleton
    @Provides
    public String providesApiKey(RentApp rentApp) {
        String apiKey = "";

        Properties properties = new Properties();
        try {
            InputStream inputStream = rentApp.getAssets().open(API_PROPERTIES);
            properties.load(inputStream);

            apiKey = properties.getProperty(API_KEY);
        } catch (IOException e) {
            Crashlytics.log(e.getMessage());
        }

        return apiKey;
    }

    @Singleton
    @Provides
    public ApiQueryManager providesQueryManager(String apiKey) {
        return new ApiQueryManager(apiKey);
    }

    @Singleton
    @Provides
    public ApiManager providesApiManager(ApiInterface apiInterface,
                                         ApiQueryManager apiQueryManager,
                                         AdTypeRepository adTypeRepository,
                                         AdvertisementRepository advertisementRepository) {
        return new ApiManager(apiInterface, apiQueryManager, adTypeRepository, advertisementRepository);
    }

}
