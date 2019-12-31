package com.bawei.wuruitao;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.wuruitao.base.BaseActivity;
import com.bawei.wuruitao.base.mvp.BasePresenter;
import com.bawei.wuruitao.model.entity.UserEntity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class Main2Activity extends BaseActivity {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.weixin)
    Button weixin;
    @BindView(R.id.qq)
    Button qq;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);//注册
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }

    @Subscribe(sticky = true)
    public void getName(UserEntity userEntity) {
        Bitmap image = CodeUtils.createImage(userEntity.getName(), 400, 400, null);//生成二维码

        iv.setImageBitmap(image);//给控件赋值
    }

    @OnLongClick(R.id.iv)
    public void cl() {
        CodeUtils.analyzeByImageView(iv, new CodeUtils.AnalyzeCallback() {//长按识别二维码
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();//成功信息
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(Main2Activity.this, "二维码解析失败", Toast.LENGTH_SHORT).show();//失败信息
            }
        });
    }

    @OnClick({R.id.weixin, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin:
                EventBus.getDefault().post("微信");
                break;
            case R.id.qq:
                EventBus.getDefault().post("QQ");
                break;
        }
    }


    @Subscribe
    public void getWeiXin(String weixin) {
        Toast.makeText(this, weixin, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void getQQ(String qq) {
        Toast.makeText(this, qq, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//i.在Activity销毁的时候注销注册
    }


}


