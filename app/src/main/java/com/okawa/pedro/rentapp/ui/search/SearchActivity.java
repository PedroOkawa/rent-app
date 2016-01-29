package com.okawa.pedro.rentapp.ui.search;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.ActivitySearchBinding;
import com.okawa.pedro.rentapp.di.component.AppComponent;
import com.okawa.pedro.rentapp.di.component.DaggerSearchComponent;
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
    protected void setupComponent(AppComponent component) {
        DaggerSearchComponent
                .builder()
                .appComponent(component)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(Bundle savedInstanceState) {
        /* DEFINE START TRANSITION ANIMATION */
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);

        /* INITIALIZE ACTIVITY */
        binding = (ActivitySearchBinding) getBinding();
        searchPresenter.restoreData(getIntent());
        searchPresenter.initialize(this, binding);
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
    public void showProgress() {
        binding.srlActivitySearch.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
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
                searchPresenter.openGithub();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /* DEFINE END TRANSITION ANIMATION */
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }
}
