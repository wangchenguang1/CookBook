package com.example.wcg.cookbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.toolbox.ImageLoader;
import com.example.wcg.cookbook.Controller.CKClassifyFragment;
import com.example.wcg.cookbook.Controller.CKDetailFragment;
import com.example.wcg.cookbook.Controller.CKListFragment;
import com.example.wcg.cookbook.Controller.CKNameFragment;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;
import com.example.wcg.cookbook.Utils.Dimension;
import com.example.wcg.cookbook.Utils.Screen;
import com.example.wcg.cookbook.View.CKTopBar;

import java.util.HashMap;

/**
 * Created by wcg on 16/3/8.
 */
public class CKRootActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rootTabs;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private CKClassifyFragment ckClassifyFragment;
    private CKDetailFragment ckDetailFragment;
    private CKNameFragment ckNameFragment;
    private CKListFragment ckListFragment;

    private CKTopBar ckTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ckroot);
        ckTopBar = (CKTopBar)findViewById(R.id.topBar);

        ckClassifyFragment = new  CKClassifyFragment();
        ckDetailFragment = new  CKDetailFragment();
        ckNameFragment = CKNameFragment.newInstance();
        ckListFragment = CKListFragment.newInstance();
        ckListFragment.passedId = "0";
        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rootContent, ckClassifyFragment);
        fragmentTransaction.add(R.id.rootContent, ckListFragment);
        fragmentTransaction.add(R.id.rootContent, ckDetailFragment);
        fragmentTransaction.add(R.id.rootContent, ckNameFragment);
        fragmentTransaction.commit();

        rootTabs = (RadioGroup)findViewById(R.id.rootTabs);
        rootTabs.setOnCheckedChangeListener(this);

        ((RadioButton)findViewById(R.id.tab1)).setChecked(true);

    }

    public void checked(String id){
        ((RadioButton)findViewById(R.id.tab2)).setChecked(true);
        ckListFragment.passedId = id;

    }

    private void request(){

        // 请求示例
        HttpClient httpClient = HttpClient.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id", "0");
        if (HttpClient.netStatus ==CKNetStatus.NetAvailable){
            showLoading();
        }
        httpClient.requestData(HttpClient.classify_url, hashMap, CKClassifyData.class, new HttpClient.CallBack() {
            @Override
            public void success(Object object) {
                hidenLoading();
                CKClassifyData ckClassifyData = (CKClassifyData) object;
            }

            @Override
            public void fail(ErrorModel errorModel) {
                hidenLoading();
                showErrorMessage(errorModel.getErrMsg());
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        this.hidenLoading();
        switch (checkedId){
            case R.id.tab1:

//                Bundle bundle = new Bundle();
//                bundle.putString("name", "classify");
//                ckClassifyFragment.setArguments(bundle);

                ckTopBar.setTitleText("首页");
                fragmentTransaction = fragmentManager.beginTransaction();
//                if (!ckClassifyFragment.isAdded()){
//                    fragmentTransaction.add(R.id.rootContent, ckClassifyFragment);
//                }
                fragmentTransaction.show(ckClassifyFragment)
                        .hide(ckListFragment)
                        .hide(ckNameFragment)
                        .hide(ckDetailFragment);
                fragmentTransaction.commit();

                break;
            case R.id.tab2:

                ckTopBar.setTitleText("菜谱");
                fragmentTransaction = fragmentManager.beginTransaction();


//                if (!ckListFragment.isAdded()){
//                    fragmentTransaction.add(R.id.rootContent, ckListFragment);
//                }
                fragmentTransaction.show(ckListFragment)
                        .hide(ckClassifyFragment)
                        .hide(ckNameFragment)
                        .hide(ckDetailFragment);
                fragmentTransaction.commit();




                break;
//            case R.id.tab3:
//
//                ckTopBar.setTitleText("购物车");
//                fragmentTransaction = fragmentManager.beginTransaction();
//
////
////                if (!ckDetailFragment.isAdded()){
////                    fragmentTransaction.add(R.id.rootContent, ckDetailFragment);
////                }
//                fragmentTransaction.show(ckDetailFragment)
//                        .hide(ckListFragment)
//                        .hide(ckNameFragment)
//                        .hide(ckClassifyFragment);
//                fragmentTransaction.commit();
//
//                break;
            case R.id.tab4:

                ckTopBar.setTitleText("我的");
                fragmentTransaction = fragmentManager.beginTransaction();

//                if (!ckNameFragment.isAdded()){
//                    fragmentTransaction.add(R.id.rootContent, ckNameFragment);
//                }
                fragmentTransaction.show(ckNameFragment)
                        .hide(ckListFragment)
                        .hide(ckClassifyFragment)
                        .hide(ckDetailFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
