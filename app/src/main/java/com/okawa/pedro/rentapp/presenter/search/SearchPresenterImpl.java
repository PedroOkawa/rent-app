package com.okawa.pedro.rentapp.presenter.search;

import com.okawa.pedro.rentapp.databinding.ActivitySearchBinding;
import com.okawa.pedro.rentapp.ui.search.SearchView;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;
import com.okawa.pedro.rentapp.util.manager.ApiManager;

/**
 * Created by pokawa on 27/01/16.
 */
public class SearchPresenterImpl implements SearchPresenter, OnApiServiceListener {

    private SearchView searchView;
    private ApiManager apiManager;

    public SearchPresenterImpl(SearchView searchView, ApiManager apiManager) {
        this.searchView = searchView;
        this.apiManager = apiManager;
    }

    @Override
    public void setupViews(ActivitySearchBinding binding) {
        /* SHOWS PROGRESS BAR WHILE LOADING */
        searchView.showProgressBar();

        /* CALLS REST SERVICE */
        apiManager.requestAdvertisements(this);
    }

    @Override
    public void onSuccess() {
        searchView.hideProgressBar();
    }

    @Override
    public void onError(String message) {
        searchView.hideProgressBar();
    }
}
