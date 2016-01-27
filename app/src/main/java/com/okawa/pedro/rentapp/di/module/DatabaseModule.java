package com.okawa.pedro.rentapp.di.module;

import android.database.sqlite.SQLiteDatabase;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * Created by pokawa on 26/01/16.
 */
@Module
public class DatabaseModule {

    private static final String DATABASE_NAME = "RENT_DB";

    @Singleton
    @Provides
    public DaoSession providesDaoSession(RentApp rentApp) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(rentApp, DATABASE_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    @Singleton
    @Provides
    public AdvertisementRepository providesAdvertisementRepository(DaoSession daoSession) {
        return new AdvertisementRepository(daoSession);
    }

}
