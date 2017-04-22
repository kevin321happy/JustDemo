package com.kevin.interdebug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.BaseRequest;
import com.lzy.okrx.RxAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.Call;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

  private TextView mTv_content;
  private Map<String, String> mMap = new TreeMap<>();
  //  private String URL="http://admin.52qmct.com:8080/hsWebInterface";
  private String URL = "http://172.16.3.30/";
  private Button mBt_jiami;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mTv_content = (TextView) findViewById(R.id.tv_content);
    mBt_jiami = (Button) findViewById(R.id.bt_jiami);
//    initParam();
//    initData();
  }

  //联网操作
  private void initData() {
    HashMap<String, String> signdata = new HashMap<>();
    long time = System.currentTimeMillis() / 1000;
    Log.i("test", time + "时间戳");

    String timestamp = String.valueOf(time);
    signdata.put("user_name", "sunjin");
    signdata.put("pass_word", "123456");
    signdata.put("timestamp", timestamp);
    String[] Data = RuleTool.getSignData(signdata);
    OkGo.post(URL + "user_post")
        .params("user_name", "sunjin")
        .params("pass_word", "123456")
        .params("timestamp", timestamp)
        .params("sign", Data[0])
        .params("str", Data[1])
        .execute(new StringCallback() {
          @Override
          public void onBefore(BaseRequest request) {
            super.onBefore(request);
          }

          @Override
          public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);
          }

          @Override
          public void onSuccess(String s, Call call, Response response) {
            Log.i("test", "返回结果 ：" + s);
          }
        });
  }

  private void initParam() {
    mMap.put("uid", "22");
    mMap.put("name", "zs");
    mMap.put("activityId", "222");
    mMap.put("courseId", "213");
    mMap.put("timestamp", System.currentTimeMillis() + "");
    String[] signData = RuleTool.getSignData(mMap);
    String signd = signData[0];
    String str = signData[1];
    Log.i("test", "signData :" + signd + "Str ；=" + str);
  }

  //接口调试
  public void deBug(View view) {
  }

  //Md5加密
  public void MdFive(View view) {
    String s = Md5Utils.MD5("asd%#!123&");
    Log.i("test", "MD :" + s);
    mTv_content.setText(s);

  }

  //点击联网
  public void contact(View view) {
    initData();

  }

  public void getKey(View view) {
    OkGo.post(URL + "app/getSecret")
        .params("app_id", "qmct")
        .execute(new StringCallback() {
          @Override
          public void onSuccess(String s, Call call, Response response) {
            Log.i("test", s);
          }
        });
  }

  //使用OKRx来联网
  public void okRx(View view) {
    HashMap<String, String> signdata = new HashMap<>();
    long time = System.currentTimeMillis() / 1000;
    Log.i("test", time + "时间戳");

    String timestamp = String.valueOf(time);
    signdata.put("user_name", "sunjin");
    signdata.put("pass_word", "123456");
    signdata.put("timestamp", timestamp);
    String[] Data = RuleTool.getSignData(signdata);
    Observable<String> call = OkGo.post( URL + "user_post")//
        .params("user_name", "sunjin")
        .params("pass_word", "123456")
        .params("timestamp", timestamp)
        .params("sign", Data[0])
        .params("str", Data[1])
        .getCall(StringConvert.create(), RxAdapter.<String>create());
    call.doOnSubscribe(new Action0() {
      @Override
      public void call() {
      }
    })
        .observeOn(AndroidSchedulers.mainThread())//切换到主线程
        .subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            Log.i("test",s);
            Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
          }
        });

  }
  public void jiami(View view) {
    mBt_jiami.setText(Md5Utils.MD5("xiaowenxiang123"));
  }
}
