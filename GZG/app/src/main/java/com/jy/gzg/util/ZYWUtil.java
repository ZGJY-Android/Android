package com.jy.gzg.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class ZYWUtil {
    private ZYWUtil() {
    }

    public static Context context;

    public static void init(Context cxt) {
        context = cxt;
    }

    /**
     * 项首选项中存入一个键值对
     *
     * @param filename 文件名
     * @param key
     * @param value
     */
    public static void writeData(String filename,
                                 String key, String value) {
        //实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
        SharedPreferences mySharePreferences
                = context.getSharedPreferences(filename, Activity.MODE_PRIVATE);

        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = mySharePreferences.edit();

        //用putString的方法保存数据
        editor.putString(key, value);

        //提交数据
        editor.commit();
    }

    /**
     * 从首选项中取出一个值
     *
     * @param filename 文件名
     * @param key
     * @return
     */
    public static String readData(String filename,
                                  String key) {
        //实例化SharedPreferences对象,参数1是存储文件的名称，参数2是文件的打开方式，当文件不存在时，直接创建，如果存在，则直接使用
        SharedPreferences mySharePreferences
                = context.getSharedPreferences(filename, Activity.MODE_PRIVATE);
        //用getString获取值
        return mySharePreferences.getString(key, "");
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param filename
     * @param key
     * @return
     */
    public static boolean contains(String filename, String key) {
        SharedPreferences sp =
                context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 移除某个值
     *
     * @param filename
     * @param key
     */
    public static void remove(String filename, String key) {
        SharedPreferences sp = context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 日期格式转换
     *
     * @param millis
     * @return
     */
    public static String getTime(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
