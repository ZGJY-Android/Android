package com.jy.gzg.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.home.ui.XCFlowLayout;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class ProductCommentHeader extends RelativeLayout {
    Context context = null;
    // 显示的文字
    private String[] mDatas = new String[]{"很好用(5)",
            "很好用(5)",
            "很好用(5)",
            "很好用(5)",
            "不好用(5)"};

    private XCFlowLayout mFlowLayout;

    public ProductCommentHeader(final Context context) {
        super(context);
        this.context = context;
        View view = inflate(context, R.layout.productcomment_header, this);
        mFlowLayout = (XCFlowLayout) view.findViewById(R.id.xcflow_layout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 15;
        lp.rightMargin = 15;
        lp.topMargin = 15;
        lp.bottomMargin = 15;
        for (int i = 0; i < mDatas.length; i++) {
            final TextView tv_text = new TextView(context);
            tv_text.setText(mDatas[i]);
            tv_text.setTextSize(12);
            tv_text.setTextColor(Color.parseColor("#111111"));
            tv_text.setBackgroundResource(R.drawable.shape_red_bck);
            tv_text.setPadding(40, 20, 40, 20);
            mFlowLayout.addView(tv_text, lp);

            // 设置点击事件
            tv_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, tv_text.getText().toString(), Toast
                            .LENGTH_SHORT).show();
                }
            });
        }
    }
}
