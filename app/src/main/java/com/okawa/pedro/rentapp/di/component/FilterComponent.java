package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.di.module.FilterModule;
import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.filter.FilterPresenter;
import com.okawa.pedro.rentapp.ui.filter.FilterActivity;
import com.okawa.pedro.rentapp.ui.filter.FilterView;

import dagger.Component;

/**
 * Created by pokawa on 28/01/16.
 */
@Activity
@Component(dependencies = RentAppComponent.class, modules = FilterModule.class)
public interface FilterComponent {

    void inject(FilterActivity filterActivity);

    /* PRESENTER */
    FilterView providesFilterView();
    FilterPresenter providesFilterPresenter();
}
