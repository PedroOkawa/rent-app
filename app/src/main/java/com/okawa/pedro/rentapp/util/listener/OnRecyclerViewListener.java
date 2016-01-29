package com.okawa.pedro.rentapp.util.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by pokawa on 27/01/16.
 */
public abstract class OnRecyclerViewListener extends RecyclerView.OnScrollListener {
    public static final int LIST_THRESHOLD = 5;

    private int previousTotal = 0;

    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItem;

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

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = gridLayoutManager.getItemCount();
        firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

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

    public void reset() {
        visibleItemCount = 0;
        totalItemCount = 0;
        previousTotal = 0;
    }
}
