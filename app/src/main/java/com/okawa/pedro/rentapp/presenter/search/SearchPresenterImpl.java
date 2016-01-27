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
        /* SHOWS PROGRESS BAR WHILE LOADING */
        searchView.showProgressBar();

        /* INITIALIZE VIEWS */
        advertisementAdapter = new AdvertisementAdapter(context, new ArrayList<Advertisement>());
        autoGridLayoutManager = new AutoGridLayoutManager(context);

        binding.rvActivitySearch.setAdapter(advertisementAdapter);
        binding.rvActivitySearch.setLayoutManager(autoGridLayoutManager);
        binding.rvActivitySearch.addOnScrollListener(new OnSearchListener(autoGridLayoutManager));

        /* CALLS REST SERVICE */
        apiManager.requestAdvertisements(this);
    }

    @Override
    public void onOrientationChanged(int orientation) {
        autoGridLayoutManager.changeColumnsNumber(orientation);
    }

    /* LOADS ADVERTISEMENTS SEARCH PAGE */
    private void loadNextPage() {
        List<Advertisement> advertisements =
                advertisementRepository.selectAdvertisementsPaged(advertisementAdapter.getItemCount());
        advertisementAdapter.addDataSet(advertisements);
    }

    @Override
    public void onSuccess() {
        searchView.hideProgressBar();
    }

    @Override
    public void onError(String message) {
        searchView.hideProgressBar();
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
