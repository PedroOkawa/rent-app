package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.database.PaginationRepository;
import com.okawa.pedro.rentapp.di.module.DatabaseModule;
import com.okawa.pedro.rentapp.di.module.RentAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pokawa on 26/01/16.
 */
@Singleton
@Component(modules = { RentAppModule.class, DatabaseModule.class })
public interface RentAppComponent {

    void inject(RentApp rentApp);

    /* APP */
    RentApp providesRentApp();

    /* DATABASE */
    PaginationRepository providesPaginationRepository();
    AdvertisementRepository providesAdvertisementRepository();

}
