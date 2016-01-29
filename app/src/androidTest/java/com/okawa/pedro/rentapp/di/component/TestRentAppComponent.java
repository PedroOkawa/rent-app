package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.common.MainActivityTest;
import com.okawa.pedro.rentapp.di.module.ApiModule;
import com.okawa.pedro.rentapp.di.module.CallModule;
import com.okawa.pedro.rentapp.di.module.DatabaseModule;
import com.okawa.pedro.rentapp.di.module.RentAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pokawa on 29/01/16.
 */
@Singleton
@Component(modules = { RentAppModule.class, ApiModule.class, CallModule.class, DatabaseModule.class })
public interface TestRentAppComponent extends AppComponent {

    void inject(MainActivityTest mainActivityTest);

}
