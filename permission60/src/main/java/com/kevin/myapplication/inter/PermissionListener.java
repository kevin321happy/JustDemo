package com.kevin.myapplication.inter;

/**
 * Created by kevin on 2017/3/19. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司) 权限监听的接口
 */

public interface PermissionListener {

  //已授权
  void granted();

  //权限拒绝
  void denied();
}
