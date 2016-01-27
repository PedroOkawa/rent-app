package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.search.SearchPresenter;
import com.okawa.pedro.rentapp.presenter.search.SearchPresenterImpl;
import com.okawa.pedro.rentapp.ui.search.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pokawa on 27/01/16.
 */
@Module
public class SearchModule {

    private SearchView searchView;

    public SearchModule(SearchView searchView) {
        this.searchView = searchView;
    }

    @Activity
    @Provides
    public SearchView providesSearchView() {
        return searchView;
    }

    @Activity
    @Provides
    public SearchPresenter providesSearchPresenter(SearchView searchView) {
        return new SearchPresenterImpl(searchView);
    }

}
