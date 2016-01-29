package com.okawa.pedro.rentapp.presenter.details;

import android.content.Context;
import android.content.Intent;

import com.okawa.pedro.rentapp.databinding.ActivityDetailsBinding;

/**
 * Created by pokawa on 28/01/16.
 */
public interface DetailsPresenter {

    void restoreData(Intent intent);
    void initialize(Context context, ActivityDetailsBinding binding);
    void openGithub();

}
