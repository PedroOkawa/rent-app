package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.di.module.MainModule;
import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.main.MainPresenter;
import com.okawa.pedro.rentapp.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by pokawa on 26/01/16.
 */
@Activity
@Component(dependencies = RentAppComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    /* PRESENTER */
    MainPresenter providesMainPresenter();
}