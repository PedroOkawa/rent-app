package com.okawa.pedro.rentapp.presenter.search;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.databinding.ActivitySearchBinding;
import com.okawa.pedro.rentapp.ui.search.SearchView;
import com.okawa.pedro.rentapp.util.adapter.advertisement.AdvertisementAdapter;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;
import com.okawa.pedro.rentapp.util.listener.OnRecyclerViewListener;
import com.okawa.pedro.rentapp.util.manager.ApiManager;
import com.okawa.pedro.rentapp.util.manager.AutoGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import greendao.Advertisement;

/**
 * Created by pokawa on 27/01/16.
 */
public class SearchPresenterImpl implements SearchPresenter, OnApiServiceListener {

    private SearchView searchView;
    private ApiManager apiManager;
    private AdvertisementRepository advertisementRepository;

    private Context context;
    private ActivitySearchBinding binding;
    private AdvertisementAdapter advertisementAdapter;
    private AutoGridLayoutManager autoGridLayoutManager;

    public SearchPresenterImpl(SearchView searchView,
                               ApiManager apiManager,
                               AdvertisementRepository advertisementRepository) {
        this.searchView = searchView;
        this.apiManager = apiManager;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public void initialize(Context context, ActivitySearchBinding binding) {
        /* STORES CONTEXT */
        this.context = context;

        /* STORES BINDING */
        this.binding = binding;

        /* SHOWS PROGRESS BAR WHILE LOADING */
        searchView.showProgressBar();

        /* INITIALIZE VIEWS */
        advertisementAdapter = new AdvertisementAdapter(context, new ArrayList<Advertisement>());
        autoGridLayoutManager = new AutoGridLayoutManager(context);

        this.binding.rvActivitySearch.setAdapter(advertisementAdapter);
        this.binding.rvActivitySearch.setLayoutManager(autoGridLayoutManager);
        this.binding.rvActivitySearch.addOnScrollListener(new OnSearchListener(autoGridLayoutManager));

        /* LOAD FIRST PAGE */
        loadNextPage();
    }

    @Override
    public void onOrientationChanged(int orientation) {
        autoGridLayoutManager.changeColumnsNumber(orientation);
    }

    /* LOADS ADVERTISEMENTS SEARCH PAGE */
    private void loadNextPage() {
        this.binding.srlActivitySearch.setRefreshing(true);
        if(advertisementRepository.canLoadNextPage()) {
            /* CALLS REST SERVICE */
            apiManager.requestAdvertisements(this);
        } else {
            loadFromDatabase();
        }
    }

    private void loadFromDatabase() {
        List<Advertisement> advertisements =
                advertisementRepository.selectAdvertisementsPaged(advertisementAdapter.getItemCount());
        advertisementAdapter.addDataSet(advertisements);
    }

    @Override
    public void onSuccess() {
        searchView.hideProgressBar();

        this.binding.srlActivitySearch.setRefreshing(false);

        loadFromDatabase();
    }

    @Override
    public void onError(String message) {
        searchView.hideProgressBar();

        this.binding.srlActivitySearch.setRefreshing(false);
    }

    /* ENDLESS RECYCLER VIEW LISTENER */
    private class OnSearchListener extends OnRecyclerViewListener {

        public OnSearchListener(GridLayoutManager gridLayoutManager) {
            super(gridLayoutManager);
        }

        @Override
        public void onVisibleThreshold() {
            loadNextPage();
        }
    }
}
