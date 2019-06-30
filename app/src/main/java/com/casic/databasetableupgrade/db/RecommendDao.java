package com.casic.databasetableupgrade.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.casic.databasetableupgrade.bean.RecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭宝
 * @project： DatabaseTableUpgrade
 * @package： com.casic.databasetableupgrade.db
 * @date： 2019/6/27 0027 16:41
 * @brief: 推荐 表操作
 */
public class RecommendDao {

    private Context mContext;

    private static final String TABLE_NAME = "recommend";
    private static final String TITLE = "title";
    private static final String SOURCE = "source";
    private static final String COMMENTS = "comments";
    private static final String TIME = "time";
    private static final String ISTOP = "istop";
    private final MySQLiteOpenHelper mMySQLiteOpenHelper;


    public RecommendDao(Context context) {
        mContext = context;
        mMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    /**
     * 表初始化
     * @param sqLiteDatabase
     */
    public static void init(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table "+TABLE_NAME +"(_id integer primary key autoincrement,"
                +TITLE+ " varchar(200),"
                +SOURCE+" varchar(100),"
                +COMMENTS+" integer(200),"
                +TIME+" varchar(100)"
                +")");
    }


    /**
     * 添加数据
     * @param recommendBean
     * @return
     */
    public boolean insert(RecommendBean recommendBean){
        long isInsertSucced = 0;
        SQLiteDatabase readableDatabase = mMySQLiteOpenHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,recommendBean.getTitle());
        contentValues.put(SOURCE,recommendBean.getSource());
        contentValues.put(COMMENTS,recommendBean.getComments());
        contentValues.put(TIME,recommendBean.getTime());
        contentValues.put(ISTOP,recommendBean.getIstop());
        isInsertSucced= readableDatabase.insert(TABLE_NAME, null, contentValues);

        readableDatabase.close();
        return isInsertSucced==-1?false:true;
    }


    /**
     * 查询数据库中所有的数据
     * @param
     */
    public List<RecommendBean> queryAll(){

        ArrayList<RecommendBean> recommendBeanList = new ArrayList<>();

        RecommendBean recommendBean = null;

        //创建数据库
        SQLiteDatabase readableDatabase = mMySQLiteOpenHelper.getReadableDatabase();

        //查询该表中的所有数据
        Cursor cursor = readableDatabase.rawQuery(" select * from "+TABLE_NAME+"", null);

        //cursor(结果集)
        //如果有结果数据，并且表中行数大于0
        if (cursor!=null&&cursor.getCount()>0){

            //如果游标还可以往下移动，那么就继续循环取出数据
            while (cursor.moveToNext()){

                //根据列的索引获取对应的数据
                int comments = cursor.getInt(cursor.getColumnIndex(COMMENTS));
                String title = cursor.getString(cursor.getColumnIndex(TITLE));
                String source = cursor.getString(cursor.getColumnIndex(SOURCE));
                String time = cursor.getString(cursor.getColumnIndex(TIME));
                String istop = cursor.getString(cursor.getColumnIndex(ISTOP));

                recommendBean = new RecommendBean();
                recommendBean.setComments(comments);
                recommendBean.setTitle(title);
                recommendBean.setSource(source);
                recommendBean.setTime(time);
                recommendBean.setIstop(istop);

                recommendBeanList.add(recommendBean);
            }
            //关闭结果集
            cursor.close();

        }else {
            Log.i("queryAll:","未查询到相关的数据");
        }
        //最后关闭数据库
        readableDatabase.close();

        return recommendBeanList;
    }

}
