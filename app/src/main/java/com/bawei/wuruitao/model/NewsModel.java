package com.bawei.wuruitao.model;

import com.bawei.wuruitao.contract.NewsInfoContract;
import com.bawei.wuruitao.model.entity.NewsEntity;
import com.bawei.wuruitao.utils.OkHttpUtils;
import com.google.gson.Gson;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao.model
 * ClassName:   NewsModel
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_9:08
 */
public class NewsModel implements NewsInfoContract.IModel {
    @Override
    public void getNewsData(String url, UserDataCallBack dataCallBack) {
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.OkHttpDataCallBack() {
            @Override
            public void success(String json) {
                if (dataCallBack != null) {
                    NewsEntity newsEntity = new Gson().fromJson(json, NewsEntity.class);
                    dataCallBack.success(newsEntity);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (dataCallBack != null) {
                    dataCallBack.failure(throwable);
                }
            }
        });
    }
}
