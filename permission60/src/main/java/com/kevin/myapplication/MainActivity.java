package com.kevin.myapplication;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.kevin.myapplication.inter.PermissionListener;
import java.util.List;

public class MainActivity extends BaseActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
  //申请权限
  public void button(View view){
    requestsPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS,
        }, new PermissionListener() {
      @Override
      public void granted() {
        Toast.makeText(MainActivity.this,"所有权限都同意了",Toast.LENGTH_SHORT).show();
      }
      @Override
      public void denied(List<String> deniedPermission) {
        for(String persmission:deniedPermission){
          Toast.makeText(MainActivity.this,"被拒绝的权限："+persmission,Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}
