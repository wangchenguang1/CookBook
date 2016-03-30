package com.example.wcg.cookbook.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by wcg on 16/3/28.
 */
public class CKEmptyView extends RelativeLayout {

    private TextView textView;

    public CKEmptyView(Context context) {
        super(context);
        init(context);
    }

    public CKEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CKEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){

        textView = new TextView(context);
        textView.setText("没有找到相应菜谱!");
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(Gravity.CENTER);
        layoutParams.addRule(CENTER_IN_PARENT);
        addView(textView, layoutParams);

    }
}
