package com.okawa.pedro.rentapp.util;

import android.util.Log;

import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.database.PaginationRepository;
import com.okawa.pedro.rentapp.model.Response;
import com.okawa.pedro.rentapp.network.ApiInterface;

import java.util.List;

import greendao.Advertisement;
import greendao.Pagination;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pokawa on 27/01/16.
 */
public class ApiManager {

    private ApiInterface apiInterface;
    private AdvertisementRepository advertisementRepository;
    private PaginationRepository paginationRepository;

    public ApiManager(ApiInterface apiInterface,
                      AdvertisementRepository advertisementRepository,
                      PaginationRepository paginationRepository) {
        this.apiInterface = apiInterface;
        this.advertisementRepository = advertisementRepository;
        this.paginationRepository = paginationRepository;
    }

    public void requestAdvertisements() {
        apiInterface
                .listAdvertisements(defineQuery())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Pagination pagination = response.getResult().getResults().getPagination();
                        List<Advertisement> advertisements = response.getResult().getResults().getAdvertisements();

                        paginationRepository.updatePagination(pagination);
                        advertisementRepository.insertOrReplaceInTx(advertisements);
                    }
                });
    }

    private String defineQuery() {
        /* CHECK FOR PREVIOUS PAGINATION */
        long page = 1;
        if(paginationRepository.paginationExists()) {
            Pagination pagination = paginationRepository.getPagination();
            page = pagination.getCurrentPage();
        }

        /* BUILD QUERY */
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("{\"");
        stringBuffer.append(ApiInterface.SEARCH_API_KEY);
        stringBuffer.append("\":\"");
        stringBuffer.append("ebee1d7f1b18209efbc5a6fb0e760a56cc54f1ac");
        stringBuffer.append("\",\"");
        stringBuffer.append(ApiInterface.SEARCH_QUERY);
        stringBuffer.append("\":{\"");
        stringBuffer.append(ApiInterface.SEARCH_PER_PAGE);
        stringBuffer.append("\":");
        stringBuffer.append(ApiInterface.SEARCH_PER_PAGE_VALUE);
        stringBuffer.append(",\"");
        stringBuffer.append(ApiInterface.SEARCH_PAGE);
        stringBuffer.append("\":");
        stringBuffer.append(page);
        stringBuffer.append("}}");

        return stringBuffer.toString();
    }
}
