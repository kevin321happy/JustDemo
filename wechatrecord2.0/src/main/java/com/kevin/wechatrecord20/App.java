package com.kevin.wechatrecord20;


import android.app.Application;
import android.os.Environment;
import java.io.File;
import mabeijianxi.camera.VCamera;
import mabeijianxi.camera.util.DeviceUtils;

public class App extends Application {

  @Override
  public void onCreate() {
    //设置拍摄视屏的缓存路径
    File recordFeil = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    //检测是否是中心的手机
    if (DeviceUtils.isZte()) {
      if (recordFeil.exists()) {
        VCamera.setVideoCachePath(recordFeil + "/weixin/");
      } else {
        VCamera.setVideoCachePath(recordFeil.getPath().replace("/sdcard/",
            "/sdcard-ext/")
            + "/weixin/");
      }
    } else {
      VCamera.setVideoCachePath(recordFeil + "/weixin/");
    }
    VCamera.setDebugMode(true);
    VCamera.initialize(this);
  }
}
