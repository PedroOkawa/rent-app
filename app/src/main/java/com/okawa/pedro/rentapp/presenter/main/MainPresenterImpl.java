package com.okawa.pedro.rentapp.presenter.main;

import android.content.Context;

import com.okawa.pedro.rentapp.databinding.ActivityMainBinding;
import com.okawa.pedro.rentapp.ui.main.MainView;
import com.okawa.pedro.rentapp.util.manager.CallManager;

/**
 * Created by pokawa on 26/01/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private CallManager callManager;

    public MainPresenterImpl(MainView mainView, CallManager callManager) {
        this.mainView = mainView;
        this.callManager = callManager;
    }

    @Override
    public void initialize(Context context, ActivityMainBinding binding) {

        callManager.search(context);

    }
}
