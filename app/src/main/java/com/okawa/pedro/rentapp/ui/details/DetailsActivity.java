package com.okawa.pedro.rentapp.ui.details;

import android.os.Bundle;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.di.component.RentAppComponent;
import com.okawa.pedro.rentapp.ui.common.BaseActivity;

/**
 * Created by pokawa on 27/01/16.
 */
public class DetailsActivity extends BaseActivity {

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_details;
    }

    @Override
    protected void setupComponent(RentAppComponent component) {

    }

    @Override
    protected void doOnCreated(Bundle savedInstanceState) {

    }
}
