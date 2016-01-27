package com.okawa.pedro.rentapp.database;

import android.util.Log;

import java.util.Collection;
import java.util.List;

import greendao.Advertisement;
import greendao.DaoSession;
import greendao.Pagination;

/**
 * Created by pokawa on 26/01/16.
 */
public class AdvertisementRepository {

    public static final int SELECT_LIMIT = 20;

    private DaoSession daoSession;

    public AdvertisementRepository(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    /* SEARCH */

    public void insertOrReplaceInTx(Collection<Advertisement> advertisements) {
        daoSession.getAdvertisementDao().insertOrReplaceInTx(advertisements);
    }

    public List<Advertisement> selectAdvertisementsPaged(int offset) {
        return daoSession.getAdvertisementDao().queryBuilder().limit(SELECT_LIMIT).offset(offset).list();
    }

    public long count() {
        return daoSession.getAdvertisementDao().count();
    }

    /* PAGINATION */

    public void updatePagination(Pagination pagination) {
        pagination.setId((long) 0);
        daoSession.getPaginationDao().insertOrReplace(pagination);
    }

    public Pagination getPagination() {
        return daoSession.getPaginationDao().load((long) 0);
    }

    public boolean paginationExists() {
        return daoSession.getPaginationDao().count() > 0;
    }

    public boolean canLoadNextPage() {
        if (paginationExists()) {
            Pagination pagination = getPagination();
            if (count() < (pagination.getCurrentPage() * pagination.getPerPage())) {
                return true;
            }

            return false;
        }
        return true;
    }

    public long getCurrentPage() {
        if(paginationExists()) {
            Pagination pagination = getPagination();
            return pagination.getCurrentPage();
        }
        return 1;
    }

    /* COMMON */

    public void deleteAll() {
        daoSession.getPaginationDao().deleteAll();
        daoSession.getAdvertisementDao().deleteAll();
    }
}
