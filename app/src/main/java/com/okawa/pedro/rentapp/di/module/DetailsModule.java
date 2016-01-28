package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.details.DetailsPresenter;
import com.okawa.pedro.rentapp.presenter.details.DetailsPresenterImpl;
import com.okawa.pedro.rentapp.ui.details.DetailsView;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pokawa on 28/01/16.
 */
@Module
public class DetailsModule {

    private DetailsView detailsView;

    public DetailsModule(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Activity
    @Provides
    public DetailsView providesDetailsView() {
        return detailsView;
    }

    @Activity
    @Provides
    public DetailsPresenter providesDetailsPresenter(DetailsView detailsView,
                                                     AdvertisementRepository advertisementRepository) {
        return new DetailsPresenterImpl(detailsView, advertisementRepository);
    }

}
