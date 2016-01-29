package com.okawa.pedro.rentapp.util.adapter.adtype;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.databinding.AdapterAdTypeBinding;
import com.okawa.pedro.rentapp.util.adapter.common.BindingAdapter;
import com.okawa.pedro.rentapp.util.manager.CallManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import greendao.AdType;

/**
 * Created by pokawa on 28/01/16.
 */
public class AdTypeAdapter extends BindingAdapter<AdType, AdapterAdTypeBinding> {

    private Context context;
    private CallManager callManager;

    public AdTypeAdapter(List<AdType> data, Context context, CallManager callManager) {
        super(data);
        this.context = context;
        this.callManager = callManager;
    }

    @Override
    public int layoutToInflate() {
        return R.layout.adapter_ad_type;
    }

    @Override
    protected void doOnBindViewHolder(BindingViewHolder holder,
                                      AdapterAdTypeBinding binding,
                                      int position, final AdType item) {
        binding.setAdType(item);

        Picasso.with(context)
                .load(R.drawable.placeholder_ad_type)
                .placeholder(R.drawable.placeholder_ad_type)
                .centerCrop()
                .fit()
                .into(binding.ivAdapterAdType);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callManager.filter(context, item.getName());
            }
        });
    }
}
