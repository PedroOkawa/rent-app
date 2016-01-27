package com.okawa.pedro.rentapp.presenter.main;

import com.okawa.pedro.rentapp.databinding.ActivityMainBinding;
import com.okawa.pedro.rentapp.ui.main.MainView;
import com.okawa.pedro.rentapp.util.manager.ApiManager;

/**
 * Created by pokawa on 26/01/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private ApiManager apiManager;

    public MainPresenterImpl(MainView mainView, ApiManager apiManager) {
        this.mainView = mainView;
        this.apiManager = apiManager;
    }

    @Override
    public void setupView(ActivityMainBinding binding) {
        apiManager.requestAdvertisements();
    }
}
