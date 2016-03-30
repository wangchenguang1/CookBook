package com.example.wcg.cookbook.Controller;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wcg.cookbook.CKApplication;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKListContent;
import com.example.wcg.cookbook.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

/**
 * Created by wcg on 16/3/24.
 */
public class CKListFragmentAdapter  extends BaseAdapter{

    private Context context = null;
    private ArrayList<CKListContent> arrayList  = new ArrayList<CKListContent>();

    public CKListFragmentAdapter(ArrayList<CKListContent> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CKListContent data = (CKListContent) getItem(position);
        LinearLayout view = null;
        if (convertView != null){
            view = (LinearLayout)convertView;
        } else {
            view = (LinearLayout) LayoutInflater.from(CKListFragmentAdapter.this.context).inflate(R.layout.list_fragment_cell, null);
        }


        LinearLayout tagsView = (LinearLayout) view.findViewById(R.id.tags);
        tagsView.removeAllViews();
        String sTag = data.getKeywords();
        String[] tagArray;
        if (sTag != null) {
            tagArray = sTag.split(" ");
        } else {
            tagArray = null;
        }

        for (int i = 0; i < tagArray.length; i++){
            TextView tag = new TextView(context);
            tag.setText(tagArray[i]);
            tag.setPadding(10, 2, 10, 2);
            tag.setGravity(Gravity.CENTER);
            tag.setBackgroundResource(R.drawable.shape_textview);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8,0,8,0);
            tagsView.addView(tag,layoutParams);
        }

        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView content = (TextView) view.findViewById(R.id.content);
        String food = "食材: "+data.getFood();
        nameView.setText(data.getName());
        content.setText(food);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        String url = HttpClient.pre_image_url + data.getImg();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        CKApplication.imageLoader.displayImage(url,imageView, options);


        return view;
    }

}
