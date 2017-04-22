package com.kevin.wechatrecord20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.kevin.wechatrecord20.widget.ActionSheetDialog;
import com.kevin.wechatrecord20.widget.ActionSheetDialog.OnSheetItemClickListener;
import com.kevin.wechatrecord20.widget.ActionSheetDialog.SheetItemColor;
import java.util.HashMap;
import mabeijianxi.camera.MediaRecorderActivity;
import mabeijianxi.camera.model.AutoVBRMode;
import mabeijianxi.camera.model.BaseMediaBitrateConfig;
import mabeijianxi.camera.model.MediaRecorderConfig;

public class MainActivity extends AppCompatActivity {

  private ImageView mIv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mIv = (ImageView) findViewById(R.id.iv_fm);
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
    MediaRecorderActivity
        .goSmallVideoRecorder(this, SendSmallVideoActivity.class.getName(), config);
  }

  /*
  * //创建MediaMetadataRetriever对象
    	MediaMetadataRetriever mmr=new MediaMetadataRetriever();
    	//设置资源位置
    	String path="/storage/sdcard1"+"/Movies"+"/XiaomiPhone.mp4";
//绑定资源
    	mmr.setDataSource(path);
    	//获取第一帧图像的bitmap对象
    	Bitmap bitmap=mmr.getFrameAtTime();
    	//加载到ImageView控件上
    	img.setImageBitmap(bitmap);
  * */
  //获取视频的第一帧
  public void getFirstIma(View view) {
    //   http://v1.52qmct.com/uploadima/2017/22219201704171931.mp4
//    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
    String path = "http://v1.52qmct.com/uploadima/2017/22219201704171931.mp4";
//    String path = "http://baobab.wdjcdn.com/145076769089714.mp4";
//    Uri uri = Uri.parse(path);
//    mmr.setDataSource(path);
    Bitmap videoThumbnail = getVideoThumbnail(path);
    //获取视频第一帧
//    Bitmap frameAtTime = mmr.getFrameAtTime();
    mIv.setImageBitmap(videoThumbnail);

  }

  /**
   * 给出url，获取视频的第一帧
   */
  public static Bitmap getVideoThumbnail(String url) {
    Bitmap bitmap = null;
//MediaMetadataRetriever 是android中定义好的一个类，提供了统一
//的接口，用于从输入的媒体文件中取得帧和元数据；
    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
    try {
      //根据文件路径获取缩略图
      retriever.setDataSource(url, new HashMap<String, String>());
      //获得第一帧图片
      bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_NEXT_SYNC);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } finally {
      retriever.release();
    }
    return bitmap;
  }

  //前往获取视频列表
  public void getvideoList(View view) {
    startActivity(new Intent(this, VideoPickActivity.class));
  }

  //底部弹出对话框
  public void showDialog(View view) {
    new ActionSheetDialog(this)
        .builder()
        .setCancelable(false)
        .setCanceledOnTouchOutside(false)
        .addSheetItem("拍摄视频", SheetItemColor.Blue,
            new OnSheetItemClickListener() {
              @Override
              public void onClick(int which) {
                Toast.makeText(MainActivity.this, "点击了去拍摄视频", Toast.LENGTH_SHORT).show();
              }
            })
        .addSheetItem("去相册选择", SheetItemColor.Blue,
            new OnSheetItemClickListener() {
              @Override
              public void onClick(int which) {
                Toast.makeText(MainActivity.this, "点击了去相册选择", Toast.LENGTH_SHORT).show();

              }
            }).show();
  }
}
