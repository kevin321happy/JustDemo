package com.ffmpeg.demo;


import android.app.Application;
import android.os.Environment;
import com.yixia.camera.VCamera;
import java.io.File;

public class RecordedApp extends Application {

  public static String VEDIO_PATH = Environment.getDataDirectory().getPath() + "/vedio";

  @Override
  public void onCreate() {
    super.onCreate();
    //初始化Vamera
    VEDIO_PATH += String.valueOf(System.currentTimeMillis());
    File file=new File(VEDIO_PATH);
    if (!file.exists()){
      file.mkdirs();
    }
    //设置视频缓存路径
    VCamera.setVideoCachePath(VEDIO_PATH);
    //开启日志输出
    VCamera.setDebugMode(true);
    //初始化SDK
    VCamera.initialize(this);
  }
}
