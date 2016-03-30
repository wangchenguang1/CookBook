package com.example.wcg.cookbook.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wcg.cookbook.R;

/**
 * Created by wcg on 16/3/10.
 */
public class CKTopBar extends RelativeLayout {

    private ImageButton leftButton;
    private Button rightButton;
    public TextView title;
    private View bottomLine;

    public interface CKTopBarClickListener{
        public void backClick();
    };
    private CKTopBarClickListener topBarClickListener;
    public void setTopBarClickListener(CKTopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }



    public CKTopBar(Context context) {
        super(context);
        init(context);
    }

    public CKTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CKTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){

        leftButton = new ImageButton(context);
        rightButton = new Button(context);
        title = new TextView(context);
        bottomLine = new View(context);

        title.setText("首页");
        title.setGravity(Gravity.CENTER);  //  文字剧中
        title.setTextSize(16);
        title.setTextColor(Color.BLACK);

        leftButton.setVisibility(INVISIBLE);  // 默认不显示
        rightButton.setVisibility(INVISIBLE); // 默认不显示

        leftButton.setBackgroundResource(R.drawable.common_go_back_image);
        rightButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

        title.setBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomLine.setBackgroundColor(Color.GRAY);

        LayoutParams layoutParams1, layoutParams2,  layoutParams3,  layoutParams4;
        layoutParams1 = new LayoutParams(80, 80);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT );
        layoutParams1.setMargins(20, 0, 0, 0);
        layoutParams1.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(leftButton, layoutParams1);

        layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams2.addRule(ALIGN_PARENT_RIGHT);
        addView(rightButton, layoutParams2);

        layoutParams3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams3.addRule(CENTER_IN_PARENT, TRUE);
        addView(title, layoutParams3);

        layoutParams4 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
        layoutParams4.addRule(ALIGN_PARENT_BOTTOM);
        addView(bottomLine, layoutParams4);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarClickListener.backClick();
            }
        });
    }

    public void setTitleText(String text){
        this.title.setText(text);
    }

    public ImageButton getLeftButton() {
        return leftButton;
    }
}
