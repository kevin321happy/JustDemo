package com.kevin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kevin on 2017/3/19. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 */

public class BaseActivity extends AppCompatActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  //申请权限的方法
  public void requestsPermission() {


  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
