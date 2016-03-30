package com.example.wcg.cookbook.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wcg.cookbook.CKApplication;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyContent;
import com.example.wcg.cookbook.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by wcg on 16/3/16.
 */
public class CKClassifyFragmentAdapter extends BaseAdapter{

    public int index;
    private Context context = null;
    private ArrayList<CKClassifyContent> dataArray = new ArrayList<CKClassifyContent>();
    private ImageLoader imageLoader = null;

    public CKClassifyFragmentAdapter(Context context, ArrayList<CKClassifyContent> dataArray) {
        this.context = context;
        this.dataArray = dataArray;
        this.imageLoader = CKApplication.imageLoader;

    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    
    @Override
    public int getCount() {
        return dataArray.size();
    }

    @Override
    public Object getItem(int position) {
        return dataArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RelativeLayout view = null;
        if (convertView != null){
            view = (RelativeLayout)convertView;
        } else {
            view = (RelativeLayout) LayoutInflater.from(CKClassifyFragmentAdapter.this.context).inflate(R.layout.classify_fragment_list_cell, null);
        }
        CKClassifyContent data = (CKClassifyContent)getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(data.getName());

        if (position == index){
            view.setBackgroundColor(Color.WHITE);
        } else{
            view.setBackgroundColor(Color.parseColor("#d8d8d8"));
        }

        return view;
    }


}
