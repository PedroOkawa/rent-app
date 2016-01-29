package com.okawa.pedro.rentapp.presenter.search;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.databinding.ActivitySearchBinding;
import com.okawa.pedro.rentapp.model.bus.ConnectionEvent;
import com.okawa.pedro.rentapp.ui.search.SearchView;
import com.okawa.pedro.rentapp.util.adapter.advertisement.AdvertisementAdapter;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;
import com.okawa.pedro.rentapp.util.listener.OnRecyclerViewListener;
import com.okawa.pedro.rentapp.util.manager.ApiManager;
import com.okawa.pedro.rentapp.util.manager.AutoGridLayoutManager;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import greendao.Advertisement;

/**
 * Created by pokawa on 27/01/16.
 */
public class SearchPresenterImpl implements SearchPresenter, OnApiServiceListener {

    private SearchView searchView;
    private ApiManager apiManager;
    private CallManager callManager;
    private AdvertisementRepository advertisementRepository;

    private AdvertisementAdapter advertisementAdapter;
    private AutoGridLayoutManager autoGridLayoutManager;
    private OnSearchListListener onSearchListListener;

    public SearchPresenterImpl(SearchView searchView,
                               ApiManager apiManager,
                               CallManager callManager,
                               AdvertisementRepository advertisementRepository) {
        this.searchView = searchView;
        this.apiManager = apiManager;
        this.callManager = callManager;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public void initialize(Context context, ActivitySearchBinding binding) {
        /* SHOW PROGRESS BAR */
        binding.setLoading(true);

        /* INITIALIZE VIEWS */
        advertisementAdapter = new AdvertisementAdapter(new ArrayList<Advertisement>(), context, callManager);
        autoGridLayoutManager = new AutoGridLayoutManager(context);
        onSearchListListener = new OnSearchListListener(autoGridLayoutManager);

        binding.rvActivitySearch.setAdapter(advertisementAdapter);
        binding.rvActivitySearch.setLayoutManager(autoGridLayoutManager);
        binding.rvActivitySearch.addOnScrollListener(onSearchListListener);
        binding.srlActivitySearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /* RESET ADVERTISEMENTS LIST */
                onSearchListListener.reset();
                advertisementAdapter.reset();
                advertisementRepository.deleteAll();
                loadNextPage();
            }
        });

        /* EVENT BUS */
        EventBus.getDefault().register(this);
    }

    @Override
    public void restoreData(Intent intent) {
        /* INITIALIZE TOOLBAR */
        searchView.initializeToolbar(intent.getStringExtra(CallManager.BUNDLE_SEARCH));
    }

    @Override
    public void onOrientationChanged(int orientation) {
        autoGridLayoutManager.changeColumnsNumber(orientation);
    }

    /* LOAD ADVERTISEMENTS SEARCH PAGE */
    private void loadNextPage() {
        searchView.showProgress();

        if(advertisementRepository.canLoadNextPage()) {
            apiManager.requestAdvertisements(this);
        } else {
            loadFromDatabase();
            searchView.hideProgress();
        }
    }

    private void loadFromDatabase() {
        /* LOAD DATA PAGED FROM DATABASE */
        List<Advertisement> advertisements = advertisementRepository.selectAllAdvertisementsPaged(advertisementAdapter.getItemCount());
        advertisementAdapter.addDataSet(advertisements);
        advertisementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {
        loadFromDatabase();
        searchView.hideProgress();
    }

    @Override
    public void onError(String message) {
        loadFromDatabase();
        searchView.hideProgress();
        searchView.displayError(message);
    }

    /* ON ESTABLISH CONNECTION */
    @Subscribe
    public void onEvent(ConnectionEvent connectionEvent) {
        /* REQUEST DATA FROM API */
        loadNextPage();
    }

    /* ENDLESS RECYCLER VIEW LISTENER */
    private class OnSearchListListener extends OnRecyclerViewListener {

        public OnSearchListListener(GridLayoutManager gridLayoutManager) {
            super(gridLayoutManager);
        }

        @Override
        public void onVisibleThreshold() {
            loadNextPage();
        }
    }
}
