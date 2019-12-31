package com.bawei.wuruitao.base.mvp;

import java.lang.ref.WeakReference;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao.base.mvp
 * ClassName:   BasePresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_8:47
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {
    protected M m;
    private WeakReference<V> weakReference;

    public BasePresenter() {
        m = initModel();
    }
    //绑定V层
    public void attach(V v) {
        weakReference = new WeakReference<>(v);
    }

    protected abstract M initModel();

    public void detach() {//解决内存泄漏
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }
    //获取V层对象
    public V getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
