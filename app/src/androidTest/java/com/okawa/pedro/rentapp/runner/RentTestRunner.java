package com.okawa.pedro.rentapp.runner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.okawa.pedro.rentapp.common.TestRentApp;

/**
 * Created by pokawa on 29/01/16.
 */
public class RentTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestRentApp.class.getName(), context);
    }
}
