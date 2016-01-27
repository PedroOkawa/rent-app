package com.okawa.pedro.rentapp.database;

import greendao.DaoSession;
import greendao.Pagination;

/**
 * Created by pokawa on 26/01/16.
 */
public class PaginationRepository {

    private DaoSession daoSession;

    public PaginationRepository(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void updatePagination(Pagination pagination) {
        pagination.setId((long) 0);
        daoSession.getPaginationDao().insertOrReplace(pagination);
    }

    public Pagination getPagination() {
        return daoSession.getPaginationDao().load((long) 0);
    }

}
