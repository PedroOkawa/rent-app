package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.filter.FilterPresenter;
import com.okawa.pedro.rentapp.presenter.filter.FilterPresenterImpl;
import com.okawa.pedro.rentapp.ui.filter.FilterView;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pokawa on 28/01/16.
 */
@Module
public class FilterModule {

    private FilterView filterView;

    public FilterModule(FilterView filterView) {
        this.filterView = filterView;
    }

    @Activity
    @Provides
    public FilterView providesFilterView() {
        return filterView;
    }

    @Activity
    @Provides
    public FilterPresenter providesFilterPresnter(FilterView filterView, CallManager callManager) {
        return new FilterPresenterImpl(filterView, callManager);
    }



}
