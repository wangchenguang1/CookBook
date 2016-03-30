package com.example.wcg.cookbook.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wcg.cookbook.CKNetStatus;
import com.example.wcg.cookbook.CKRootActivity;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyContent;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;
import com.example.wcg.cookbook.R;
import com.example.wcg.cookbook.Utils.Dimension;
import com.example.wcg.cookbook.Utils.Screen;
import com.example.wcg.cookbook.View.CKTopBar;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wcg on 16/3/9.
 */
public class CKClassifyFragment extends Fragment implements AdapterView.OnItemClickListener {


    public static boolean isOpenDrawer = false;  // 是否开启了右侧抽屉

    private ListView listView;
    private ArrayList<CKClassifyContent> dataArray;
    private CKClassifyFragmentAdapter ckClassifyFragmentAdapter = null;
    public CKClassifyFragmentDrawerView drawerView = null;

    public static CKClassifyFragment newInstance(){

        CKClassifyFragment fragment = new CKClassifyFragment();
        return fragment;
    }

    public CKClassifyFragment(){

    }

    // 创建方法
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("onCreateClassify");
        dataArray = new ArrayList<CKClassifyContent>();
        ckClassifyFragmentAdapter = new CKClassifyFragmentAdapter(this.getContext(), dataArray);


    }

    // 创建视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        System.out.println("onCreateViewCLassify");
        View view = inflater.inflate(R.layout.fragment_classify, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(ckClassifyFragmentAdapter);
        listView.setOnItemClickListener(this);


        int width = Screen.getWidthPixels(this.getContext());
        int height = Screen.getHeightPixels(this.getContext());
        int h = Dimension.dp2px(this.getContext(), 92);
        final int distance1 = Dimension.dp2px(this.getContext(), 80);
        drawerView = new CKClassifyFragmentDrawerView(this.getContext());
        FrameLayout frameLayout = (FrameLayout)view;
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(width - distance1 , height - h);
        frameLayoutParams.setMargins(distance1 + 1,0,0,0);
        frameLayout.addView(drawerView, frameLayoutParams);

        ckClassifyFragmentAdapter.index = 0;
        request();
        return view;

    }


    //  网络请求
    private void request(){

        final CKRootActivity ckRootActivity = (CKRootActivity)getActivity();

        // 请求示例
        HttpClient httpClient = HttpClient.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id", "0");
        if (HttpClient.netStatus == CKNetStatus.NetAvailable){
            ckRootActivity.showLoading();
        }
        httpClient.requestData(HttpClient.classify_url, hashMap, CKClassifyData.class, new HttpClient.CallBack() {
            @Override
            public void success(Object object) {
                ckRootActivity.hidenLoading();
                CKClassifyData ckClassifyData = (CKClassifyData) object;

                dataArray.addAll(0, ckClassifyData.getTngou());
                ckClassifyFragmentAdapter.notifyDataSetChanged();

                drawerView.request(dataArray.get(0).getId());


            }

            @Override
            public void fail(ErrorModel errorModel) {
                ckRootActivity.hidenLoading();
                Toast.makeText(getContext(), errorModel.getErrMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // 当Fragment所在的Activity启动完成后调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        System.out.println("onActivityCreated");
    }


    // listView 点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("1234567890");

        ckClassifyFragmentAdapter.setIndex(position);
        ckClassifyFragmentAdapter.notifyDataSetInvalidated();
        listView.setSelection(position);

        final int width = Screen.getWidthPixels(this.getContext());
        final int distance = Dimension.dp2px(this.getContext(), 80);

        if (1 == 0) {

            FrameLayout.LayoutParams frameLayoutParams1 = (FrameLayout.LayoutParams) drawerView.getLayoutParams();
            AnimationSet translate_animation_set = new AnimationSet(true);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, -(width - distance), 0, 0);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.i("start", "start");
                }
                //动画执行完成， 标记,移除动画， 更改物理位置
                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.i("end", "end");
                    isOpenDrawer = true;

                    FrameLayout.LayoutParams frameLayoutParams = (FrameLayout.LayoutParams) drawerView.getLayoutParams();
                    frameLayoutParams.setMargins(distance,0 ,0,0);
                    drawerView.setLayoutParams(frameLayoutParams);
                    drawerView.clearAnimation();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                    Log.i("repeat", "repeat");
                }
            });
            translate_animation_set.addAnimation(translateAnimation);
            translate_animation_set.setDuration(500);
            translate_animation_set.setFillAfter(true);
            drawerView.startAnimation(translate_animation_set);
        }

        drawerView.request(dataArray.get(position).getId());

    }

}
