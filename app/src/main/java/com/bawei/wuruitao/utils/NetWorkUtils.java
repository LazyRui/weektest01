package com.bawei.wuruitao.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao.utils
 * ClassName:   NetWorkUtils
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_9:04
 */
public class NetWorkUtils {
    private static NetWorkUtils netWorkUtils;

    private NetWorkUtils() {
    }

    public static NetWorkUtils getInstance() {
        if (netWorkUtils == null){
            synchronized (NetWorkUtils.class){
                if (netWorkUtils == null){
                    netWorkUtils = new NetWorkUtils();
                }
            }
        }
        return netWorkUtils;
    }


    //判断是否有网
    public boolean hashNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        }
        return false;
    }



}
