package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.database.PaginationRepository;
import com.okawa.pedro.rentapp.di.module.ApiModule;
import com.okawa.pedro.rentapp.di.module.DatabaseModule;
import com.okawa.pedro.rentapp.di.module.RentAppModule;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.ApiManager;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by pokawa on 26/01/16.
 */
@Singleton
@Component(modules = { RentAppModule.class, ApiModule.class, DatabaseModule.class })
public interface RentAppComponent {

    void inject(RentApp rentApp);

    /* APP */
    RentApp providesRentApp();

    /* API */
    OkHttpClient providesOkHttpClient();
    ApiInterface providesApiInterface();
    ApiManager providesApiManager();

    /* DATABASE */
    PaginationRepository providesPaginationRepository();
    AdvertisementRepository providesAdvertisementRepository();

}
