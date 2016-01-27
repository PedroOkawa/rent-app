package com.okawa.pedro.rentapp.presenter.main;

import android.content.Context;
import android.view.View;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.ActivityMainBinding;
import com.okawa.pedro.rentapp.ui.main.MainView;
import com.okawa.pedro.rentapp.util.listener.OnViewTouchListener;
import com.okawa.pedro.rentapp.util.manager.CallManager;

/**
 * Created by pokawa on 26/01/16.
 */
public class MainPresenterImpl implements MainPresenter, OnViewTouchListener {

    private MainView mainView;
    private CallManager callManager;

    private Context context;

    public MainPresenterImpl(MainView mainView, CallManager callManager) {
        this.mainView = mainView;
        this.callManager = callManager;
    }

    @Override
    public void initialize(Context context, ActivityMainBinding binding) {
        /* STORES CONTEXT */
        this.context = context;

        /* INITIALIZE VIEWS */
        binding.setTouchListener(this);
    }

    @Override
    public void onViewTouched(View view) {
        if(view.getId() == R.id.btActivityMainGo) {
            callManager.search(context);
        }
    }
}
