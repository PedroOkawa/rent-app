package com.okawa.pedro.rentapp.presenter.details;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.databinding.ActivityDetailsBinding;
import com.okawa.pedro.rentapp.ui.details.DetailsView;
import com.okawa.pedro.rentapp.util.manager.CallManager;
import com.squareup.picasso.Picasso;

import greendao.Advertisement;

/**
 * Created by pokawa on 28/01/16.
 */
public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView detailsView;
    private CallManager callManager;
    private AdvertisementRepository advertisementRepository;

    private Context context;
    private ActivityDetailsBinding binding;

    private Advertisement advertisement;

    private int flexibleSpaceHeight;
    private int statusBarColor;
    private int toolbarColor;
    private int titleColor;

    public DetailsPresenterImpl(DetailsView detailsView,
                                CallManager callManager,
                                AdvertisementRepository advertisementRepository) {
        this.detailsView = detailsView;
        this.callManager = callManager;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public void restoreData(Intent intent) {
        long id = intent.getLongExtra(CallManager.BUNDLE_DETAILS, -1);
        advertisement = advertisementRepository.selectAdvertisementById(id);

        detailsView.initializeToolbar(advertisement.getFullAddress());
    }

    @Override
    public void initialize(Context context, ActivityDetailsBinding binding) {
        /* STORES CONTEXT */
        this.context = context;

        /* STORES BINDING */
        this.binding = binding;

        /* INITIALIZE VIEWS */
        binding.setAdvertisement(advertisement);

        Picasso.with(context)
                .load(advertisement.getImageLarge())
                .placeholder(R.mipmap.ic_image)
                .centerCrop()
                .fit()
                .into(binding.ivAdvertisement);

        binding.svActivityDetails.setScrollViewCallbacks(new ObservableScrollListener());
        ScrollUtils.addOnGlobalLayoutListener(binding.getRoot(), new GlobalLayoutListener());

        /* DEFINES VARIABLES USED TO CREATE TOOLBAR ANIMATION ON SCROLL */
        flexibleSpaceHeight = context.getResources().getDimensionPixelSize(R.dimen.activity_details_image_height);
        toolbarColor = ContextCompat.getColor(context, R.color.color_primary);
        titleColor = ContextCompat.getColor(context, R.color.white);
        statusBarColor = ContextCompat.getColor(context, R.color.black);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* CREATES THE LINK TO THE TRANSITION ANIMATION */
            ViewCompat.setTransitionName(binding.ivAdvertisement, CallManager.TRANSITION_IMAGE);
            ViewCompat.setTransitionName(binding.viewAdvertisementInfo.rlViewAdvertisementInfo, CallManager.TRANSITION_DETAILS);

            /* MAKES STATUS BAR TRANSPARENT */
            detailsView.initializeStatusBar();
        }
    }

    @Override
    public void openGithub() {
        callManager.github(context);
    }

    /* TOOLBAR ANIMATIONS */
    private void updateScroll(int scrollY) {
        float alpha = Math.min(1, (float) scrollY / (flexibleSpaceHeight - binding.rlAdvertisementInfo.getHeight() - 25));
        binding.toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, toolbarColor));
        binding.toolbar.setTitleTextColor(ScrollUtils.getColorWithAlpha(alpha, titleColor));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailsView.changeStatusBarColor(ScrollUtils.getColorWithAlpha(alpha / 4, statusBarColor));
        }

        binding.rlAdvertisementInfo.setAlpha(1 - ((float) scrollY / (flexibleSpaceHeight / 3)));
    }

    public class GlobalLayoutListener implements Runnable {
        @Override
        public void run() {
            updateScroll(binding.svActivityDetails.getCurrentScrollY());
        }
    }

    public class ObservableScrollListener implements ObservableScrollViewCallbacks {
        @Override
        public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
            updateScroll(scrollY);
        }

        @Override
        public void onDownMotionEvent() {
        }

        @Override
        public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        }
    }
}
