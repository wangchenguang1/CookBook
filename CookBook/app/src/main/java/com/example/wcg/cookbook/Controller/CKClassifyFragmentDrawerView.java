package com.example.wcg.cookbook.Controller;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.wcg.cookbook.CKRootActivity;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyContent;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;
import com.example.wcg.cookbook.R;
import com.example.wcg.cookbook.Utils.Dimension;
import com.example.wcg.cookbook.Utils.Screen;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wcg on 16/3/18.
 */
public class CKClassifyFragmentDrawerView extends RelativeLayout implements AdapterView.OnItemClickListener{


    private GestureDetector mGestureDetector;

    private Context context;

    private CKClassifyFragmentDrawerView myDrawerView;
    private listViewAdapter adapter;

    private int marginX;
    private int X;
    private int Y;
    public ArrayList<CKClassifyContent> arrayList;

    int width = Screen.getWidthPixels(this.getContext());

    public CKClassifyFragmentDrawerView(Context context) {
        super(context);
        init(context);
    }

    public CKClassifyFragmentDrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CKClassifyFragmentDrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){

        this.context = context;
        myDrawerView = this;
//        myDrawerView.setOnTouchListener(this);
        arrayList = new ArrayList<CKClassifyContent>();
        adapter = new listViewAdapter(context, arrayList);


        setBackgroundColor(Color.WHITE);

        int width = Screen.getWidthPixels(context);
        int height = Screen.getHeightPixels(context);
        int h = Dimension.dp2px(context, 120);
        final int distance = Dimension.dp2px(context, 80);

//        View line = new View(context);
//        line.setBackgroundColor(Color.GRAY);
//        LayoutParams lineParameter = new LayoutParams(1, height - h);
//        lineParameter.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        lineParameter.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        addView(line, lineParameter);

        myListView listView = new myListView(context);
        listView.setVerticalScrollBarEnabled(false);
        listView.setOnItemClickListener(this);

        listView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        LayoutParams listViewLayout = new LayoutParams(width, height - h);
        listViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        listViewLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        listViewLayout.setMargins(1, 0, 0, 0);

        listView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        addView(listView, listViewLayout);

        listView.setAdapter(adapter);

        //创建手势监听器对象
//        mGestureDetector = new GestureDetector(context, new MyGestureListener());

    }




    public void request(String id){

        HttpClient httpClient = HttpClient.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id", id);

        httpClient.requestData(HttpClient.classify_url, hashMap, CKClassifyData.class, new HttpClient.CallBack() {
            @Override
            public void success(Object object) {

                CKClassifyData ckClassifyData = (CKClassifyData) object;
                arrayList.clear();
                arrayList.addAll(ckClassifyData.getTngou());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void fail(ErrorModel errorModel) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = this.context;
        System.out.println(position + "");

        CKRootActivity ckRootActivity = (CKRootActivity)context;
        String classId = arrayList.get(position).getId();

        ckRootActivity.checked(classId);



    }
}





/*
* class
* ListViewAdapter
*/
class listViewAdapter extends BaseAdapter{

    private Context context = null;
    private ArrayList<CKClassifyContent> dataArray = new ArrayList<CKClassifyContent>();


    public listViewAdapter(Context context, ArrayList<CKClassifyContent> dataArray) {
        this.context = context;
        this.dataArray = dataArray;
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
            view = (RelativeLayout) LayoutInflater.from(listViewAdapter.this.context).inflate(R.layout.classify_drawer_cell, null);
        }

        CKClassifyContent ckClassifyContent = (CKClassifyContent) getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(ckClassifyContent.getName());
        return view;
    }
}


class myListView extends ListView{

    public myListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myListView(Context context) {
        super(context);
    }

    public myListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // true，事件分发给当前View，并由dispatchTouchEvent进行消费。停止向下传递
    // false， 当前view获取的事件，返回给父view的dispatchTouchEvent，进行消费。
    // 默认，super.dispatchTouchEvent(ev)，事件自动分发给当前onInterceptTouchEvent方法
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
    // true 则将事件拦截，并且交给当前View的onTouchEvent处理
    // false 将事件放行，当前view事件，被传递到子view上。由子view的dispatchTouchEvent 进行分发。
   // super.onInterceptTouchEvent(ev)，事件默认会被拦截,交给当前View的onTouchEvent处理
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return false;
//    }
//
//    // true 接受并消费该事件。
//    // false 从当前View向上传递，并由上层view的 onTouchEvent 来接收
//    // 默认 false
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//
////        getParent().requestDisallowInterceptTouchEvent(true);
//        return false;
//    }


}

