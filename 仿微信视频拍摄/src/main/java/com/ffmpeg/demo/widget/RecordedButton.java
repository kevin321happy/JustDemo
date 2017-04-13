package com.ffmpeg.demo.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ffmpeg.demo.R;
import java.util.ArrayList;
import java.util.List;
//点击拍摄的button

/**
 * description: 点击录制的button autour: Kevin company:锦绣氘(武汉)科技有限公司 date: 2017/4/12 23:18 update:
 * 2017/4/12 version: 1.21 站在峰顶 看世界 落在谷底 思人生
 */
public class RecordedButton extends View {

  private float radius1;//外圈圆半径
  private float radius2;//内圈圆的半径
  private float zoom = 0.8f;//初始化缩放的比例
  private int dp5;
  private int colorBlue;
  private int colorGray;
  private Paint mPaint;
  private Paint paintProgress;
  private boolean isResponseLongTouch;
  private OnGestureListener mOnGestureListener;
  private Paint paintSplit;
  private Paint paintDelete;
  private RectF mOval;
  private float progress;
  private int measuredWidth = -1;
  private float RowX = -1;
  private float RowY = -1;
  private float DownX;
  private float DownY;
  private boolean isDeleteMode;
  /**
   * 当前进度 以角度为单位
   */
  private float girthPro;
  //断点集合
  private List<Float> splitList = new ArrayList<>();
  private long animTime = 150;

  public RecordedButton(Context context) {
    super(context);
    init();
  }


  public RecordedButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public RecordedButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    dp5 = (int) getResources().getDimension(R.dimen.dp5);
    colorBlue = getResources().getColor(R.color.blue);
    colorGray = getResources().getColor(R.color.gray);

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //绘制进度的画笔
    paintProgress = new Paint();
    paintProgress.setAntiAlias(true);
    paintProgress.setColor(colorBlue);
    paintProgress.setStrokeWidth(dp5);
    paintProgress.setStyle(Paint.Style.STROKE);

    paintSplit = new Paint();
    paintSplit.setAntiAlias(true);
    paintSplit.setColor(Color.WHITE);
    paintSplit.setStrokeWidth(dp5);
    paintSplit.setStyle(Paint.Style.STROKE);
    //清除的画笔
    paintDelete = new Paint();
    paintDelete.setAntiAlias(true);
    paintDelete.setColor(Color.RED);
    paintDelete.setStrokeWidth(dp5);
    paintDelete.setStyle(Paint.Style.STROKE);
    // 设置绘制的大小
    mOval = new RectF();
  }

  /**
   * 获取进度
   */
  public float getProgress() {
    return progress;
  }

  /**
   * 是否响应长按事件
   */
  public void setResponseLongTouch(boolean responseLongTouch) {
    isResponseLongTouch = responseLongTouch;
  }

  /**
   * 设置手势的监听
   */
  public void setOnGestureListener(
      OnGestureListener onGestureListener) {
    mOnGestureListener = onGestureListener;
  }

  /**
   * 手势的监听
   */
  public interface OnGestureListener {

    void onLongClick();

    void onClick();

    void onLift();

    void onOver();

  }

  /**
   * 绘制的handler
   */
  private Handler drawHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
    }
  };

  private float firstX;
  private float firstY;

  /**
   * 触摸监听
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (isResponseLongTouch) {
          drawHandler.sendEmptyMessageDelayed(0, animTime);
          firstX = DownX = event.getX();
          firstY = DownY = event.getY();
        }
        break;
      case MotionEvent.ACTION_MOVE:
        float MoveX=event.getRawX();
        float MoveY=event.getRawY();


        break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return true;
  }

  /**
   * 测量的方法
   */
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (measuredWidth == -1) {
      measuredWidth = getMeasuredWidth();
      radius1 = measuredWidth * zoom / 2;
      radius2 = measuredWidth * zoom / 2 - dp5;
      mOval.left = dp5 / 2;
      mOval.top = dp5 / 2;
      mOval.right = measuredWidth - dp5 / 2;
      mOval.bottom = measuredWidth - dp5 / 2;
    }
  }

  /**
   * 控件摆放
   */
  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if (RowX == -1) {
      RowX = getX();
      RowY = getY();
    }
  }

  /**
   * 绘制的方法
   */
  @Override
  protected void onDraw(Canvas canvas) {
    //绘制外圈的圆
    mPaint.setColor(Color.WHITE);
    canvas.drawCircle(measuredWidth / 2, measuredWidth / 2, radius1, mPaint);
    //绘制内圈的圆
    mPaint.setColor(getResources().getColor(R.color.blue));
    canvas.drawCircle(measuredWidth / 2, measuredWidth / 2, radius2, mPaint);
    //绘制进度(弧形)参数1：弧形的外切矩形  参数2：起始角度   参数3：当前角度  参数4 是否有中心点  参数5：画笔
    canvas.drawArc(mOval, 270, girthPro, false, paintProgress);
    //绘制段点
    for (int i = 0; i < splitList.size(); i++) {
      if (1 != 0) {
        canvas.drawArc(mOval, 270 + splitList.get(i), 1, false, paintSplit);
      }
    }
    //绘制删除模式的段点
    if (isDeleteMode && splitList.size() > 0) {
      float split = splitList.get(splitList.size() - 1);
      canvas.drawArc(mOval, 270 + split, girthPro - split, false, paintDelete);
    }
  }
}
