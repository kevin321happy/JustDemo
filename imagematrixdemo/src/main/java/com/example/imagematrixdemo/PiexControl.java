package com.example.imagematrixdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class PiexControl extends Activity {

  private ImageView iv_1;
  private ImageView iv_2;
  private ImageView iv_3;
  private ImageView iv_4;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_piex);
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b);

    iv_1 = (ImageView) findViewById(R.id.iv_1);
    iv_2 = (ImageView) findViewById(R.id.iv_2);
    iv_3 = (ImageView) findViewById(R.id.iv_3);
    iv_4 = (ImageView) findViewById(R.id.iv_4);

    iv_1.setImageBitmap(bitmap);
    Bitmap negative = IamgeHelper.handleImageNegative(bitmap);
    iv_3.setImageBitmap(negative);
    iv_2.setImageBitmap(negative);
  }
}
