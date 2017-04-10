package com.kevin.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

  private TextView tv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv = (TextView) findViewById(R.id.tv);
    //创建被观察者,事件的发起者(被观察者)
    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("你好啊RxJAva");
      }
    });
    //创建观察者,事件的响应者(订阅者)
    Subscriber subscriber = new Subscriber() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(Object o) {
        tv.setText(o + "");
      }


    };
    //被观察者订阅,关联
//    observable.subscribe(subscriber);
    //简化版
    observable.subscribe(new Action1<String>() {
      @Override
      public void call(String o) {
        tv.setText(o + "1111");
      }
    });
    Observable.just("你好啊,我是简化版的Rxjava啊").subscribe(new Action1<String>() {
      @Override
      public void call(String s) {
        tv.setText(s);
      }
    });
    //使用labdlambda表达式来写
    observable.just("呵呵呵呵").subscribe(s -> tv.setText(s));
    //操作符使用
    //Map操作符
    Observable.just("hello333").map(new Func1<String, String>() {
      @Override
      public String call(String s) {
        return s + "我是Map改造过的啊";
      }
    }).subscribe(s -> tv.setText(s));
    //使用labmda表达式来简化Map操作
    Observable.just("Labmda表达式来操作啊").map(s -> s + "这是Map来改造啊").subscribe(s -> tv.setText(s));
    Observable.just("s").map(new Func1<String, Integer>() {
      @Override
      public Integer call(String s) {
        return s.hashCode();
      }
    }).subscribe(s1 -> tv.setText(s1.toString()));
  }
}
