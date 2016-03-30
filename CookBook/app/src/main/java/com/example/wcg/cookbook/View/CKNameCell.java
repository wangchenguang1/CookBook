package com.example.wcg.cookbook.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wcg.cookbook.R;
import com.example.wcg.cookbook.Utils.Dimension;

import static com.example.wcg.cookbook.R.*;
import static com.example.wcg.cookbook.R.drawable.pay_checkout_arrow_right;

/**
 * Created by wcg on 16/3/28.
 */
public class CKNameCell extends RelativeLayout implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    private ImageView arrowImage;


    public interface CKNameCellListener{
        public void click();
    };
    private CKNameCellListener ckNameCellListener;

    public void setCkNameCellListener(CKNameCellListener ckNameCellListener) {
        this.ckNameCellListener = ckNameCellListener;
    }



    public CKNameCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CKNameCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CKNameCell(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context){

        setOnClickListener(this);

        Drawable drawable = context.getResources().getDrawable(R.drawable.pay_checkout_arrow_right);

        imageView = new ImageView(context);
        arrowImage = new ImageView(context);
        arrowImage.setImageDrawable(drawable);

        textView = new TextView(context);
        textView.setText("我的收藏");
//        textView.setBackgroundColor(Color.parseColor("#db384c"));
//
        int ImageWidth = Dimension.dp2px(context, 35);
        int marginWidth = Dimension.dp2px(context, 10);
//        imageView.setBackgroundColor(Color.parseColor("#db384c"));
        imageView.setImageResource(R.drawable.detail_collected);
        LayoutParams layoutParams1 = new LayoutParams(ImageWidth, ImageWidth);
        layoutParams1.setMargins(marginWidth, marginWidth / 2, marginWidth, marginWidth / 2);
        addView(imageView, layoutParams1);

        LayoutParams layoutParams2 = new LayoutParams(ImageWidth/2, ImageWidth/2);
        layoutParams2.setMargins(0, 0, marginWidth, 0);
        layoutParams2.addRule(ALIGN_PARENT_RIGHT);
        layoutParams2.addRule(CENTER_VERTICAL);
//        arrowImage.setBackgroundColor(Color.parseColor("#db384c"));
        addView(arrowImage, layoutParams2);

        LayoutParams layoutParams3 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams3.addRule(CENTER_VERTICAL);
        layoutParams3.addRule(Gravity.CENTER);
        layoutParams3.setMargins(ImageWidth + marginWidth * 2, 0, ImageWidth/2 + marginWidth * 2, 0);
        addView(textView, layoutParams3);

    }
    
    @Override
    public void onClick(View v) {

        if (this.ckNameCellListener != null) {
            this.ckNameCellListener.click();
        }
    }
}
