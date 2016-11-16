package com.jy.gzg.viewcontrollers.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jy.gzg.R;

public class HelpFeedbackActivity extends AppCompatActivity {
    private ImageView iv_return;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpfeedback);
        iv_return = (ImageView) findViewById(R.id.iv_return);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpFeedbackActivity.this, SubmitSuccessActivity.class);
                startActivity(intent);
            }
        });
    }
}
