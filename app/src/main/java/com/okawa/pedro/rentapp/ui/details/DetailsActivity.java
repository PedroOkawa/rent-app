package com.okawa.pedro.rentapp.ui.details;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.ActivityDetailsBinding;
import com.okawa.pedro.rentapp.di.component.AppComponent;
import com.okawa.pedro.rentapp.di.component.DaggerDetailsComponent;
import com.okawa.pedro.rentapp.di.module.DetailsModule;
import com.okawa.pedro.rentapp.presenter.details.DetailsPresenter;
import com.okawa.pedro.rentapp.ui.common.BaseActivity;

import javax.inject.Inject;

/**
 * Created by pokawa on 27/01/16.
 */
public class DetailsActivity extends BaseActivity implements DetailsView {

    @Inject
    DetailsPresenter detailsPresenter;

    private ActivityDetailsBinding binding;

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_details;
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerDetailsComponent
                .builder()
                .appComponent(component)
                .detailsModule(new DetailsModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(Bundle savedInstanceState) {
        binding = (ActivityDetailsBinding) getBinding();
        detailsPresenter.restoreData(getIntent());
        detailsPresenter.initialize(this, binding);
    }

    @Override
    public void initializeToolbar(String title) {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_action_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void initializeStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void changeStatusBarColor(int color) {
        getWindow().setStatusBarColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_github, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_github:
                detailsPresenter.openGithub();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
