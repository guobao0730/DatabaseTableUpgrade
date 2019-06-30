package com.casic.databasetableupgrade.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.casic.databasetableupgrade.R;
import com.casic.databasetableupgrade.bean.RecommendBean;

import java.util.List;

/**
 * @author 郭宝
 * @project： DatabaseTableUpgrade
 * @package： com.casic.databasetableupgrade.adapter
 * @date： 2019/6/27 0027 17:20
 * @brief:
 */
public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.MainRVViewHolder> {

    private Context mContext;
    private List<RecommendBean> mRecommendBeans;
    private final LayoutInflater inflater;

    public MainRVAdapter(Context context, List<RecommendBean> recommendBeans) {
        mContext = context;
        mRecommendBeans = recommendBeans;
        inflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public MainRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_main, viewGroup, false);
        return new MainRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVViewHolder mainRVViewHolder, int i) {
        RecommendBean recommendBean = mRecommendBeans.get(i);
        mainRVViewHolder.tv_title.setText(recommendBean.getTitle());
        mainRVViewHolder.tv_source.setText(recommendBean.getSource());
        mainRVViewHolder.tv_comments.setText(recommendBean.getComments()+"评论");
        mainRVViewHolder.tv_time.setText(recommendBean.getTime());
        String istop = recommendBean.getIstop();
        // 1 表示置顶
        switch (istop) {
            case "0":
                mainRVViewHolder.tv_istop.setVisibility(View.INVISIBLE);
                break;
            case "1":
                mainRVViewHolder.tv_istop.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mRecommendBeans.size();
    }


    public static class MainRVViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_source;
        public TextView tv_comments;
        public TextView tv_time;
        public TextView tv_istop;

        public MainRVViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_source = itemView.findViewById(R.id.tv_source);
            tv_comments = itemView.findViewById(R.id.tv_comments);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_istop = itemView.findViewById(R.id.tv_istop);
        }
    }

}
