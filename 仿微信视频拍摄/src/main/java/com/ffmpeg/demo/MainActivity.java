package com.ffmpeg.demo;

import static com.ffmpeg.demo.R.id.rl_bottom2;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ffmpeg.demo.widget.FocusSurfaceView;
import com.ffmpeg.demo.widget.MyVideoView;
import com.ffmpeg.demo.widget.RecordedButton;
import com.ffmpeg.demo.widget.RecordedButton.OnGestureListener;
import com.yixia.camera.MediaRecorderNative;
import com.yixia.camera.VCamera;
import com.yixia.camera.model.MediaObject;
import com.yixia.camera.model.MediaObject.MediaPart;
import com.yixia.camera.util.Log;
import com.yixia.videoeditor.adapter.UtilityAdapter;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private static final int REQUEST_KEY = 100;
  private static final int HANDLER_RECORD = 200;//拍摄视频
  private static final int HANDLER_EDIT_VIDEO = 201;//合成视频


  private AlertDialog progressDialog;
  private MediaObject mMediaObject;
  //最大录制时间
  private int maxDuration = 8000;
  //本次段落是否录制完成
  private boolean isRecordedOver;
  private MediaRecorderNative mMediaRecorder;

  @Bind(R.id.sv_ffmpeg)
  FocusSurfaceView mSvFfmpeg;
  @Bind(R.id.vv_play)
  MyVideoView mVvPlay;
  @Bind(R.id.iv_back)
  ImageView mIvBack;
  @Bind(R.id.iv_finish)
  ImageView mIvFinish;
  @Bind(R.id.rl_bottom)
  RelativeLayout mRlBottom;
  @Bind(R.id.iv_close)
  ImageView mIvClose;
  @Bind(R.id.iv_next)
  ImageView mIvNext;
  @Bind(rl_bottom2)
  RelativeLayout mRlBottom2;
  @Bind(R.id.rb_start)
  RecordedButton mRbStart;
  @Bind(R.id.tv_hint)
  TextView mTvHint;
  private TextView mTextView;
  private Handler myHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case HANDLER_RECORD://拍摄视频的handler
          if (!isRecordedOver) {
            if (mRlBottom.getVisibility() == View.VISIBLE) {
              changeButton(false);
            }
            mRbStart.setProgress(mMediaObject.getDuration());
            myHandler.sendEmptyMessageDelayed(HANDLER_RECORD, 30);
          }
          break;
        case HANDLER_EDIT_VIDEO://合成视频的handler
          int progress = UtilityAdapter
              .FilterParserAction("", UtilityAdapter.PARSERACTION_PROGRESS);
          if (mTextView != null) {
            mTextView.setText("视频编译中 " + progress + "%");
          }
          if (progress == 100) {
            syntVideo();
          } else if (progress == -1) {
            closeProgressDialog();
            Toast.makeText(getApplicationContext(), "视频合成失败", Toast.LENGTH_SHORT).show();
          } else {
            sendEmptyMessageDelayed(HANDLER_EDIT_VIDEO, 30);
          }
          break;
      }
    }
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //设置全屏模式
    getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN,
        LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initMediaRecorder();

    mSvFfmpeg.setTouchFocus(mMediaRecorder);

    mRbStart.setMax(maxDuration);

    mRbStart.setOnGestureListener(new RecordedButton.OnGestureListener() {
      @Override
      public void onLongClick() {
        isRecordedOver = false;
        mMediaRecorder.startRecord();
        mRbStart.setSplit();
        myHandler.sendEmptyMessageDelayed(HANDLER_RECORD, 100);
      }

      @Override
      public void onClick() {
      }

      @Override
      public void onLift() {
        isRecordedOver = true;
        mMediaRecorder.stopRecord();
        changeButton(mMediaObject.getMedaParts().size() > 0);
      }

      @Override
      public void onOver() {
        isRecordedOver = true;
        mRbStart.closeButton();
        mMediaRecorder.stopRecord();
        videoFinish();
      }
    });

    mIvBack.setOnClickListener(this);
    mIvFinish.setOnClickListener(this);
    mIvNext.setOnClickListener(this);
    mIvClose.setOnClickListener(this);

  }

  /**
   * 初始化视频录制对象
   */
  private void initMediaRecorder() {
    mMediaRecorder = new MediaRecorderNative();
    String key = String.valueOf(System.currentTimeMillis());
    //设置缓存文件夹
    mMediaObject = mMediaRecorder.setOutputDirectory(key, VCamera.getVideoCachePath());
    //设置视频预览源
    mMediaRecorder.setSurfaceHolder(mSvFfmpeg.getHolder());
    //准备
    mMediaRecorder.prepare();
    //滤波器相关
    UtilityAdapter.freeFilterParser();
    UtilityAdapter.initFilterParser();

  }


  //拍摄按钮的手势监听
  private OnGestureListener mOnGestureListener = new OnGestureListener() {
    @Override
    public void onLongClick() {
      isRecordedOver = false;
      mMediaRecorder.startRecord();
      mRbStart.setSplit();
      //发送消息开始录制
      Log.i("test", "正在长按着........");
      myHandler.sendEmptyMessageDelayed(HANDLER_RECORD, 100);

    }

    @Override
    public void onClick() {

    }

    @Override
    public void onLift() {
      //录制完成
      isRecordedOver = true;
      mMediaRecorder.stopRecord();
      changeButton(mMediaObject.getMedaParts().size() > 0);
    }

    @Override
    public void onOver() {
      isRecordedOver = true;
      mRbStart.closeButton();
      mMediaRecorder.stopRecord();
      videoFinish();
    }
  };

  //完成录制
  private void videoFinish() {
    changeButton(false);
    mRbStart.setVisibility(View.GONE);
    mTextView = showProgressDialog();
    myHandler.sendEmptyMessage(HANDLER_EDIT_VIDEO);
  }

  /**
   * 初始化视频拍摄状态
   */
  private void initMediaRecorderState() {
    mVvPlay.setVisibility(View.GONE);
    mVvPlay.pause();

    mRbStart.setVisibility(View.VISIBLE);
    mRlBottom2.setVisibility(View.GONE);
    changeButton(false);
    mTvHint.setVisibility(View.VISIBLE);

    LinkedList<MediaObject.MediaPart> list = new LinkedList<>();
    list.addAll(mMediaObject.getMedaParts());

    for (MediaObject.MediaPart part : list) {
      mMediaObject.removePart(part, true);
    }

    mRbStart.setProgress(mMediaObject.getDuration());
    mRbStart.cleanSplit();
  }

  //设置改变按钮
  private void changeButton(boolean flag) {

    if (flag) {
      mTvHint.setVisibility(View.VISIBLE);
      mRlBottom.setVisibility(View.VISIBLE);
    } else {
      mTvHint.setVisibility(View.GONE);
      mRlBottom.setVisibility(View.GONE);
    }
  }


  //显示合成视频时的进度提示
  public TextView showProgressDialog() {
    Builder builder = new Builder(this);
    builder.setCancelable(false);
    View view = View.inflate(this, R.layout.dialog_loading, null);
    builder.setView(view);
    ProgressBar pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
    TextView tv_hint = (TextView) view.findViewById(R.id.tv_hint);
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      pb_loading.setIndeterminateTintList(
          ContextCompat.getColorStateList(this, R.color.dialog_pro_color));
    }
    tv_hint.setText("视频编译中");
    progressDialog = builder.create();
    progressDialog.show();

    return tv_hint;
  }

  @Override
  protected void onResume() {
    super.onResume();
    mMediaRecorder.startRecord();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mMediaRecorder.stopRecord();
  }

  @Override
  public void onBackPressed() {
    if (mRbStart.getSplitCount() == 0) {
      super.onBackPressed();
    } else {
      initMediaRecorderState();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      if (requestCode == REQUEST_KEY) {
        initMediaRecorderState();
      }
    }
  }

  /**
   * 合成视频
   */
  private void syntVideo() {
    //ffmpeg -i "concat:ts0.ts|ts1.ts|ts2.ts|ts3.ts" -c copy -bsf:a aac_adtstoasc out2.mp4
    StringBuilder sb = new StringBuilder("ffmpeg");
    sb.append(" -i");
    String concat = "concat:";
    for (MediaPart part : mMediaObject.getMedaParts()) {
      concat += part.mediaPath;
      concat += "|";
    }
    concat = concat.substring(0, concat.length() - 1);
    sb.append(" " + concat);
    sb.append(" -c");
    sb.append(" copy");
    sb.append(" -bsf:a");
    sb.append(" aac_adtstoasc");
    sb.append(" -y");
    String output = RecordedApp.VEDIO_PATH + "/finish.mp4";
    sb.append(" " + output);

    int i = UtilityAdapter.FFmpegRun("", sb.toString());
    closeProgressDialog();
    if (i == 0) {
      mRlBottom2.setVisibility(View.VISIBLE);
      mVvPlay.setVisibility(View.VISIBLE);

      mVvPlay.setVideoPath(output);
      mVvPlay.setOnPreparedListener(new OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
          mVvPlay.setLooping(true);
          mVvPlay.start();
        }
      });
      if (mVvPlay.isPrepared()) {
        mVvPlay.setLooping(true);
        mVvPlay.start();
      }
    } else {
      Toast.makeText(getApplicationContext(), "视频合成失败", Toast.LENGTH_SHORT).show();
    }
  }

  //关闭进度提示
  public void closeProgressDialog() {
    try {
      if (progressDialog != null) {
        progressDialog.dismiss();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.iv_back:
        if(mRbStart.isDeleteMode()){//判断是否要删除视频段落
          MediaObject.MediaPart lastPart = mMediaObject.getPart(mMediaObject.getMedaParts().size() - 1);
          mMediaObject.removePart(lastPart, true);
          mRbStart.setProgress(mMediaObject.getDuration());
          mRbStart.deleteSplit();
          changeButton(mMediaObject.getMedaParts().size() > 0);
          mIvBack.setImageResource(R.mipmap.video_delete);
        }else if(mMediaObject.getMedaParts().size() > 0){
          mRbStart.setDeleteMode(true);
          mIvBack.setImageResource(R.mipmap.video_delete_click);
        }
        break;
      case R.id.iv_finish:
        videoFinish();
        break;
      case R.id.iv_next:
        mRbStart.setDeleteMode(false);
//        Intent intent = new Intent(MainActivity.this, EditVideoActivity.class);
//        intent.putExtra("path", MyApplication.VIDEO_PATH+"/finish.mp4");
//        startActivityForResult(intent, REQUEST_KEY);
        break;
      case R.id.iv_close:
        initMediaRecorderState();
        break;
    }
  }
}
