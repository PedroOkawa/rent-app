package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.RentApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pokawa on 26/01/16.
 */
@Module
public class RentAppModule {

    private RentApp rentApp;

    public RentAppModule(RentApp rentApp) {
        this.rentApp = rentApp;
    }

    @Singleton
    @Provides
    public RentApp providesRentApp() {
        return rentApp;
    }

}
