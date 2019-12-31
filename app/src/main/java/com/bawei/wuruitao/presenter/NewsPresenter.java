package com.bawei.wuruitao.presenter;

import com.bawei.wuruitao.model.NewsModel;
import com.bawei.wuruitao.model.entity.NewsEntity;
import com.bawei.wuruitao.base.mvp.BasePresenter;
import com.bawei.wuruitao.contract.NewsInfoContract;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao
 * ClassName:   NewsPresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_9:09
 */
public class NewsPresenter extends BasePresenter<NewsModel, NewsInfoContract.IView> implements NewsInfoContract.IPresenter {
    @Override
    public void setNewsUrl(String url) {
        m.getNewsData(url, new NewsInfoContract.IModel.UserDataCallBack() {
            @Override
            public void success(NewsEntity newsEntity) {
                getView().success(newsEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }

    @Override
    protected NewsModel initModel() {
        return new NewsModel();
    }
}
