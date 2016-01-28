package com.okawa.pedro.rentapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import greendao.Advertisement;
import greendao.Pagination;

/**
 * Created by pokawa on 27/01/16.
 */
public class ResponseSearch {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    /* RESULT */
    public class Result {

        private Results results;

        public Results getResults() {
            return results;
        }

        public void setResults(Results results) {
            this.results = results;
        }
    }

    /* RESULTS */
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

}
