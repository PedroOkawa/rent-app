package com.okawa.pedro.rentapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import greendao.AdType;

/**
 * Created by pokawa on 28/01/16.
 */
public class ResponseAdType {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    /* RESULT */
    public class Result {

        @SerializedName("ad_types")
        private List<AdType> adTypes;

        public List<AdType> getAdTypes() {
            return adTypes;
        }

        public void setAdTypes(List<AdType> adTypes) {
            this.adTypes = adTypes;
        }
    }
}
