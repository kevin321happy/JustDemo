package com.kevin.wechatrecord20;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.shuyu.common.CommonRecyclerAdapter;
import com.shuyu.common.CommonRecyclerManager;
import com.shuyu.common.model.RecyclerBaseModel;
import java.util.ArrayList;
import java.util.List;

//视频选择界面
public class VideoPickActivity extends Activity implements OnClickListener {

  @Bind(R.id.tv_left)
  TextView mTvLeft;
  @Bind(R.id.tv_right)
  TextView mTvRight;
  @Bind(R.id.recycle)
  RecyclerView mRecycler;
  private List<RecyclerBaseModel> datalist = new ArrayList<>();
  private List<VideoInfo> mVideoInfoList = new ArrayList<>();
  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      if (msg.what == 1) {
        mRecycler.setLayoutManager(new GridLayoutManager(VideoPickActivity.this, 4));
        mRecycleAdapter = new VideoRecycleAdapter(mVideoInfoList);
        mRecycler.setAdapter(mRecycleAdapter);
      }
    }
  };
  private VideoRecycleAdapter mRecycleAdapter;


  //绑定数据
  private void bindData() {
    if (mVideoInfoList != null && mVideoInfoList.size() > 0) {
      for (VideoInfo videoInfo : mVideoInfoList) {
        //设置布局ID
        videoInfo.setResLayoutId(VideoListHodler.ID);
        Log.i("test", "所有的数据1：" + "长度 ：" + mVideoInfoList.size() + "单个数据 ：" + videoInfo.toString());
      }
      setDatas(mVideoInfoList);
    }
  }

  public void setDatas(List datas) {
    this.datalist = datas;
    if (mCommonRecyclerAdapter != null) {
      mCommonRecyclerAdapter.setListData(datas);
    }
  }

  private CommonRecyclerManager mCommonRecyclerManager;
  private CommonRecyclerAdapter mCommonRecyclerAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video_pick);
    ButterKnife.bind(this);
    new SearchVedio().start();
    initView();
  }

  private void initView() {
    mTvRight.setOnClickListener(this);
    mTvLeft.setOnClickListener(this);
  }


  //获取格式化之后的时间
  public String getFormatTime(long time) {
    String s = null;
    long hour = time / (60 * 60 * 1000);
    long minute = (time - hour * 60 * 60 * 1000) / (60 * 1000);
    long second = (time - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
    if (second >= 60) {
      second = second % 60;
      minute += second / 60;
    }
    if (minute >= 60) {
      minute = minute % 60;
      hour += minute / 60;
    }
    String sh = " ";
    String sm = " ";
    String ss = " ";
    if (hour < 10) {
      sh = "0 " + String.valueOf(hour);
    } else {
      sh = String.valueOf(hour);
    }
    if (minute < 10) {
      sm = "0 " + String.valueOf(minute);
    } else {
      sm = String.valueOf(minute);
    }
    if (second < 10) {
      ss = "0 " + String.valueOf(second);
    } else {
      ss = String.valueOf(second);
    }
    s = sh.substring(1) + ":" + sm + ":" + ss;
    System.out.println(sh + sm + ss);
    System.out.println(hour + "a " + minute + "a " + second + "a ");
    return s;
  }

  //点击监听
  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case  R.id.tv_left://取消
        break;
      case R.id.tv_right://确定
        List<VideoInfo> chooseVideos = mRecycleAdapter.getChooseVideos();
//        Log.d("test","视频信息 ："+chooseVideos.get(0).toString());
        if (chooseVideos.size()==0){
          Toast.makeText(this,"请选择视频",Toast.LENGTH_SHORT).show();
          return;
        }else {
          //跳转界面
          Toast.makeText(this,"视频的时间为 ："+chooseVideos.get(0).getTotletime(),Toast.LENGTH_SHORT).show();
        }
        break;
    }

  }


  //查找本地视频
  class SearchVedio extends Thread {

    @Override
    public void run() {
      // 如果有sd卡（外部存储卡）
      if (android.os.Environment.getExternalStorageState()
          .equals(android.os.Environment.MEDIA_MOUNTED)) {
        Uri originalUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = VideoPickActivity.this.getApplicationContext().getContentResolver();
        Cursor cursor = cr.query(originalUri, null, null, null, null);
        if (cursor == null) {
          return;
        }
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
          String title = cursor
              .getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
          String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
          long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
          long duration = cursor
              .getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
          //获取当前Video对应的Id，然后根据该ID获取其缩略图的uri
          int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
          String[] selectionArgs = new String[]{id + ""};
          String[] thumbColumns = new String[]{MediaStore.Video.Thumbnails.DATA,
              MediaStore.Video.Thumbnails.VIDEO_ID};
          String selection = MediaStore.Video.Thumbnails.VIDEO_ID + "=?";
          String uri_thumb = "";
          Cursor thumbCursor = (VideoPickActivity.this.getApplicationContext().getContentResolver())
              .query(
                  MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, thumbColumns, selection,
                  selectionArgs,
                  null);
          if (thumbCursor != null && thumbCursor.moveToFirst()) {
            uri_thumb = thumbCursor
                .getString(thumbCursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
          }
          String time = getFormatTime(duration);
          VideoInfo videoInfo = new VideoInfo(path, title, time, uri_thumb);
          if (!videoInfo.getThumb().equals("")) {
            mVideoInfoList.add(videoInfo);
          }
//          BitmapEntity bitmapEntity = new BitmapEntity(title, path, size, uri_thumb, duration);
//          bit.add(bitmapEntity);
        }
        if (cursor != null) {
          cursor.close();
//          if (mVideoInfoList!=null&&mVideoInfoList.size()>0){
//            for (VideoInfo videoInfo : mVideoInfoList) {
//              Log.d("SearchVedio", "videoInfo:" + videoInfo.toString());
//            }
//          }
          mHandler.sendEmptyMessage(1);
        }
      }
    }
  }

  /**
   * 获取所有的视频列表
   */
  @RequiresApi(api = VERSION_CODES.M)
  public List<VideoInfo> getListVideos() {
    return mVideoInfoList;
  }
}
