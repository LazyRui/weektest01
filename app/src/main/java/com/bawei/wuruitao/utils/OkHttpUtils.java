package com.bawei.wuruitao.utils;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao
 * ClassName:   OkHttpUtils
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_8:57
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler;
    private OkHttpUtils() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()//添加日志拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    //封装OKHTTP的get方法，
    public void doGet(String url,OkHttpDataCallBack httpDataCallBack){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                IOException error = e;

                if (httpDataCallBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpDataCallBack.failure(e);

                        }
                    });
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (httpDataCallBack != null) {
                            httpDataCallBack.success(json);
                        }
                    }
                });
            }
        });
    }

    public interface OkHttpDataCallBack{
        void success(String json);
        void failure(Throwable throwable);
    }

}
