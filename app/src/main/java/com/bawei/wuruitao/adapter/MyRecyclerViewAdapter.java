package com.bawei.wuruitao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wuruitao.R;
import com.bawei.wuruitao.model.entity.NewsEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ProjectName: WuRuitao20191231
 * PackageName: com.bawei.wuruitao
 * ClassName:   MyRecyclerViewAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/31_9:19
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.VH> {


    private final Context context;
    private final List<NewsEntity.RankingBean> ranking;


    public MyRecyclerViewAdapter(Context context, List<NewsEntity.RankingBean> ranking) {

        this.context = context;
        this.ranking = ranking;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new VH(View.inflate(context, R.layout.recycleview_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        NewsEntity.RankingBean list = ranking.get(position);
        //加载图片
        Glide.with(context)
                .load(list.getAvatar())
                .circleCrop()//原型图
                .placeholder(R.mipmap.ic_launcher)//占位图
                .error(R.mipmap.ic_launcher)//错误图
                .into(holder.uIv);
        holder.uId.setText(list.getRank());//。。。
        holder.uName.setText(list.getName());//设置名字

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addRVItemClickListener != null) {
                    addRVItemClickListener.listener(list,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    class VH extends RecyclerView.ViewHolder {
        //找控件
        @BindView(R.id.u_id)
        TextView uId;
        @BindView(R.id.u_iv)
        ImageView uIv;
        @BindView(R.id.u_name)
        TextView uName;
        @BindView(R.id.u_num)
        TextView uNum;
        @BindView(R.id.u_oush)
        TextView uOush;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //自定义接口回调

    private AddRVItemClickListener addRVItemClickListener;

    public void setAddRVItemClickListener(AddRVItemClickListener addRVItemClickListener) {
        this.addRVItemClickListener = addRVItemClickListener;
    }

    public interface AddRVItemClickListener{
        void listener(NewsEntity.RankingBean list,int position);
    }


}
