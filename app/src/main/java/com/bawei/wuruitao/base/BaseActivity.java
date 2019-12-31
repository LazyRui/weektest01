package com.bawei.wuruitao.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.wuruitao.base.mvp.BasePresenter;
import com.bawei.wuruitao.base.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao
 * ClassName:   BaseActivity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_8:54
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P p;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        bind = ButterKnife.bind(this);
        p = initPresenter();
        if (p != null) {
            p.attach(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int layoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p != null) {
            p.detach();
        }
        if (bind != null) {
            bind.unbind();
        }
    }
}
