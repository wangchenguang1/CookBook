package com.example.wcg.cookbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;

import java.util.HashMap;

public class BaseActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 请求示例
    }

    // 显示错误提示
    public void showErrorMessage(String errorMsg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(errorMsg);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 显示 loading
    private ProgressBar creatLoading(Activity activity){
        FrameLayout rootContainer = (FrameLayout)activity.findViewById(android.R.id.content);

        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams1.gravity = Gravity.CENTER;
        View backView = new View(this);
        backView.setBackgroundColor(0x00FFFFFF);  //全透明
        rootContainer.addView(backView);

        
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        progressBar = new ProgressBar(activity);
        progressBar.setLayoutParams(layoutParams);
        progressBar.setVisibility(View.GONE);
        rootContainer.addView(progressBar);
        return progressBar;
    }

    public void showLoading(){
        if (progressBar == null){
            creatLoading(this);
        }
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hidenLoading(){
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        hidenLoading();
    }
}
