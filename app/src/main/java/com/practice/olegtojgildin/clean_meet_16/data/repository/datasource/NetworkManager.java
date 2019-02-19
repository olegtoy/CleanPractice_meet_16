package com.practice.olegtojgildin.clean_meet_16.data.repository.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public final class NetworkManager {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());
    }

}
