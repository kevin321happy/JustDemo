package com.example.dowload;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import de.greenrobot.event.EventBus;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  String url = "http://huashunoapp.oss-cn-hangzhou.aliyuncs.com/uploadima/2017";
  private TextView mTv_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);
    setContentView(R.layout.activity_main);
    //先判断服务是否开启,没开启就开启
    //开启服务
    boolean serviceWork = isServiceWork(this, "com.example.dowload.DownLoadService");
    if (serviceWork==false){
      Intent it = new Intent(this, DownLoadService.class);
      startService(it);
    }
    mTv_ = (TextView) findViewById(R.id.tv_state);
  }
  //下载
  public void downloadOne(View view) {
    DownloadInfo downloadInfo = new DownloadInfo();
    downloadInfo.setUrl(url);
    downloadInfo.setId("1");
    EventBus.getDefault().post(downloadInfo);
  }

  public void onEventMainThread(MassageEvent event) {
    Log.i("test", event.getMessage().toString());
    mTv_.setText(event.getMessage() + "");

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  public void finish(View view) {
    finish();
  }

  /**
   * 判断服务是否开启
   */
  public boolean isServiceWork(Context context, String ServiceName) {
    boolean isWork = false;
    ActivityManager am = (ActivityManager) ((Activity) context)
        .getSystemService(Context.ACTIVITY_SERVICE);
    List<RunningServiceInfo> runningServices = am.getRunningServices(40);
    if (runningServices.size() < 0) {
      return false;
    }
    for (int i = 0; i < runningServices.size(); i++) {
      String mName = runningServices.get(i).service.getClassName().toString();
      if (mName.equals(ServiceName)) {
        isWork = true;
        break;
      }
    }
    return isWork;
  }
}
