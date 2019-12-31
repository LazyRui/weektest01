package com.bawei.wuruitao.contract;

import com.bawei.wuruitao.model.entity.NewsEntity;
import com.bawei.wuruitao.base.mvp.IBaseModel;
import com.bawei.wuruitao.base.mvp.IBaseView;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao
 * ClassName:   UserInfoContract
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_8:50
 */
public interface NewsInfoContract {
    interface IModel extends IBaseModel {
        void getNewsData(String url,UserDataCallBack dataCallBack);
        interface UserDataCallBack{
            void success(NewsEntity newsEntity);
            void failure(Throwable throwable);
        }

    }

    interface IView extends IBaseView{
        void success(NewsEntity newsEntity);
        void failure(Throwable throwable);
    }

    interface IPresenter{
        void setNewsUrl(String url);
    }
}
