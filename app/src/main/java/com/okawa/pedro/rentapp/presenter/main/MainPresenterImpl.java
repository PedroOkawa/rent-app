package com.okawa.pedro.rentapp.presenter.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.databinding.ActivityMainBinding;
import com.okawa.pedro.rentapp.ui.main.MainView;
import com.okawa.pedro.rentapp.util.adapter.adtype.AdTypeAdapter;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;
import com.okawa.pedro.rentapp.util.manager.ApiManager;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import java.util.ArrayList;
import java.util.List;

import greendao.AdType;

/**
 * Created by pokawa on 26/01/16.
 */
public class MainPresenterImpl implements MainPresenter, OnApiServiceListener {

    private MainView mainView;
    private ApiManager apiManager;
    private CallManager callManager;
    private AdTypeRepository adTypeRepository;

    private AdTypeAdapter adTypeAdapter;

    public MainPresenterImpl(MainView mainView, ApiManager apiManager, CallManager callManager, AdTypeRepository adTypeRepository) {
        this.mainView = mainView;
        this.apiManager = apiManager;
        this.callManager = callManager;
        this.adTypeRepository = adTypeRepository;
    }

    @Override
    public void initialize(Context context, ActivityMainBinding binding) {
        /* SHOW PROGRESS BAR */
        binding.setLoading(true);

        /* INITIALIZE VIEWS */
        adTypeAdapter = new AdTypeAdapter(new ArrayList<AdType>(), context, callManager);

        binding.rvActivityMain.setAdapter(adTypeAdapter);
        binding.rvActivityMain.setLayoutManager(new LinearLayoutManager(context));

        /* API CALL */
        apiManager.requestAdTypes(this);
    }

    private void loadFromDatabase() {
        /* LOAD DATA PAGED FROM DATABASE */
        List<AdType> adTypes = adTypeRepository.selectAllAdType();
        adTypeAdapter.addDataSet(adTypes);
        adTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {
        loadFromDatabase();
        mainView.hideProgress();
    }

    @Override
    public void onError(String message) {
        loadFromDatabase();
        mainView.hideProgress();
        mainView.displayError(message);
    }
}
