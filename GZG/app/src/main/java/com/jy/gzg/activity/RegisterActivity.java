package com.jy.gzg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.jy.gzg.widget.AppConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private ImageView iv_return;
    private EditText ed_account, ed_pwd, ed_repwd, ed_email;
    private Button btn_register;
    private TextView tv_accountinfo, tv_emailinfo;
    private boolean isOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setViewListen();
    }

    /**
     * 初始化各种控件
     */
    private void initView() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        ed_account = (EditText) findViewById(R.id.ed_account);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        ed_repwd = (EditText) findViewById(R.id.ed_repwd);
        ed_email = (EditText) findViewById(R.id.ed_email);
        btn_register = (Button) findViewById(R.id.btn_register);
        tv_accountinfo = (TextView) findViewById(R.id.tv_accountinfo);
        tv_emailinfo = (TextView) findViewById(R.id.tv_emailinfo);
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 检查用户名是否存在
        ed_account.addTextChangedListener(watcherAccount);
        // 检查邮箱是否存在
        ed_email.addTextChangedListener(watcherEmail);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 非空验证
                if (TextUtils.isEmpty(ed_account.getText()) || TextUtils.isEmpty(ed_pwd
                        .getText()) || TextUtils.isEmpty(ed_repwd
                        .getText()) || TextUtils.isEmpty(ed_email
                        .getText())) {
                    AppToast.getInstance().showShort("各项信息不能为空");
                } else if (!(ed_pwd.getText().toString().trim()).equals(ed_repwd.getText()
                        .toString().trim())) {
                    AppToast.getInstance().showShort("两次密码输入不一致");
                } else {
                    String account = ed_account.getText().toString();
                    String password = ed_pwd.getText().toString();
                    String email = ed_email.getText().toString();
                    if (isOk)
                        registerSubmit(account, password, email);
                }
            }
        });
    }

    /**
     * 描述用户名监听
     */
    private TextWatcher watcherAccount = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().equals("")) {
                tv_accountinfo.setText("");
                isOk = false;
                return;
            }
            checkUsername(s.toString());
        }
    };

    /**
     * 描述邮箱监听
     */
    private TextWatcher watcherEmail = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.toString().equals("")) {
                tv_emailinfo.setText("");
                isOk = false;
                return;
            }
            checkEmail(s.toString());
        }
    };

    /**
     * 注册提交
     *
     * @param account
     * @param password
     * @param email
     */
    private void registerSubmit(final String account, final String password, final String email) {
        RequestQueue requestQueue1 = Volley.newRequestQueue(RegisterActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstant
                .REGISTER_SUBMIT, new Response
                .Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject.has("creation_date")) {
                        AppToast.getInstance().showShort("注册成功");
                        finish();
                    } else {
                        // 注册失败
                        String content = jsonObject.getString("content");// 注册失败的情况
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
                map.put("username", account);
                map.put("password", password);
                map.put("email", email);
                return map;
            }
        };
        requestQueue1.add(stringRequest);
    }

    /**
     * 检查用户名
     *
     * @param account
     */
    private void checkUsername(final String account) {
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstant
                .ISEXISTED_ACCOUNT, new Response
                .Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String type = jsonObject.getString("type");
                    if (type.equals("success")) {
                        tv_accountinfo.setText("");
                        isOk = true;
                    } else {
                        tv_accountinfo.setText("该用户名已存在");
                        isOk = false;
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
                map.put("username", account);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    /**
     * 检查邮箱
     *
     * @param email
     */
    private void checkEmail(final String email) {
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstant
                .ISEXISTED_EMAIL, new Response
                .Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String type = jsonObject.getString("type");
                    if (type.equals("success")) {
                        tv_emailinfo.setText("");
                        isOk = true;
                    } else {
                        tv_emailinfo.setText("该邮箱已存在");
                        isOk = false;
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
                map.put("email", email);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
