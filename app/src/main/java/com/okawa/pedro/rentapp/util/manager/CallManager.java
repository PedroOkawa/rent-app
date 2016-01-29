package com.okawa.pedro.rentapp.util.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.okawa.pedro.rentapp.ui.details.DetailsActivity;
import com.okawa.pedro.rentapp.ui.filter.FilterActivity;
import com.okawa.pedro.rentapp.ui.search.SearchActivity;

/**
 * Created by pokawa on 27/01/16.
 */
public class CallManager {

    public static final String BUNDLE_FILTER = "filter";
    public void filter(Context context, String adType) {
        Intent intent = new Intent(context, FilterActivity.class);
        intent.putExtra(BUNDLE_FILTER, adType);
        context.startActivity(intent);
    }

    public static final String BUNDLE_SEARCH = "search";
    public void search(Context context, String adType) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(BUNDLE_SEARCH, adType);
        context.startActivity(intent);
    }

    public static final String TRANSITION_IMAGE = "image";
    public static final String TRANSITION_DETAILS = "details";
    public static final String BUNDLE_DETAILS = "details";
    public void details(Activity activity, long advertisementId, Pair<View, String>... params) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, params);

        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(BUNDLE_DETAILS, advertisementId);

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

}
