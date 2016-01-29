package com.okawa.pedro.rentapp.suite;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.di.component.DaggerTestRentAppComponent;
import com.okawa.pedro.rentapp.di.module.RentAppModule;

/**
 * Created by pokawa on 29/01/16.
 */
public class TestRentApp extends RentApp {

    @Override
    protected void setupComponent() {
        component = DaggerTestRentAppComponent
                .builder()
                .rentAppModule(new RentAppModule(this))
                .build();
    }
}
