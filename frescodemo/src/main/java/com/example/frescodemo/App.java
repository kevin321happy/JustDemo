package com.example.frescodemo;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by kevin on 2017/3/15. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 */

public class App extends Application {
  private static App app;


  @Override
  public void onCreate() {
    super.onCreate();
    app=this;
    //普通初始化
    Fresco.initialize(this);
  }
  public static App get() {
    return app;
  }
}
