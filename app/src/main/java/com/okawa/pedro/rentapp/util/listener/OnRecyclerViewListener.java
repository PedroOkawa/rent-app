package com.okawa.pedro.rentapp.util.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by pokawa on 27/01/16.
 */
public abstract class OnRecyclerViewListener extends RecyclerView.OnScrollListener {
    public static final int LIST_THRESHOLD = 10;

    private int previousTotal = 0;
    private boolean loading = true;
    private GridLayoutManager gridLayoutManager;

    public abstract void onVisibleThreshold();

    public OnRecyclerViewListener(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
        onVisibleThreshold();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = gridLayoutManager.getItemCount();
        int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + LIST_THRESHOLD)) {
            onVisibleThreshold();
            loading = true;
        }
    }
}
