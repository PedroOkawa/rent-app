package com.okawa.pedro.rentapp.presenter.details;

import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.ui.details.DetailsView;

/**
 * Created by pokawa on 28/01/16.
 */
public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView detailsView;
    private AdvertisementRepository advertisementRepository;

    public DetailsPresenterImpl(DetailsView detailsView, AdvertisementRepository advertisementRepository) {
        this.detailsView = detailsView;
        this.advertisementRepository = advertisementRepository;
    }

}
