package com.okawa.pedro.rentapp.presenter.filter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.ActivityFilterBinding;
import com.okawa.pedro.rentapp.ui.filter.FilterView;
import com.okawa.pedro.rentapp.util.listener.OnViewTouchListener;
import com.okawa.pedro.rentapp.util.manager.CallManager;

/**
 * Created by pokawa on 28/01/16.
 */
public class FilterPresenterImpl implements FilterPresenter, OnViewTouchListener {

    private FilterView filterView;
    private CallManager callManager;

    private Context context;

    public FilterPresenterImpl(FilterView filterView, CallManager callManager) {
        this.filterView = filterView;
        this.callManager = callManager;
    }

    @Override
    public void initialize(Context context, ActivityFilterBinding binding) {
        /* STORE CONTEXT */
        this.context = context;

        /* INITIALIZE VIEWS */
        binding.setTouchListener(this);
    }

    @Override
    public void restoreData(Intent intent) {
        /* INITIALIZE TOOLBAR */
        filterView.initializeToolbar(intent.getStringExtra(CallManager.BUNDLE_FILTER));
    }

    @Override
    public void onViewTouched(View view) {
        if(view.getId() == R.id.tvActivityFilterSearch) {
            callManager.search(context);
        }
    }
}
