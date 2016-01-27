package com.okawa.pedro.rentapp.util.manager;

import android.content.Context;
import android.content.Intent;

import com.okawa.pedro.rentapp.ui.search.SearchActivity;

/**
 * Created by pokawa on 27/01/16.
 */
public class CallManager {

    public void search(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

}
