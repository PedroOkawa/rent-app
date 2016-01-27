package com.okawa.pedro.rentapp.util.manager;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
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

    private static final String API_PROPERTIES = "api.properties";
    private static final String API_KEY = "key";

    private Context context;
    private ApiInterface apiInterface;
    private AdvertisementRepository advertisementRepository;

    public ApiManager(Context context,
                      ApiInterface apiInterface,
                      AdvertisementRepository advertisementRepository) {
        this.context = context;
        this.apiInterface = apiInterface;
        this.advertisementRepository = advertisementRepository;
    }

    /* SEARCH CALL */
    public void requestAdvertisements(final OnApiServiceListener listener) {
        if(advertisementRepository.canLoadNextPage()) {
            apiInterface
                    .search(defineAdvertisementsQuery())
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
                            if(pagination.getCurrentPage() < pagination.getNumPages()) {
                                pagination.setCurrentPage(pagination.getCurrentPage() + 1);
                            }
                            List<Advertisement> advertisements = response.getResult().getResults().getAdvertisements();

                            advertisementRepository.updatePagination(pagination);
                            advertisementRepository.insertOrReplaceInTx(advertisements);
                        }
                    });
        }
    }

    /* RETURNS PARAMETERS TO MAKE SEARCH */
    private String defineAdvertisementsQuery() {
        /* CHECK FOR PREVIOUS PAGINATION */
        long currentPage = advertisementRepository.getCurrentPage();

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
        stringBuffer.append(AdvertisementRepository.SELECT_LIMIT);
        stringBuffer.append(",");
        stringBuffer.append(ApiInterface.SEARCH_PAGE);
        stringBuffer.append(":");
        stringBuffer.append(currentPage);
        stringBuffer.append("}}");

        return stringBuffer.toString();
    }

    /* RETURN API KEY STORED ON PROPERTIES FILE */
    private String getApiKey() {
        String apiKey = "";

        Properties properties = new Properties();
        try {
            InputStream inputStream = context.getAssets().open(API_PROPERTIES);
            properties.load(inputStream);

            apiKey = properties.getProperty(API_KEY);
        } catch (IOException e) {
            Crashlytics.log(e.getMessage());
        }

        return apiKey;
    }
}
