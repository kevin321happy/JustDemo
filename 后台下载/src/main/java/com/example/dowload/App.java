package com.example.dowload;


import android.app.Application;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.rest.RequestQueue;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    initNoHttp();
  }

  private void initNoHttp() {
    RequestQueue requestQueue = NoHttp.newRequestQueue();
    NoHttp.initialize(this, new NoHttp.Config()
        .setNetworkExecutor(new OkHttpNetworkExecutor())//底层为okhttp
        .setCacheStore(new DBCacheStore(this).setEnable(true))//数据库缓存
        .setConnectTimeout(30 * 1000)
        .setReadTimeout(10 * 1000)
    );
  }
}
