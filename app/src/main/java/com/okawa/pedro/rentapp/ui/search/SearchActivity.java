package com.okawa.pedro.rentapp.ui.search;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.ActivitySearchBinding;
import com.okawa.pedro.rentapp.di.component.DaggerSearchComponent;
import com.okawa.pedro.rentapp.di.component.RentAppComponent;
import com.okawa.pedro.rentapp.di.module.SearchModule;
import com.okawa.pedro.rentapp.presenter.search.SearchPresenter;
import com.okawa.pedro.rentapp.ui.common.BaseActivity;

import javax.inject.Inject;

/**
 * Created by pokawa on 27/01/16.
 */
public class SearchActivity extends BaseActivity implements SearchView {

    private ActivitySearchBinding binding;

    @Inject
    SearchPresenter searchPresenter;

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_search;
    }

    @Override
    protected void setupComponent(RentAppComponent component) {
        DaggerSearchComponent
                .builder()
                .rentAppComponent(component)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(Bundle savedInstanceState) {
        binding = (ActivitySearchBinding) getBinding();
        searchPresenter.initialize(this, binding);
    }

    @Override
    public void showRefresh() {
        binding.srlActivitySearch.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        binding.srlActivitySearch.setRefreshing(false);
        binding.setLoading(false);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        searchPresenter.onOrientationChanged(newConfig.orientation);
    }
}
