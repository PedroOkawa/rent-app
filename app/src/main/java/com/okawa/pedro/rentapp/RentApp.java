package com.okawa.pedro.rentapp;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.okawa.pedro.rentapp.di.component.AppComponent;
import com.okawa.pedro.rentapp.di.component.DaggerRentAppComponent;
import com.okawa.pedro.rentapp.di.module.RentAppModule;

import io.fabric.sdk.android.Fabric;

/**
 * Created by pokawa on 26/01/16.
 */
public class RentApp extends Application {

    private static final String COMPONENT_NULL_EXCEPTION = "Component may not be null. You must initialize component before.";

    protected AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupCrashReport();
        setupComponent();
    }

    protected void setupCrashReport() {
        Fabric.with(this, new Crashlytics());
    }

    protected void setupComponent() {
        component = DaggerRentAppComponent
                .builder()
                .rentAppModule(new RentAppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        if(component == null) {
            throw new NullPointerException(COMPONENT_NULL_EXCEPTION);
        }
        return component;
    }

}
