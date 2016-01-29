package com.okawa.pedro.rentapp.util.manager;

import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.di.module.DatabaseModule;
import com.okawa.pedro.rentapp.model.response.ResponseAdType;
import com.okawa.pedro.rentapp.model.response.ResponseSearch;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ApiQueryManager apiQueryManager;
    private AdTypeRepository adTypeRepository;
    private AdvertisementRepository advertisementRepository;

    public ApiManager(ApiInterface apiInterface,
                      ApiQueryManager apiQueryManager,
                      AdTypeRepository adTypeRepository,
                      AdvertisementRepository advertisementRepository) {
        this.apiInterface = apiInterface;
        this.apiQueryManager = apiQueryManager;
        this.adTypeRepository = adTypeRepository;
        this.advertisementRepository = advertisementRepository;
    }

    /* SEARCH CALL */
    public void requestAdvertisements(final OnApiServiceListener listener) {
        /* SEARCH PARAMETERS */
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ApiQueryManager.PARAMETER_PER_PAGE, DatabaseModule.SELECT_LIMIT);
        parameters.put(ApiQueryManager.PARAMETER_PAGE, advertisementRepository.getCurrentPage());

        apiInterface
                .search(apiQueryManager.generateQuery(parameters))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseSearch>() {
                    @Override
                    public void onCompleted() {
                        listener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseSearch responseSearch) {
                        Pagination pagination = responseSearch.getResult().getResults().getPagination();

                        if(pagination.getCurrentPage() < pagination.getNumPages()) {
                            pagination.setCurrentPage(pagination.getCurrentPage() + 1);
                        }

                        List<Advertisement> advertisements = responseSearch.getResult().getResults().getAdvertisements();

                        advertisementRepository.updatePagination(pagination);
                        advertisementRepository.updateAdvertisementsInTx(advertisements);
                    }
                });
    }

    /* AD TYPES CALL */
    public void requestAdTypes(final OnApiServiceListener onApiServiceListener) {
        apiInterface
                .adTypes(apiQueryManager.generateQuery())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseAdType>() {
                    @Override
                    public void onCompleted() {
                        onApiServiceListener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onApiServiceListener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseAdType responseAdType) {
                        adTypeRepository.updateAdTypeInTx(responseAdType.getResult().getAdTypes());
                    }
                });
    }
}
