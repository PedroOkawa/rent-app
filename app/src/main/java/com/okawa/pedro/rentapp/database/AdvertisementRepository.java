package com.okawa.pedro.rentapp.database;

import greendao.DaoSession;

/**
 * Created by pokawa on 26/01/16.
 */
public class AdvertisementRepository {

    private DaoSession daoSession;

    public AdvertisementRepository(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

}
