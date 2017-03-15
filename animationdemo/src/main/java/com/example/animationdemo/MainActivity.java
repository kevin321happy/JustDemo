package com.example.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private Button rotateButton;
  private Button scaleButton;
  private Button alphaButton;
  private Button translateButton;
  private Button set;
  private ImageView image;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    rotateButton = (Button) findViewById(R.id.rotateButton);

    scaleButton = (Button) findViewById(R.id.scaleButton);

    alphaButton = (Button) findViewById(R.id.alphaButton);

    translateButton = (Button) findViewById(R.id.translateButton);

    set = (Button) findViewById(R.id.set);

    image = (ImageView) findViewById(R.id.image);
    initListener();
  }

  private void initListener() {
    rotateButton.setOnClickListener(this);
    scaleButton.setOnClickListener(this);
    alphaButton.setOnClickListener(this);
    translateButton.setOnClickListener(this);
    set.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.alphaButton://透明
        startalpha();
        break;
      case R.id.rotateButton://旋转
        startrotate();
        break;
      case R.id.scaleButton://缩放
        startscale();
        break;
      case R.id.translateButton://平移
        starttranslate();
        break;
      case R.id.set://组合动画
        set();
        break;
    }
  }

  //组合动画
  private void set() {
    ObjectAnimator moveIn = ObjectAnimator.ofFloat(image, "translationX", -500f, 0f);
    ObjectAnimator rotate = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
    ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(image, "alpha", 1f, 0f, 1f);
    ObjectAnimator scaleY = ObjectAnimator.ofFloat(image, "scaleX", 1f, 1.2f, 1.5f, 2f, 1f);
    AnimatorSet set = new AnimatorSet();
    set.play(rotate).with(scaleY).after(moveIn).with(fadeInOut);
    set.setInterpolator(new BounceInterpolator());
    set.setDuration(2000);
    set.start();
  }

  //透明
  //@RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  private void startalpha() {
    ObjectAnimator alpha = ObjectAnimator.ofFloat(image, "alpha", 1f, 0.5f, 0.2f, 0f, 1f);
    alpha.setDuration(5000);
    //alpha.setAutoCancel(false);
    alpha.setEvaluator(new TypeEvaluator() {
      @Override
      public Object evaluate(float fraction, Object startValue, Object endValue) {
        return fraction;
      }
    });
    alpha.setRepeatMode(ValueAnimator.REVERSE);
    alpha.setInterpolator(new DecelerateInterpolator());
    alpha.setRepeatCount(2);
    alpha.start();
  }

  private void starttranslate() {
    float translationX = image.getTranslationX();
    ObjectAnimator translation = ObjectAnimator
        .ofFloat(image, "translationX", translationX, translationX - 500f, translationX - 300f,
            translationX - 200f, translationX, translationX + 200f, translationX + 300f,
            translationX + 500f);
    translation.setDuration(5000);
    translation.setInterpolator(new CycleInterpolator(10f));
    translation.setRepeatMode(ValueAnimator.RESTART);
    translation.setRepeatCount(3);
    translation.start();
  }

  private void startscale() {
    ObjectAnimator scaleY = ObjectAnimator.ofFloat(image, "scaleX", 1f, 1.2f, 1.5f, 2f, 1f);
    scaleY.setDuration(2000);
    scaleY.setRepeatMode(ValueAnimator.REVERSE);
    scaleY.setRepeatCount(3);
    scaleY.setObjectValues(0.1f, 0.3f, 0.9f, 1f, 2f, 3f, 1f);
    scaleY.start();
    scaleY.addUpdateListener(new AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        if (BuildConfig.DEBUG) {
          Log.d("MainActivity", "animation.getValues():" + animation.getValues());
        }
      }
    });
  }

  //旋转
  private void startrotate() {
    ObjectAnimator animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 180f, 360f, 270f, 180f);
    animator.setDuration(500);
    animator.setRepeatCount(3);
    animator.setRepeatMode(ValueAnimator.RESTART);
    animator.setInterpolator(new LinearInterpolator());
    animator.start();
  }
}
