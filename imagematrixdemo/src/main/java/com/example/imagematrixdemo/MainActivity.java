package com.example.imagematrixdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends AppCompatActivity implements OnSeekBarChangeListener {

  private ImageView mImageView;
  private SeekBar mSb2;
  private SeekBar mSb1;
  private SeekBar mSb3;
  private static int MAX_VALUE = 255;
  private static int MID_VALUE = 127;
  private float mHue, mSaturate, mLum;
  private Bitmap mBitmap;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    mBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.a);
    mImageView = (ImageView) findViewById(R.id.iv);
    mImageView.setImageBitmap(mBitmap);
    mSb1 = (SeekBar) findViewById(R.id.sb_1);
    mSb2 = (SeekBar) findViewById(R.id.sb_2);
    mSb3 = (SeekBar) findViewById(R.id.sb_3);
    mSb1.setOnSeekBarChangeListener(this);
    mSb2.setOnSeekBarChangeListener(this);
    mSb3.setOnSeekBarChangeListener(this);
    mSb1.setMax(MAX_VALUE);
    mSb2.setMax(MAX_VALUE);
    mSb3.setMax(MAX_VALUE);
    //设置初始值
    mSb1.setProgress(MID_VALUE);
    mSb2.setProgress(MID_VALUE);
    mSb3.setProgress(MID_VALUE);
  }

  public void btone(View view) {

  }

  //seekbar监听事件
  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    switch (seekBar.getId()) {
      case R.id.sb_1:
        mHue=(progress-MID_VALUE)*1.0f/MID_VALUE*180;
        break;
      case R.id.sb_2:
        mSaturate=progress*1.0f/MID_VALUE;
        break;
      case R.id.sb_3:
        mLum=progress*1.0f/MID_VALUE;
        break;
    }
    mImageView.setImageBitmap(IamgeHelper.handleImageEffect(mBitmap, mHue, mSaturate, mLum));
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {

  }
}
