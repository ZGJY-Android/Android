package com.jy.gzg.viewcontrollers.mine.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jy.gzg.R;

public class SubmitSuccessActivity extends AppCompatActivity {
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitsuccess);
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
