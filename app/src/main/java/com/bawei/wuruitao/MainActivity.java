package com.bawei.wuruitao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wuruitao.adapter.MyRecyclerViewAdapter;
import com.bawei.wuruitao.api.Api;
import com.bawei.wuruitao.base.BaseActivity;
import com.bawei.wuruitao.contract.NewsInfoContract;
import com.bawei.wuruitao.model.entity.NewsEntity;
import com.bawei.wuruitao.model.entity.UserEntity;
import com.bawei.wuruitao.presenter.NewsPresenter;
import com.bawei.wuruitao.utils.NetWorkUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<NewsPresenter> implements NewsInfoContract.IView {

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.m)
    LinearLayout m;

    @Override
    protected void initData() {
        //判断网络状态
        boolean b = NetWorkUtils.getInstance().hashNet(this);
        if (b) {
            p.setNewsUrl(Api.NEWS_URL);
        } else {
            m.setVisibility(View.GONE);
            Toast.makeText(this, "没有网络……", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));//
        //在Activity创建的时候注册EventBus，在Activity销毁的时候注销注册


    }

    @Override
    protected NewsPresenter initPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(NewsEntity newsEntity) {
        if (newsEntity != null) {
            Toast.makeText(this, newsEntity.getRanking().get(0).getName(), Toast.LENGTH_SHORT).show();
            MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(this, newsEntity.getRanking());//创建适配器
            rv.setAdapter(recyclerViewAdapter);//设置适配器
            //设置点击监听
            recyclerViewAdapter.setAddRVItemClickListener(new MyRecyclerViewAdapter.AddRVItemClickListener() {
                @Override
                public void listener(NewsEntity.RankingBean list, int position) {
                    Toast.makeText(MainActivity.this, list.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void failure(Throwable throwable) {

    }

    //ii.点击图1中的红色文字“点击这里分享给朋友”跳转到图2，使用自己的名字生成二维码并展示
    @OnClick(R.id.tv_search)
    public void onViewClicked() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("武瑞涛");
        EventBus.getDefault().postSticky(userEntity);
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }

}
