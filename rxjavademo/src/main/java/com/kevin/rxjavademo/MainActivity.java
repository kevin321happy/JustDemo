package com.kevin.rxjavademo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private Subscriber<String> mSubscriber;
  private Observable mObservable;
  private ImageView mIv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mIv = (ImageView) findViewById(R.id.iv);
    initRx();
  }

  private void initRx() {
    //1、创建Observe观察者(它决定事件触发的时候将有怎样的行为，台灯)
    mSubscriber = new Subscriber<String>() {
      @Override
      public void onStart() {
        super.onStart();
        Log.i(TAG, "onstart");
      }

      @Override
      public void onCompleted() {
        Log.i(TAG, "Item: " + "onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        Log.i(TAG, e + "");
      }

      @Override
      public void onNext(String s) {
        Log.i(TAG, s);
      }
    };
    //创建被观察者(事件的发起者,当事件产生都会执行他的call方法)
    //create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列
    mObservable = Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("执行事件1");
        subscriber.onNext("执行事件2");
        subscriber.onNext("执行事件3");
        subscriber.onNext("执行事件4");
        subscriber.onCompleted();
      }
    });
    //第三步 Subscribe (订阅)被观察者订阅观察者
//    Log.i(TAG,"即将开始订阅事件");
//    Subscription subscribe = mObservable.subscribe(mSubscriber);
//    Log.i(TAG,"已经完成事件订阅");
//    subscribe.unsubscribe();
//    Log.i(TAG,"取消事件订阅释放内存");
    //订阅的核心代码
//    public Subscription subscribe(Subscriber subscriber) {
//      subscriber.onStart();
//      onSubscribe.call(subscriber);
//      return subscriber;}
//    调用 Subscriber.onStart() 。这个方法在前面已经介绍过，是一个可选的准备方法。
//    调用 Observable 中的 OnSubscribe.call(Subscriber) 。在这里，事件发送的逻辑开始运行。从这也可以看出，在 RxJava 中， Observable 并不是在创建的时候就立即开始发送事件，而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
//    将传入的 Subscriber 作为 Subscription 返回。这是为了方便 unsubscribe().
  }

  /**
   * //使用RxJava Observe观察者(它决定事件触发的时候将有怎样的行为，台灯) subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的：
   * 不过subscriber多出了 onStart() 方法做准备工作以及unsubscribe(): 释放内存避免内存泄漏
   */
  public void RxButton(View View) {
    //普通的订阅
    Subscription subscribe = mObservable.subscribe(mSubscriber);
    //取消订阅释放内存
    subscribe.unsubscribe();
  }

  //不完整订阅
  public void IncompleteSub(View view) {
    Action1 onNextAction = new Action1<String>() {
      @Override
      public void call(String s) {
        Log.i(TAG, "action1:" + s);
      }
    };
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {

      @Override
      public void call(Throwable throwable) {
        //执行错误的操作
      }
    };
    new Action0() {
      //oncompleted
      @Override
      public void call() {
        Log.i(TAG, "completed");
      }
    };
  }

  // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
//  mObservable.OnSubscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
//  observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
//  observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
//打印名字
  public void PrintSub(View view) {
    PrintOutArray();
    LoadImage();
  }

  //加载图片显示
  private void LoadImage() {
    //线程控制 —— Scheduler (一)指定代码运行在什么样的线程里面
    //subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
    //observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。（事件的回调）

    Observable.create(new OnSubscribe<Drawable>() {
      @Override
      public void call(Subscriber<? super Drawable> subscriber) {
        Drawable drawable = getResources().getDrawable(R.drawable.a);
        subscriber.onNext(drawable);
        subscriber.onCompleted();
      }
    }).subscribeOn(Schedulers.io())//事件发生线程在IO线程
        .observeOn(AndroidSchedulers.mainThread())//事件回调处理在主线程
        .subscribe(new Observer<Drawable>() {

          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onNext(Drawable drawable) {
            mIv.setImageDrawable(drawable);
          }
        });

  }

  //打印一个数组
  private void PrintOutArray() {
    String[] names = {"张三，李四，王五，赵六，孙七"};
    Observable.from(names)
        .subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            Log.i(TAG, "打印名字 ：" + s);
          }
        });

  }

}
