package com.okawa.pedro.rentapp.database;

import java.util.Collection;
import java.util.List;

import greendao.AdType;
import greendao.AdTypeDao;
import greendao.DaoSession;

/**
 * Created by pokawa on 28/01/16.
 */
public class AdTypeRepository {

    private DaoSession daoSession;

    public AdTypeRepository(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void updateAdTypeInTx(Collection<AdType> advertisements) {
        daoSession.getAdTypeDao().insertOrReplaceInTx(advertisements);
    }

    public AdType selectAdTypeByName(String name) {
        return daoSession.getAdTypeDao().queryBuilder().where(AdTypeDao.Properties.Name.eq(name)).unique();
    }

    public List<AdType> selectAllAdType() {
        return daoSession.getAdTypeDao().queryBuilder().list();
    }

    public long count() {
        return daoSession.getAdTypeDao().count();
    }

}
