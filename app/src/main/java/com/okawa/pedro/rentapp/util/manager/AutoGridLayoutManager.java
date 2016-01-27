package com.okawa.pedro.rentapp.util.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by pokawa on 27/01/16.
 */
public class AutoGridLayoutManager extends GridLayoutManager {

    public AutoGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AutoGridLayoutManager(Context context) {
        super(context,
                context.getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_PORTRAIT ? 1 : 2);
    }

    public AutoGridLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context,
                context.getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_PORTRAIT ? 1 : 2,
                orientation,
                reverseLayout);
    }

    public void changeColumnsNumber(int orientation) {
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            setSpanCount(1);
        } else {
            setSpanCount(2);
        }
    }
}