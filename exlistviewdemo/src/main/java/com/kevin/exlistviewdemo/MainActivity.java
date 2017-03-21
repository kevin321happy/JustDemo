package com.kevin.exlistviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  //点击跳转到可展开的listview
  public void jump(View view) {
    startActivity(new Intent(this,ExListViewActivity.class));
  }
}
