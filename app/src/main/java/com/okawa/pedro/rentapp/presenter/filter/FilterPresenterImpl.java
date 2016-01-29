package com.okawa.pedro.rentapp.presenter.filter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.databinding.ActivityFilterBinding;
import com.okawa.pedro.rentapp.ui.filter.FilterView;
import com.okawa.pedro.rentapp.util.listener.OnViewTouchListener;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import greendao.AdType;

/**
 * Created by pokawa on 28/01/16.
 */
public class FilterPresenterImpl implements FilterPresenter, OnViewTouchListener {

    private FilterView filterView;
    private CallManager callManager;
    private AdTypeRepository adTypeRepository;

    private Context context;

    private AdType adType;

    public FilterPresenterImpl(FilterView filterView,
                               CallManager callManager,
                               AdTypeRepository adTypeRepository) {
        this.filterView = filterView;
        this.callManager = callManager;
        this.adTypeRepository = adTypeRepository;
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
        /* RETRIEVE AD TYPE */
        adType = adTypeRepository.selectAdTypeByName(intent.getStringExtra(CallManager.BUNDLE_FILTER));

        /* INITIALIZE TOOLBAR */
        filterView.initializeToolbar(adType.getDescriptionShort());
    }

    @Override
    public void onViewTouched(View view) {
        if(view.getId() == R.id.tvActivityFilterSearch) {

            /* TODO REMOVE THIS CONDITION

                IMPLEMENTED JUST BECAUSE I WANT TO SHOW THAT IT'S POSSIBLE TO SCALE THE APP FOR ALL
                TYPES OF ADVERTISEMENTS BUT I WONT HAVE ENOUGH TIME TO IMPLEMENT ALL THE REST CALLS

            */

            if(adType.getName().equals(AdType.TYPE_SALE)) {
                callManager.search(context, adType.getDescriptionPlural());
            } else {
                filterView.onError(context.getString(R.string.activity_filter_message_error));
            }
        }
    }
}
