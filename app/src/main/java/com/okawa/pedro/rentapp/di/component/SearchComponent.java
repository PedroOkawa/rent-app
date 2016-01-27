package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.di.module.SearchModule;
import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.search.SearchPresenter;
import com.okawa.pedro.rentapp.ui.search.SearchActivity;
import com.okawa.pedro.rentapp.ui.search.SearchView;

import dagger.Component;

/**
 * Created by pokawa on 27/01/16.
 */
@Activity
@Component(dependencies = RentAppComponent.class, modules = SearchModule.class)
public interface SearchComponent {

    void inject(SearchActivity searchActivity);

    /* PRESENTER */
    SearchView providesSearchView();
    SearchPresenter providesSearchPresenter();
}
