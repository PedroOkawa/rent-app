package com.okawa.pedro.rentapp.util.adapter.advertisement;

import android.content.Context;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.AdapterAdvertisementBinding;
import com.okawa.pedro.rentapp.util.adapter.common.BindingAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import greendao.Advertisement;

/**
 * Created by pokawa on 27/01/16.
 */
public class AdvertisementAdapter extends BindingAdapter<Advertisement, AdapterAdvertisementBinding> {

    private Context context;

    public AdvertisementAdapter(List<Advertisement> data, Context context) {
        super(data);
        this.context = context;
    }

    @Override
    public int layoutToInflate() {
        return R.layout.adapter_advertisement;
    }

    @Override
    protected void doOnBindViewHolder(BindingViewHolder holder,
                                      AdapterAdvertisementBinding binding,
                                      int position, Advertisement item) {

        binding.setAdvertisement(item);

        Picasso.with(context)
                .load(item.getImageLarge())
                .placeholder(R.mipmap.ic_image)
                .centerCrop()
                .fit()
                .into(binding.ivAdapterAdvertisement);

    }
}
