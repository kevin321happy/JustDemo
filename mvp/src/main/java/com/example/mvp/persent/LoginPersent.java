package com.example.mvp.persent;


import android.app.Activity;
import com.example.mvp.inter.LoginInter;

public class LoginPersent implements LoginInter {

  private Activity mActivity;

  public LoginPersent(Activity activity) {
    mActivity = activity;
  }

  @Override
  public void loginIn(String username, String psw) {

  }

  @Override
  public void loginOut(String username, String psw) {

  }
}
