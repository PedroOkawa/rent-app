package com.okawa.pedro.rentapp.ui.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.di.component.RentAppComponent;

/**
 * Created by pokawa on 26/01/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String BINDING_NULL_EXCEPTION = "Binding may not be null. You must initialize binding before.";

    private ViewDataBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(((RentApp) getApplication()).getComponent());
        setupBinding();
        doOnCreated(savedInstanceState);
    }

    protected abstract @LayoutRes int layoutToInflate();

    protected abstract void setupComponent(RentAppComponent component);

    protected abstract void doOnCreated(Bundle savedInstanceState);

    private void setupBinding() {
        binding = DataBindingUtil.setContentView(this, layoutToInflate());
    }

    private ViewDataBinding getBinding() {
        if(binding == null) {
            throw new NullPointerException(BINDING_NULL_EXCEPTION);
        }
        return binding;
    }
}
