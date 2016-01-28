package com.okawa.pedro.rentapp.util.manager;

import android.content.Context;
import android.content.Intent;

import com.okawa.pedro.rentapp.ui.filter.FilterActivity;
import com.okawa.pedro.rentapp.ui.search.SearchActivity;

/**
 * Created by pokawa on 27/01/16.
 */
public class CallManager {

    public static final String BUNDLE_FILTER = "filter";
    public void filter(Context context, String filter) {
        Intent intent = new Intent(context, FilterActivity.class);
        intent.putExtra(BUNDLE_FILTER, filter);
        context.startActivity(intent);
    }

    public void search(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

}
