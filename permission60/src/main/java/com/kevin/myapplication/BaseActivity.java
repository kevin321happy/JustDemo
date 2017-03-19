package com.kevin.myapplication;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.kevin.myapplication.inter.PermissionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/3/19. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 */

public class BaseActivity extends AppCompatActivity {

  private static PermissionListener mPermissionListener;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityControl.add(this);
  }

  //申请权限的方法
  public static void requestsPermission(String[] permissions, PermissionListener listener) {
    Activity topActivity=ActivityControl.getTopActivity();
    mPermissionListener = listener;
    if (topActivity==null){
      return;
    }
    List<String>permissionList=new ArrayList<>();
    for (String permission : permissions) {
      /* if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }*/
      //遍历所有申请的权限,如果没有就添加到权限的列表里
      if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED){
        permissionList.add(permission);
      }
      if (!permissionList.isEmpty()){
        ActivityCompat.requestPermissions(topActivity,permissionList.toArray(new String[permissionList.size()]),1);
      }else {
        mPermissionListener.granted();
      }
    }
  }
  //权限申请的回调
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode){
      case 1:
        if (grantResults.length > 0) {
          List<String> deniedPermission = new ArrayList<>();
          for (int i = 0; i < grantResults.length; i++) {
            int grantResult = grantResults[i];
            String permission = permissions[i];
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
              deniedPermission.add(permission);
            }
          }
          if (deniedPermission.isEmpty()) {
            mPermissionListener.granted();
          } else {
            mPermissionListener.denied(deniedPermission);
          }
        }
      default:
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ActivityControl.remove(this);
  }
}
