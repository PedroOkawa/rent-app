package com.okawa.pedro.rentapp.di.module;

import com.okawa.pedro.rentapp.di.scope.Activity;
import com.okawa.pedro.rentapp.presenter.main.MainPresenter;
import com.okawa.pedro.rentapp.presenter.main.MainPresenterImpl;
import com.okawa.pedro.rentapp.ui.main.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pokawa on 26/01/16.
 */
@Module
public class MainModule {

    private MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Activity
    @Provides
    public MainView providesMainView() {
        return mainView;
    }

    @Activity
    @Provides
    public MainPresenter providesMainPresenter(MainView mainView) {
        return new MainPresenterImpl(mainView);
    }
}
