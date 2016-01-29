package com.okawa.pedro.rentapp.presenter.filter;

import android.content.Context;
import android.content.Intent;

import com.okawa.pedro.rentapp.databinding.ActivityFilterBinding;

/**
 * Created by pokawa on 28/01/16.
 */
public interface FilterPresenter {

    void initialize(Context context, ActivityFilterBinding binding);
    void restoreData(Intent intent);
    void opentGithub();

}
