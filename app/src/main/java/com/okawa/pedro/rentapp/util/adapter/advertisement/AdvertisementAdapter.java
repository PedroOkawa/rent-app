package com.okawa.pedro.rentapp.util.adapter.advertisement;

import android.app.Activity;
import android.content.Context;
import android.support.v4.util.Pair;
import android.view.View;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.AdapterAdvertisementBinding;
import com.okawa.pedro.rentapp.util.adapter.common.BindingAdapter;
import com.okawa.pedro.rentapp.util.manager.CallManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import greendao.Advertisement;

/**
 * Created by pokawa on 27/01/16.
 */
public class AdvertisementAdapter extends BindingAdapter<Advertisement, AdapterAdvertisementBinding> {

    private Context context;
    private CallManager callManager;

    public AdvertisementAdapter(List<Advertisement> data, Context context, CallManager callManager) {
        super(data);
        this.context = context;
        this.callManager = callManager;
    }

    @Override
    public int layoutToInflate() {
        return R.layout.adapter_advertisement;
    }

    @Override
    protected void doOnBindViewHolder(BindingViewHolder holder,
                                      AdapterAdvertisementBinding binding,
                                      int position, final Advertisement item) {

        binding.setAdvertisement(item);

        Picasso.with(context)
                .load(item.getImageLarge())
                .placeholder(R.mipmap.ic_image)
                .centerCrop()
                .fit()
                .into(binding.ivAdvertisement);

        /* DEFINE TRANSITION ANIMATION OBJECTS (IMAGE AND RELATIVE LAYOUT) */
        final Pair<View, String> image =
                new Pair<View, String>(binding.ivAdvertisement, CallManager.TRANSITION_IMAGE);

        final Pair<View, String> details =
                new Pair<View, String>(binding.viewAdvertisementInfo.rlViewAdvertisementInfo, CallManager.TRANSITION_DETAILS);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callManager.details((Activity) context, item.getId(), image, details);
            }
        });

    }
}
