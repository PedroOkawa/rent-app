package com.okawa.pedro.rentapp.presenter.search;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.okawa.pedro.rentapp.databinding.ActivitySearchBinding;

/**
 * Created by pokawa on 27/01/16.
 */
public interface SearchPresenter {

    void initialize(Context context, ActivitySearchBinding binding);
    void restoreData(Intent intent);
    void onOrientationChanged(int orientation);
    void openGithub();

}
