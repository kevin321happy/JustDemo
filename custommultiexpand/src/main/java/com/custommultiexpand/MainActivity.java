package com.custommultiexpand;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.custommultiexpand.fragment.ContentFragment;
import com.custommultiexpand.fragment.LeftFragment;

/**
 * description:可多级展开的listview autour: Kevin company:锦绣氘(武汉)科技有限公司 date: 2017/3/22 22:10 update:
 * 2017/3/22 version: 1.21 站在峰顶 看世界 落在谷底 思人生
 */
public class MainActivity extends AppCompatActivity {

  private ContentFragment rightFragment;
  private LeftFragment leftFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  //初始化化界面
  private void initView() {
    FragmentManager manager = getFragmentManager();
    leftFragment = (LeftFragment) manager.findFragmentById(R.id.leftfragment);
    rightFragment = (ContentFragment) manager.findFragmentById(R.id.rightfragment);
  }

  public ContentFragment getContentFragment() {
    return rightFragment;
  }

  public LeftFragment getLeftFragment() {
    return leftFragment;
  }
}
