package com.okawa.pedro.rentapp.network;

import com.okawa.pedro.rentapp.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pokawa on 27/01/16.
 */
public interface ApiInterface {

    String PATH_SEARCH = "search_sale";
    String SEARCH_PARAMETERS = "parameters";
    String SEARCH_API_KEY = "\"api_key\"";
    String SEARCH_QUERY = "\"query\"";
    String SEARCH_PER_PAGE = "\"perpage\"";
    String SEARCH_PER_PAGE_VALUE = "50";
    String SEARCH_PAGE = "\"page\"";

    @GET(PATH_SEARCH)
    Observable<Response> search(@Query(SEARCH_PARAMETERS) String parameters);

}
