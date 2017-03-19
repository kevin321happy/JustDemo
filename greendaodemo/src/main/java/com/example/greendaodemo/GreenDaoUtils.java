package com.example.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import com.wyk.greendaodemo.greendao.gen.DaoMaster;
import com.wyk.greendaodemo.greendao.gen.DaoSession;

/**
 * Created by kevin on 2017/3/16. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司) greenDao工具类
 */

public class GreenDaoUtils {

  public DaoMaster.DevOpenHelper mHelper;
  public SQLiteDatabase db;
  public DaoMaster mDaoMaster;
  public DaoSession mDaoSession;

  private static GreenDaoUtils greenDaoUtils;


  public GreenDaoUtils() {
  }

  //单例模式
  public static GreenDaoUtils getInstance() {
    if (greenDaoUtils == null) {
      greenDaoUtils = new GreenDaoUtils();
    }
    return greenDaoUtils;
    //initGreenDao();
  }

  private void initGreenDao() {
    mHelper = new DaoMaster.DevOpenHelper(MyApplication.getApplication(), "imchat2-db", null);
    db = mHelper.getWritableDatabase();
    mDaoMaster = new DaoMaster(db);
    mDaoSession=mDaoMaster.newSession();
    getmDaoSession();
    getDb();
  }


  public DaoSession getmDaoSession() {
    if (mDaoSession==null){
      initGreenDao();
    }
    return mDaoSession;
  }

  public SQLiteDatabase getDb() {
    if (db==null){
      initGreenDao();
    }
    return db;
  }
}
