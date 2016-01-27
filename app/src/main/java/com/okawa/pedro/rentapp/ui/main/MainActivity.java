package com.okawa.pedro.rentapp.ui.main;

import android.os.Bundle;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.di.component.DaggerMainComponent;
import com.okawa.pedro.rentapp.di.component.RentAppComponent;
import com.okawa.pedro.rentapp.di.module.MainModule;
import com.okawa.pedro.rentapp.presenter.main.MainPresenter;
import com.okawa.pedro.rentapp.ui.common.BaseActivity;

import javax.inject.Inject;

/**
 * Created by pokawa on 26/01/16.
 */
public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupComponent(RentAppComponent component) {
        DaggerMainComponent
                .builder()
                .rentAppComponent(component)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(Bundle savedInstanceState) {
        mainPresenter.setupView();
    }
}
