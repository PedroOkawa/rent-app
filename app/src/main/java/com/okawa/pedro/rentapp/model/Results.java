package com.okawa.pedro.rentapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import greendao.Advertisement;
import greendao.Pagination;

/**
 * Created by pokawa on 27/01/16.
 */
public class Results {

    private Pagination pagination;
    @SerializedName("ads")
    private List<Advertisement> advertisements;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
