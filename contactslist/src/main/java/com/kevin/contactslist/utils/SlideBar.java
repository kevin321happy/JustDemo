package com.kevin.contactslist.utils;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.kevin.contactslist.R;

public class SlideBar extends View {

  // 26个字母
  public static String[] b = {"☆", "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
      "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
      "X", "Y", "Z"};
  private Paint mPaint;
  private int choose = -1;//被选中时为-1
  private TextView mTextDialog;
  private onTouchingLetterChangedListener mOnTouchingLetterChangedListener;

  //对外提供set方法
  public void setOnTouchingLetterChangedListener(
      onTouchingLetterChangedListener onTouchingLetterChangedListener) {
    mOnTouchingLetterChangedListener = onTouchingLetterChangedListener;
  }

  public void setTextDialog(TextView textDialog) {
    mTextDialog = textDialog;
  }

  public SlideBar(Context context) {
    this(context, null);
  }

  public SlideBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SlideBar(Context context, AttributeSet attrs, int defStyleAttr) {
    this(context, attrs);
  }

  //初始化的方法
  private void init() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


  }

  /**
   * 重写绘制的方法
   */
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int height = getHeight();//获取当前控件的高度
    int width = getWidth();//获取当前控件的宽度
    int itemheight = height / b.length;//每一个子控件的高度
    for (int i = 0; i < b.length; i++) {
      mPaint.setColor(getResources().getColor(R.color.colorPink));
      mPaint.setTextSize(40);
      if (i == choose) {
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setFakeBoldText(true);//设置加粗
      }
      //每个子条目的x,y坐标
      float posx = width / 2 - mPaint.measureText(b[i]) / 2;
      float posy = itemheight * i + itemheight;
      canvas.drawText(b[i], posx, posy, mPaint);
      mPaint.reset();
    }
  }

  //重写事件是否被拦截方法
  @RequiresApi(api = VERSION_CODES.JELLY_BEAN)
  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    int action = event.getAction();
    float y = event.getY();
    int oldchoose = choose;
    onTouchingLetterChangedListener listener = mOnTouchingLetterChangedListener;
    int c = (int) (y / getHeight() * b.length);//点击点的y坐标的高度除以总高度得到的比值乘以数组的长度得到的就是点击b中的个数
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        setBackground(new ColorDrawable(0x00000000));
        choose=-1;
        invalidate();
        if (mTextDialog!=null){
          mTextDialog.setVisibility(VISIBLE);
        }
        break;
      case MotionEvent.ACTION_MOVE:
        break;
      case MotionEvent.ACTION_UP:
        break;
      default:
        if (oldchoose!=c){
          if (listener!=null){
            listener.onTouchingLetterChanged(b[c]);
          }
          if (mTextDialog!=null){
            mTextDialog.setText(b[c]);
            mTextDialog.setVisibility(VISIBLE);
          }
          choose=c;
          invalidate();
        }
        break;
    }

    return true;

  }

  private interface onTouchingLetterChangedListener {

    public void onTouchingLetterChanged(String s);
  }
}
