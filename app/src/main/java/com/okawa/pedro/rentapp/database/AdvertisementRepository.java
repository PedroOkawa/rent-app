package com.okawa.pedro.rentapp.database;

import java.util.Collection;

import greendao.Advertisement;
import greendao.DaoSession;

/**
 * Created by pokawa on 26/01/16.
 */
public class AdvertisementRepository {

    private DaoSession daoSession;

    public AdvertisementRepository(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void insertOrReplaceInTx(Collection<Advertisement> advertisements) {
        daoSession.getAdvertisementDao().insertOrReplaceInTx(advertisements);
    }

    public long count() {
        return daoSession.getAdvertisementDao().count();
    }

}
