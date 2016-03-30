package com.example.wcg.cookbook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.wcg.cookbook.Http.HttpClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by wcg on 16/3/7.
 */

//Android系统自动会为每个程序运行时创建一个Application类的对象且只创建一个，所以Application可以说是单例（singleton）模式的一个类
public class CKApplication extends Application {


    private static CKApplication instance;
    public static CKApplication getInstance() {
        return instance;
    }
    public static ImageLoader imageLoader = ImageLoader.getInstance();


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        HttpClient httpClient = HttpClient.getInstance(getApplicationContext());   // 实例化httpClient客户端


        // 初始化当前网络状态
        boolean isNetAvailable = httpClient.isNetworkAvailable();
        if (isNetAvailable){
            HttpClient.netStatus = CKNetStatus.NetAvailable;
        } else {
            HttpClient.netStatus = CKNetStatus.NetError;
        }

    }

}
