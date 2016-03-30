package com.example.wcg.cookbook.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wcg.cookbook.BaseActivity;
import com.example.wcg.cookbook.CKApplication;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKDetailContent;
import com.example.wcg.cookbook.Model.Classfy.CKListData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;
import com.example.wcg.cookbook.R;
import com.example.wcg.cookbook.View.CKEmptyView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.HashMap;

/**
 * Created by wcg on 16/3/16.
 */
public class CKDetailActivity extends BaseActivity {

    private ImageView imageView;
    private TextView name;
    private TextView count1;
    private TextView count2;
    private TextView count3;
    private TextView description;
    private TextView message;
    private CKEmptyView emptyView;
    private ScrollView scrollView;
    private CKDetailContent ckDetailContent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.name);
        count1 = (TextView) findViewById(R.id.count1);
        count2 = (TextView) findViewById(R.id.count2);
        count3 = (TextView) findViewById(R.id.count3);
        description = (TextView) findViewById(R.id.descrption);
        message = (TextView) findViewById(R.id.msg);
        emptyView = (CKEmptyView) findViewById(R.id.emptyView);
        scrollView = (ScrollView) findViewById(R.id.scrollview);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.i("id:", id);
        request(id);
    }

    private void request(String id){

        showLoading();
        HttpClient httpClient = HttpClient.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id", id);
        httpClient.requestData(HttpClient.detail_url, hashMap, CKDetailContent.class, new HttpClient.CallBack() {
            @Override
            public void success(Object object) {
                hidenLoading();

                ckDetailContent = (CKDetailContent)object;
                String msg = ckDetailContent.getMessage();

                name.setText(ckDetailContent.getName());
                message.setText(Html.fromHtml(msg));
                count1.setText("访问 :"+ckDetailContent.getCount() + "");
                count2.setText("评论 :"  + ckDetailContent.getRcount() + "");
                count3.setText("收藏 :" + ckDetailContent.getFcount() + "");
                description.setText(ckDetailContent.getDescription());

                String url = HttpClient.pre_image_url + ckDetailContent.getImg();
                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .cacheOnDisk(true)
                        .cacheInMemory(true)
                        .build();
                CKApplication.imageLoader.displayImage(url, imageView, options);

            }
            
            @Override
            public void fail(ErrorModel errorModel) {
                Toast.makeText(CKDetailActivity.this, errorModel.getErrMsg(), Toast.LENGTH_SHORT).show();
                hidenLoading();
                if (ckDetailContent == null) {
                    emptyView.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        hidenLoading();
    }
}
