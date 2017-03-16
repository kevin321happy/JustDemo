package com.example.greendaodemo;

import android.app.Application;

/**
 * Created by kevin on 2017/3/16. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 * ╭╩═╮╔════╗╔════╗╔════╗╔════╗╔════╗ ╭╯ GO ╠╣   永  ╠╣  无   ╠╣   B   ╠╣   U   ╠╣  G  ╠╣
 * ╰⊙═⊙╯╚◎══◎╝╚◎══◎╝╚◎══◎╝╚◎══◎╝╚◎══◎╝
 */

public class MyApplication extends Application {

  private Object mApplication;
  public static MyApplication myApplication;

  @Override
  public void onCreate() {
    super.onCreate();
    myApplication=this;
    //getApplication();
  }

  public  static  Application getApplication() {
    return myApplication;
  }
}
