package com.example.imagematrixdemo;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by kevin on 2017/3/17. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司) 图片效果处理工具类
 */

public  class IamgeHelper {
  /**
   * @param hue "色彩的色度
   * @param saturated "饱和
   * @param lun "明亮度
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

  public static Bitmap handleImageNegative(Bitmap bitmap) {
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    int color;//用来存储当前取出来的颜色,用来操作当前的ragb分量
    int r, g, b, a;

   Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    int[] oldpx = new int[width * height];
    int[] newpx = new int[width * height];
    //将像素点保存在old数组中
    bitmap.getPixels(oldpx, 0, width, 0, 0, width, height);
    //遍历每一个像素点
    for (int i = 0; i < width * height; i++) {
      color = oldpx[i];
      r = Color.red(color);
      g = Color.green(color);
      b = Color.blue(color);
      a = Color.alpha(color);

      //通过算法来显示浮雕特效
      r = 255 - r;
      g = 255 - g;
      b = 255 - b;

      if (r > 255) {
        r = 255;
      } else if (r < 0) {
        r = 0;
      }
      if (g > 255) {
        g = 255;
      } else if (g < 0) {
        g = 0;
      }
      if (b > 255) {
        b = 255;
      } else if (b < 0) {
        b = 0;
      }
      newpx[i] = Color.argb(a, r, g, b);
    }
//    bm.getPixels(oldpx, 0, width, 0, 0, width, height);
    bm.setPixels(newpx, 0, width, 0, 0, width, height);
    return bm;
  }

}
