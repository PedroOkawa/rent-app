package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.util.manager.CallManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pokawa on 27/01/16.
 */
@Module
public class CallModule {

    @Singleton
    @Provides
    public CallManager providesCallManager() {
        return new CallManager();
    }

}
