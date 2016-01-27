package com.okawa.pedro.rentapp.util.manager;

import android.content.Context;
import android.content.Intent;

import com.okawa.pedro.rentapp.ui.search.SearchActivity;

/**
 * Created by pokawa on 27/01/16.
 */
public class CallManager {

    public Intent search(Context context) {
        return new Intent(context, SearchActivity.class);
    }

}
