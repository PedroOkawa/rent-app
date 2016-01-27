package com.okawa.pedro.rentapp.util.manager;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.database.PaginationRepository;
import com.okawa.pedro.rentapp.model.Response;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import greendao.Advertisement;
import greendao.Pagination;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pokawa on 27/01/16.
 */
public class ApiManager {

    private static final String API_KEY = "key";

    private Context context;
    private ApiInterface apiInterface;
    private AdvertisementRepository advertisementRepository;
    private PaginationRepository paginationRepository;

    public ApiManager(Context context,
                      ApiInterface apiInterface,
                      AdvertisementRepository advertisementRepository,
                      PaginationRepository paginationRepository) {
        this.context = context;
        this.apiInterface = apiInterface;
        this.advertisementRepository = advertisementRepository;
        this.paginationRepository = paginationRepository;
    }

    public void requestAdvertisements(final OnApiServiceListener listener) {
        if(callListAdvertisements()) {
            apiInterface
                    .listAdvertisements(defineQuery())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onCompleted() {
                            listener.onSuccess();
                        }

                        @Override
                        public void onError(Throwable e) {
                            listener.onError(e.getMessage());
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
    }

    private boolean callListAdvertisements() {
        if(paginationRepository.paginationExists()) {
            Pagination pagination = paginationRepository.getPagination();
            if(advertisementRepository.count() >= pagination.getTotal()) {
                return false;
            }
        }

        return true;
    }

    private String defineQuery() {
        /* CHECK FOR PREVIOUS PAGINATION */
        long currentPage = getCurrentPage();

        /* BUILD QUERY */
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("{");
        stringBuffer.append(ApiInterface.SEARCH_API_KEY);
        stringBuffer.append(":\"");
        stringBuffer.append(getApiKey());
        stringBuffer.append("\",");
        stringBuffer.append(ApiInterface.SEARCH_QUERY);
        stringBuffer.append(":{");
        stringBuffer.append(ApiInterface.SEARCH_PER_PAGE);
        stringBuffer.append(":");
        stringBuffer.append(ApiInterface.SEARCH_PER_PAGE_VALUE);
        stringBuffer.append(",");
        stringBuffer.append(ApiInterface.SEARCH_PAGE);
        stringBuffer.append(":");
        stringBuffer.append(currentPage);
        stringBuffer.append("}}");

        return stringBuffer.toString();
    }

    private long getCurrentPage() {
        if(paginationRepository.paginationExists()) {
            Pagination pagination = paginationRepository.getPagination();
            return pagination.getCurrentPage();
        }
        return 1;
    }

    private String getApiKey() {
        String apiKey = "";

        Properties properties = new Properties();
        try {
            InputStream inputStream = context.getAssets().open("api.properties");
            properties.load(inputStream);

            apiKey = properties.getProperty(API_KEY);
        } catch (IOException e) {
            Crashlytics.log(e.getMessage());
        }

        return apiKey;
    }
}
