package com.kevin.wechatrecord20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import mabeijianxi.camera.MediaRecorderActivity;
import mabeijianxi.camera.model.AutoVBRMode;
import mabeijianxi.camera.model.BaseMediaBitrateConfig;
import mabeijianxi.camera.model.MediaRecorderConfig;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  //点击前往录制视频
  public void Go(View view) {
    //设置录制视频的配置参数
    MediaRecorderConfig config = new MediaRecorderConfig.Buidler()
        .doH264Compress(new AutoVBRMode()
            .setVelocity(BaseMediaBitrateConfig.Velocity.ULTRAFAST)
        )
        .setMediaBitrateConfig(new AutoVBRMode()
            .setVelocity(BaseMediaBitrateConfig.Velocity.ULTRAFAST)
        )
        .smallVideoWidth(480)
        .smallVideoHeight(360)
        .recordTimeMax(6 * 1000)
        .maxFrameRate(20)
        .captureThumbnailsTime(1)
        .recordTimeMin((int) (1.5 * 1000))
        .build();
    //跳转到视频的录制界面
    MediaRecorderActivity.goSmallVideoRecorder(this, SendSmallVideoActivity.class.getName(), config);

  }
}
