package com.okawa.pedro.rentapp.ui.filter;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.ActivityFilterBinding;
import com.okawa.pedro.rentapp.di.component.DaggerFilterComponent;
import com.okawa.pedro.rentapp.di.component.RentAppComponent;
import com.okawa.pedro.rentapp.di.module.FilterModule;
import com.okawa.pedro.rentapp.presenter.filter.FilterPresenter;
import com.okawa.pedro.rentapp.ui.common.BaseActivity;

import javax.inject.Inject;

/**
 * Created by pokawa on 28/01/16.
 */
public class FilterActivity extends BaseActivity implements FilterView {

    private ActivityFilterBinding binding;

    @Inject
    FilterPresenter filterPresenter;

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_filter;
    }

    @Override
    protected void setupComponent(RentAppComponent component) {
        DaggerFilterComponent
                .builder()
                .rentAppComponent(component)
                .filterModule(new FilterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(Bundle savedInstanceState) {
        binding = (ActivityFilterBinding) getBinding();
        filterPresenter.restoreData(getIntent());
        filterPresenter.initialize(this, binding);
    }

    @Override
    public void initializeToolbar(String title) {
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_action_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
