package com.jy.gzg.viewcontrollers.mine.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jy.gzg.R;
import com.jy.gzg.util.T;
import com.jy.gzg.viewcontrollers.mine.ui.CustomDialog;

public class SettingActivity extends AppCompatActivity {
    private ImageView iv_return;
    private RelativeLayout relat_clearcache;
    CustomDialog.Builder mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        iv_return = (ImageView) findViewById(R.id.iv_return);
        relat_clearcache = (RelativeLayout) findViewById(R.id.relat_clearcache);

        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        relat_clearcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LayoutInflater layoutInflater = LayoutInflater.from(SettingActivity.this);
                mDialog = new CustomDialog.Builder(SettingActivity.this);
                mDialog.setMessage("确定清除本地缓存？");
                mDialog.setTitle("提 示");
                mDialog.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 设置你的操作事项
                        T.getInstance().showShort("清除完毕");
                    }
                });
                mDialog.setNegativeButton("取 消", new android.content.DialogInterface
                        .OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.createDialog().show();
            }
        });
    }
}
