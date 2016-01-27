package com.okawa.pedro.rentapp.util.adapter.advertisement;

import android.databinding.ViewDataBinding;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.AdapterAdvertisementBinding;
import com.okawa.pedro.rentapp.util.adapter.common.BindingAdapter;

import java.util.List;

import greendao.Advertisement;

/**
 * Created by pokawa on 27/01/16.
 */
public class AdvertisementAdapter extends BindingAdapter<Advertisement, AdapterAdvertisementBinding> {

    public AdvertisementAdapter(List<Advertisement> data) {
        super(data);
    }

    @Override
    public int layoutToInflate() {
        return R.layout.adapter_advertisement;
    }

    @Override
    protected void doOnBindViewHolder(BindingViewHolder holder,
                                      AdapterAdvertisementBinding binding,
                                      int position, Advertisement item) {

    }
}
