package com.okawa.pedro.rentapp.ui.search;

import android.os.Bundle;

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
        searchPresenter.setupViews((ActivitySearchBinding) getBinding());
    }
}
