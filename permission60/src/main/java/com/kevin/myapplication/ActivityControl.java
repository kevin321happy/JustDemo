package com.kevin.myapplication;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/3/19. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 * //管理Activity
 */

public class ActivityControl {
    private static List<Activity> mActivityList=new ArrayList<>();
  //添加activity
  public static  void add(Activity activity){
    mActivityList.add(activity);
  }
  //移除Activity
  public  static  void remove(Activity activity){
    mActivityList.remove(activity);
  }
  //拿到栈顶的activi
  public static Activity getTopActivity(){
    if (mActivityList.isEmpty()){
      return null;
    }else {
      return mActivityList.get(mActivityList.size()-1);
    }
  }
}
