package com.okawa.pedro.rentapp.util.adapter.common;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by pokawa on 27/01/16.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    private List<T> mData;

    public BaseAdapter(List<T> data) {
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public T getItem(int position) {
        if (mData == null) {
            return null;
        }
        return mData.get(position);
    }

    public void addDataSet(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void add(int position, T item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(T item) {
        mData.remove(item);
        notifyItemRemoved(getItemPosition(item));
    }

    public void reset() {
        mData.clear();
        notifyDataSetChanged();
    }

    public int getItemPosition(T itemToFind) {
        return mData.indexOf(itemToFind);
    }

    public void moveEntity(int fromPosition, int toPosition) {
        move(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    private void move(List<T> data, int a, int b) {
        T temp = data.remove(a);
        data.add(b, temp);
    }
}
