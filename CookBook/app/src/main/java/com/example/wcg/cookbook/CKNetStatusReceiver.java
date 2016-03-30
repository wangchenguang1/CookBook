package com.example.wcg.cookbook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.wcg.cookbook.Http.HttpClient;

/**
 * Created by wcg on 16/3/8.
 */
public class CKNetStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();

        if (activeInfo == null){
            HttpClient.netStatus = CKNetStatus.NetError;
            Toast.makeText(context, "网络连接错误", 1).show();
        } else {
            HttpClient.netStatus = CKNetStatus.NetAvailable;
            Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
        }

    }
}
