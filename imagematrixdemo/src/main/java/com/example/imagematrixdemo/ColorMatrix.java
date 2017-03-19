package com.example.imagematrixdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by kevin on 2017/3/19. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 * ╭╩═╮╔════╗╔════╗╔════╗╔════╗╔════╗ ╭╯ GO ╠╣   永  ╠╣  无   ╠╣   B   ╠╣   U   ╠╣  G  ╠╣
 * ╰⊙═⊙╯╚◎══◎╝╚◎══◎╝╚◎══◎╝╚◎══◎╝╚◎══◎╝
 */

public class ColorMatrix extends Activity {

  private ImageView iam;
  private GridLayout group;
  private Bitmap mBitmap;
  private int mEtwidth;
  private int mEtheight;
  private EditText[] mEts = new EditText[20];
  private float[] mColorMatrix = new float[20];


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.color_matrix);
    iam = (ImageView) findViewById(R.id.image);
    group = (GridLayout) findViewById(R.id.group);
    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
    iam.setImageBitmap(mBitmap);

    group.post(new Runnable() {
      @Override
      public void run() {
        mEtwidth = group.getWidth() / 5;
        mEtheight = group.getHeight() / 4;
        addEts();
        initMatrix();
      }
    });
  }

  //初始化矩阵
  private void initMatrix() {
    for (int i = 0; i < 20; i++) {
      if (i % 6 == 0) {
        mEts[i].setText(String.valueOf(1));
      } else {
        mEts[i].setText(String.valueOf(0));
      }
    }
  }

  //给矩阵添加孩子
  private void addEts() {
    for (int i = 0; i < 20; i++) {
      EditText editText = new EditText(this);
      mEts[i] = editText;
      group.addView(editText, mEtwidth, mEtheight);
    }

  }

  public void btnchange(View view) {

  }

  public void btnreset(View view) {

  }

  //获取用户设置的值
  public void getMatrxi() {
    for (int i = 0; i < 20; i++) {
      mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
    }
  }

  //将矩阵的颜色值设置到图片上面
  private void setIamgeMatrxi() {
   // Bitmap bitmap=Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight());

  }
}
