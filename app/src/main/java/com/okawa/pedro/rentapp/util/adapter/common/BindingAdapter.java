package com.okawa.pedro.rentapp.util.adapter.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pokawa on 27/01/16.
 */
public abstract class BindingAdapter<T, K extends ViewDataBinding> extends BaseAdapter<T> {

    public BindingAdapter(List<T> data) {
        super(data);
    }

    public abstract @LayoutRes int layoutToInflate();
    protected abstract void doOnBindViewHolder(BindingViewHolder holder,
                                               K binding, int position, T item);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutToInflate(), parent, false);
        return new BindingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BindingViewHolder bindingViewHolder = (BindingViewHolder) holder;
        doOnBindViewHolder(bindingViewHolder, bindingViewHolder.getBinding(), position, getItem(position));
        bindingViewHolder.getBinding().executePendingBindings();
    }

    public class BindingViewHolder extends RecyclerView.ViewHolder {
        K mBinding;

        public BindingViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        public K getBinding() {
            return mBinding;
        }
    }
}
