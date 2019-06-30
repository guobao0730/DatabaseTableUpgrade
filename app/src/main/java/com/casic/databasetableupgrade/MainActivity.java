package com.casic.databasetableupgrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.casic.databasetableupgrade.adapter.MainRVAdapter;
import com.casic.databasetableupgrade.bean.RecommendBean;
import com.casic.databasetableupgrade.db.RecommendDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        mRv = findViewById(R.id.mainActivity_rv);

    }

    private void initData() {

        final RecommendDao recommendDao = new RecommendDao(this);


        ArrayList<RecommendBean> recommendBeans = new ArrayList<>();

        List<RecommendBean> recommendBeans1 = recommendDao.queryAll();

        //如果数据库有数据，那么从数据库获取
        if (recommendBeans1!=null&&recommendBeans1.size()>0){
            recommendBeans.clear();
            recommendBeans.addAll(recommendBeans1);
            Log.i(TAG,"从数据库获取数据");
        }else {
            //否则从网络获取数据
            RecommendBean recommendBean = null;
            for (int i = 0; i < 30; i++) {
                recommendBean = new RecommendBean();
                recommendBean.setTitle("习近平抵达大阪出席G20峰会" + i);
                recommendBean.setSource("新华网客户端");
                recommendBean.setComments(i * 21);
                recommendBean.setTime((i + 1) + "分钟前");
                if (i%2==0){
                    recommendBean.setIstop("0");
                }else {
                    recommendBean.setIstop("1");
                }

                recommendBeans.add(recommendBean);

                recommendDao.insert(recommendBean);
            }
            Log.i(TAG,"从网络获取数据");
        }



        MainRVAdapter mainRVAdapter = new MainRVAdapter(this, recommendBeans);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);

        mRv.setAdapter(mainRVAdapter);

    }
}
