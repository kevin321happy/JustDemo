package com.kevin.wechatrecord20;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mabeijianxi.camera.MediaRecorderActivity;


public class SendSmallVideoActivity extends Activity {

  @Bind(R.id.tv_cancel)
  TextView mTvCancel;
  @Bind(R.id.tv_send)
  TextView mTvSend;
  @Bind(R.id.rl_title)
  RelativeLayout mRlTitle;
  @Bind(R.id.line)
  View mLine;
  @Bind(R.id.et_send_content)
  EditText mEtSendContent;
  @Bind(R.id.iv_video_screenshot)
  ImageView mIvVideoScreenshot;
  @Bind(R.id.rl_root)
  RelativeLayout mRlRoot;
  private String videoUri;
  private String videoScreenshot;//视频截图
  private ImageView iv_video_screenshot;//封面图片
  private AlertDialog mAlertDialog;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //record
    setContentView(R.layout.activity_recorder);
    ButterKnife.bind(this);
    initData();

  }

  //初始化
  private void initData() {
    Intent intent = getIntent();
    videoUri = intent.getStringExtra(MediaRecorderActivity.VIDEO_URI);
    videoScreenshot = intent.getStringExtra(MediaRecorderActivity.VIDEO_SCREENSHOT);
    Bitmap bitmap = BitmapFactory.decodeFile(videoScreenshot);
    mIvVideoScreenshot.setImageBitmap(bitmap);
    mEtSendContent.setHint("视频的地址为 ：" + videoUri);
  }

  @OnClick({R.id.tv_cancel, R.id.tv_send, R.id.iv_video_screenshot})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_cancel:
        hesitate();
        break;
      case R.id.tv_send:
        break;
      case R.id.iv_video_screenshot:
        startActivity(new Intent(this, VideoPlayerActivity.class).putExtra(
            "path", videoUri));
        break;
    }
  }

  @Override
  public void onBackPressed() {
    hesitate();
  }
  private void hesitate() {
    if (mAlertDialog == null) {
      mAlertDialog = new AlertDialog.Builder(this)
          .setTitle(R.string.hint)
          .setMessage(R.string.record_camera_exit_dialog_message)
          .setNegativeButton(
              R.string.record_camera_cancel_dialog_yes,
              new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,
                    int which) {
                  finish();
//                                    FileUtils.deleteDir(getIntent().getStringExtra(MediaRecorderActivity.OUTPUT_DIRECTORY));
                }
              })
          .setPositiveButton(R.string.record_camera_cancel_dialog_no,
              null).setCancelable(false).show();
    } else {
      mAlertDialog.show();
    }
  }
}
