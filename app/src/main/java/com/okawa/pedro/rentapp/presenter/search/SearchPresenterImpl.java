package com.okawa.pedro.rentapp.presenter.search;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private OnSearchListener onSearchListener;

    public SearchPresenterImpl(SearchView searchView,
                               ApiManager apiManager,
                               AdvertisementRepository advertisementRepository) {
        this.searchView = searchView;
        this.apiManager = apiManager;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public void initialize(Context context, ActivitySearchBinding binding) {
        /* SHOW PROGRESS BAR */
        binding.setLoading(true);

        /* INITIALIZE VIEWS */
        advertisementAdapter = new AdvertisementAdapter(context, new ArrayList<Advertisement>());
        autoGridLayoutManager = new AutoGridLayoutManager(context);
        onSearchListener = new OnSearchListener(autoGridLayoutManager);

        binding.rvActivitySearch.setAdapter(advertisementAdapter);
        binding.rvActivitySearch.setLayoutManager(autoGridLayoutManager);
        binding.rvActivitySearch.addOnScrollListener(onSearchListener);
        binding.srlActivitySearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /* RESET ADVERTISEMENTS LIST */
                onSearchListener.reset();
                advertisementAdapter.reset();
                advertisementRepository.deleteAll();
                loadNextPage();
            }
        });
    }

    @Override
    public void onOrientationChanged(int orientation) {
        autoGridLayoutManager.changeColumnsNumber(orientation);
    }

    /* LOAD ADVERTISEMENTS SEARCH PAGE */
    private void loadNextPage() {
        searchView.showRefresh();

        if(advertisementRepository.canLoadNextPage()) {
            apiManager.requestAdvertisements(this);
        } else {
            loadFromDatabase();
            searchView.hideRefresh();
        }
    }

    private void loadFromDatabase() {
        /* LOAD DATA PAGED FROM DATABASE */
        List<Advertisement> advertisements =
                advertisementRepository.selectAdvertisementsPaged(advertisementAdapter.getItemCount());
        advertisementAdapter.addDataSet(advertisements);
        advertisementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {
        loadFromDatabase();
        searchView.hideRefresh();
    }

    @Override
    public void onError(String message) {
        searchView.hideRefresh();
        searchView.displayError(message);
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
