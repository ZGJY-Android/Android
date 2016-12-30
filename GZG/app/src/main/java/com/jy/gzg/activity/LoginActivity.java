package com.jy.gzg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.ZYWUtil;
import com.jy.gzg.widget.AppConstant;
import com.jy.gzg.widget.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextView tv_return,// 返回
            tv_register;// 注册
    private EditText ed_account, ed_password;
    private Button btn_login;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        ZYWUtil.init(LoginActivity.this);
        intent = getIntent();
        setViewListen();
    }

    /**
     * 初始化各种控件
     */
    private void initView() {
        tv_return = (TextView) findViewById(R.id.tv_return);
        tv_register = (TextView) findViewById(R.id.tv_register);
        ed_account = (EditText) findViewById(R.id.ed_account);
        ed_password = (EditText) findViewById(R.id.ed_password);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         *  点击登录按钮
         */
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 非空验证
                if (TextUtils.isEmpty(ed_account.getText()) || TextUtils.isEmpty(ed_password
                        .getText())) {
                    AppToast.getInstance().showShort("用户名和密码不能为空");
                } else {
                    String account = ed_account.getText().toString();
                    String password = ed_password.getText().toString();
                    connectionLogin(account, password);
                }
            }
        });

        /**
         * 注册
         */
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 测试服务器连接
     */
    private void connectionLogin(final String uname, final String upass) {
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstant
                .USER_LOGIN, new Response
                .Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject.has("login_date")) {
                        AppToast.getInstance().showShort("登录成功");
                        // 登录成功
                        String id = jsonObject.getString("id");// 用户id
                        Constant.UNAME = uname;
                        // 存入首选项
                        ZYWUtil.writeData(Constant.SP_FILE, Constant.SP_UID, id + "");
                        ZYWUtil.writeData(Constant.SP_FILE, Constant.SP_UNAME, uname);
                        ZYWUtil.writeData(Constant.SP_FILE, Constant.SP_UPASS, upass);

                        Bundle bundle = new Bundle();
                        bundle.putString("uname", uname);// 将用户名传递回去
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                        finish();
                    } else {
                        // 登录失败
                        String content = jsonObject.getString("content");// 登录失败的情况
                        AppToast.getInstance().showShort(content);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                AppToast.getInstance().showShort("连接服务器失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", uname);
                map.put("password", upass);
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }
}
