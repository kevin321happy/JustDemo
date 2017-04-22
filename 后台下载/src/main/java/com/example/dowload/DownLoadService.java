package com.example.dowload;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import de.greenrobot.event.EventBus;
import java.io.File;


public class DownLoadService extends Service {
  private String mPath;
  private DownloadQueue mDownloadQueue = NoHttp.newDownloadQueue(3);
  private EventBus mADefault;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.i("test","注册了EvenBus");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.i("test","服务开始了1");
    EventBus.getDefault().register(this);
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onStart(Intent intent, int startId) {
    super.onStart(intent, startId);
    Log.i("test","服务开始了");
    mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/nothhp";
    File filedir = new File(mPath);
//    SPUtils.put(this, activityid, mPath);
    if (!filedir.exists()) {
      filedir.mkdirs();
    }

  }


  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.i("test","绑定服务");
    return null;

  }

  @Override
  public void onDestroy() {
    super.onDestroy();
//    EventBus.getDefault().unregister(this);
    EventBus.getDefault().unregister(this);
    Log.i("test","f反注册了EvenBus");
  }

  @Override
  public boolean onUnbind(Intent intent) {
    return super.onUnbind(intent);
  }
  //接受消息
  public void onEventMainThread(DownloadInfo event) {
    String url = event.getUrl();
    String id = event.getId();
    Log.i("test","收到lEvenBus的消息："+url);
    mADefault = EventBus.getDefault();
    DownloadRequest downloadRequest = NoHttp.createDownloadRequest(url, mPath, true);
    mDownloadQueue.add(Integer.parseInt(id), downloadRequest, new DownloadListener() {
      @Override
      public void onDownloadError(int what, Exception exception) {
        mADefault.post(new MassageEvent("错误"));
      }
      @Override
      public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders,
          long allCount) {
        mADefault.post(new MassageEvent("开始"));
      }

      @Override
      public void onProgress(int what, int progress, long fileCount, long speed) {
        mADefault.post(new MassageEvent(""+progress));
      }

      @Override
      public void onFinish(int what, String filePath) {
        mADefault.post(new MassageEvent("完成"));

      }
      @Override
      public void onCancel(int what) {
        mADefault.post(new MassageEvent("取消"));
      }
    });
  }

  @Override
  public int checkPermission(String permission, int pid, int uid) {
    return super.checkPermission(permission, pid, uid);
  }
}
