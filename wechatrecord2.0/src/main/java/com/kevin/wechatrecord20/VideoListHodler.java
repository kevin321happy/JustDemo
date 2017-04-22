package com.kevin.wechatrecord20;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.shuyu.common.CommonRecyclerAdapter;
import com.shuyu.common.RecyclerBaseHolder;
import com.shuyu.common.model.RecyclerBaseModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideoListHodler extends RecyclerBaseHolder {

  //视频条目的布局文件
  public static final int ID = R.layout.video_item;
  @Bind(R.id.iv_thumb)
  ImageView mIvThumb;
  @Bind(R.id.tv_time)
  TextView mTvTime;
  @Bind(R.id.check)
  CheckBox mCheckBox;
  private List<RecyclerBaseModel> mVideoInfoList = new ArrayList<>();
  private int choosePosition = -1;
  // 用来控制CheckBox的选中状况
  private static HashMap<Integer, Boolean> isSelected = new HashMap<>();
  private Context mContext;
  private CommonRecyclerAdapter mCommonRecyclerAdapter;
  private boolean HASCHOOSE;//已经选中

  public VideoListHodler(Context context, View v) {
    super(context, v);
    mContext = context;
  }

  @Override
  public void setAdapter(CommonRecyclerAdapter commonRecyclerAdapter) {
    mCommonRecyclerAdapter = commonRecyclerAdapter;
    mVideoInfoList = mCommonRecyclerAdapter.getDataList();
    initcbStatus(mVideoInfoList);
  }


  @Override
  public void createView(View v) {
    ButterKnife.bind(this, v);

  }

  @Override
  public void onBind(RecyclerBaseModel model, final int position) {
    VideoInfo videoInfo = (VideoInfo) model;
    Glide.with(mContext).load(videoInfo.getThumb() + "").into(mIvThumb);
    mTvTime.setText("时长：" + videoInfo.getTotletime() + "");
    Log.i("test", "视频信息 ：" + videoInfo.toString());
    mCheckBox.setOnClickListener(new OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                     initcbStatus(mVideoInfoList);
                                     Log.i("test","是否选中状态 ："+HASCHOOSE);
                                     //如果当前的选中的条目的长度大于0，且之前的选择的条目的位置不等于当前的位置
                                     if (HASCHOOSE==true&& isSelected.get(position)==false) {
                                       Toast.makeText(mContext, "您最多只能选中一个视频", Toast.LENGTH_SHORT).show();
                                       mCheckBox.setChecked(false);
//                                       setSelected(isSelected);
                                       return;
                                     }
                                       if (isSelected.get(position)) {
                                         //当前如果已经选中设置为未选中
                                         isSelected.put(position, false);
                                         setSelected(isSelected);
//                                         choosePosition = -1;
                                         HASCHOOSE=false;
                                       } else {
                                         isSelected.put(position, true);
                                         choosePosition = position;
                                         setSelected(isSelected);
                                         HASCHOOSE=true;
                                         Log.i("test","我选中了 ："+HASCHOOSE);
                                       }
                                     }
                                 }
    );
    mCheckBox.setChecked(getIsSelected().get(position));
  }

  //更新选中的状态
  public void setSelected(HashMap<Integer, Boolean> selected) {
    isSelected = selected;
  }

  //获得选中状态
  public HashMap<Integer, Boolean> getIsSelected() {
    return isSelected;
  }

  //初始化checkbox 的状态
  private void initcbStatus(List<RecyclerBaseModel> data) {
//    HASCHOOSE=false;
    for (RecyclerBaseModel recyclerBaseModel : data) {
      Log.i("test", "所有的数据2：" + "长度 ：" + data.size() + "单个数据 ：" + recyclerBaseModel.toString());
    }
    for (int i = 0; i < data.size(); i++) {
      isSelected.put(i, false);
    }
  }
}
