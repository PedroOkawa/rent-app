package com.okawa.pedro.rentapp.database;

import com.okawa.pedro.rentapp.di.module.DatabaseModule;

import java.util.Collection;
import java.util.List;

import greendao.Advertisement;
import greendao.AdvertisementDao;
import greendao.DaoSession;
import greendao.Pagination;

/**
 * Created by pokawa on 26/01/16.
 */
public class AdvertisementRepository {

    private DaoSession daoSession;

    public AdvertisementRepository(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    /* SEARCH */

    public void updateAdvertisementsInTx(Collection<Advertisement> advertisements) {
        daoSession.getAdvertisementDao().insertOrReplaceInTx(advertisements);
    }

    public Advertisement selectAdvertisementById(long id) {
        return daoSession.getAdvertisementDao().queryBuilder().where(AdvertisementDao.Properties.Id.eq(id)).unique();
    }

    public List<Advertisement> selectAllAdvertisementsPaged(int offset) {
        return daoSession.getAdvertisementDao().queryBuilder().limit(DatabaseModule.SELECT_LIMIT).offset(offset).list();
    }

    public long count() {
        return daoSession.getAdvertisementDao().count();
    }

    /* PAGINATION */

    public void updatePagination(Pagination pagination) {
        pagination.setId((long) 0);
        daoSession.getPaginationDao().insertOrReplace(pagination);
    }

    public Pagination selectPagination() {
        return daoSession.getPaginationDao().load((long) 0);
    }

    public boolean paginationExists() {
        return daoSession.getPaginationDao().count() > 0;
    }

    public boolean canLoadNextPage() {
        if (paginationExists()) {
            Pagination pagination = selectPagination();
            if (count() < (pagination.getCurrentPage() * pagination.getPerPage())) {
                return true;
            }

            return false;
        }
        return true;
    }

    public long getCurrentPage() {
        if(paginationExists()) {
            Pagination pagination = selectPagination();
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
