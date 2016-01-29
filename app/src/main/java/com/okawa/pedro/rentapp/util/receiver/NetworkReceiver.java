package com.okawa.pedro.rentapp.util.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.okawa.pedro.rentapp.model.bus.ConnectionEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by pokawa on 29/01/16.
 */
public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            EventBus.getDefault().postSticky(new ConnectionEvent());
        }
    }

}
