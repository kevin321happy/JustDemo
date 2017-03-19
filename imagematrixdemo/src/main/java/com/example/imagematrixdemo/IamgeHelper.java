package com.example.imagematrixdemo;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by kevin on 2017/3/17. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司) 图片效果处理工具类
 */

public class IamgeHelper {

  /**
   * @param  "bitmap图片
   * @param hue "色彩的色度
   * @param saturated "饱和
   * @param lun "明亮度
   * @return
   */
  public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturated, float lun) {
    Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Config.ARGB_8888);
    //创建画布
    Canvas canvas = new Canvas(bitmap);
    //创建画笔
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //设置色度
    ColorMatrix hueMatrix = new ColorMatrix();
    hueMatrix.setRotate(0, hue);//红
    hueMatrix.setRotate(1, hue);//绿
    hueMatrix.setRotate(2, hue);//蓝
    //设置饱和度
    ColorMatrix staturateMatrix = new ColorMatrix();
    staturateMatrix.setSaturation(saturated);
    //设置饱和
    ColorMatrix lunMatrix = new ColorMatrix();
    lunMatrix.setScale(lun, lun, lun, 1);
    ColorMatrix imageMatrix = new ColorMatrix();
    imageMatrix.postConcat(hueMatrix);
    imageMatrix.postConcat(staturateMatrix);
    imageMatrix.postConcat(lunMatrix);
    paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
    canvas.drawBitmap(bm, 0, 0, paint);
    return bitmap;
  }

}
