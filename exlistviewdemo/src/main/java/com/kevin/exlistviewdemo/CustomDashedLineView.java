package com.kevin.exlistviewdemo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * description: 自定义虚线 autour: Kevin company:锦绣氘(武汉)科技有限公司 date: 2017/4/21 14:43 update: 2017/4/21
 * version: 1.21 站在峰顶 看世界 落在谷底 思人生
 */

public class CustomDashedLineView extends View {

  public CustomDashedLineView(Context context) {
    this(context, null);
  }

  public CustomDashedLineView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CustomDashedLineView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  //绘制虚线
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(getResources().getColor(R.color.colorAccent));
    /*   Path path = new Path();
        path.moveTo(0, 5);
        path.lineTo(this.getWidth(), 5);

        PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    * */

    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.GRAY);
    Path path = new Path();
    path.moveTo(0, 3);
    path.lineTo(this.getWidth(), 3);

    DashPathEffect pathEffect = new DashPathEffect(new float[]{5,5}, 1);
    paint.setPathEffect(pathEffect);
    canvas.drawPath(path, paint);
  }
}
