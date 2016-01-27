package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.di.module.ApiModule;
import com.okawa.pedro.rentapp.di.module.CallModule;
import com.okawa.pedro.rentapp.di.module.DatabaseModule;
import com.okawa.pedro.rentapp.di.module.RentAppModule;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.manager.ApiManager;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pokawa on 26/01/16.
 */
@Singleton
@Component(modules = { RentAppModule.class, ApiModule.class, CallModule.class, DatabaseModule.class })
public interface RentAppComponent {

    void inject(RentApp rentApp);

    /* APP */
    RentApp providesRentApp();

    /* API */
    ApiInterface providesApiInterface();
    ApiManager providesApiManager();

    /* CALL */
    CallManager providesCallManager();

    /* DATABASE */
    AdvertisementRepository providesAdvertisementRepository();

}
