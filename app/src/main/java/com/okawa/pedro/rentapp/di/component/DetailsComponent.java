package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.di.module.DetailsModule;
import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.details.DetailsPresenter;
import com.okawa.pedro.rentapp.ui.details.DetailsActivity;
import com.okawa.pedro.rentapp.ui.details.DetailsView;

import dagger.Component;

/**
 * Created by pokawa on 28/01/16.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = DetailsModule.class)
public interface DetailsComponent {

    void inject(DetailsActivity detailsActivity);

    /* PRESENTER */
    DetailsView providesDetailsView();
    DetailsPresenter providesDetailsPresenter();

}
