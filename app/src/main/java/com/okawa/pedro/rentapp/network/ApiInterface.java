package com.okawa.pedro.rentapp.network;

import com.okawa.pedro.rentapp.model.response.ResponseAdType;
import com.okawa.pedro.rentapp.model.response.ResponseSearch;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pokawa on 27/01/16.
 */
public interface ApiInterface {

    String PARAMETERS = "parameters";

    String PATH_SEARCH = "search_sale";
    String PATH_AD_TYPES = "ad_types";

    @GET(PATH_SEARCH)
    Observable<ResponseSearch> search(@Query(PARAMETERS) String parameters);

    @GET(PATH_AD_TYPES)
    Observable<ResponseAdType> adTypes(@Query(PARAMETERS) String parameters);

}
