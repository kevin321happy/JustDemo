package com.example.checkboxlistview.mvp.login;


import android.os.Bundle;
import android.widget.Toolbar;
import com.example.checkboxlistview.R;
import com.example.checkboxlistview.mvp.MVPBaseActivity;
import com.example.checkboxlistview.mvp.login.LoginContract.View;




/**
 * MVPPlugin
 * �? 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<View, LoginPresenter> implements LoginContract.View {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  @Override
  public void setActionBar(Toolbar toolbar) {
    super.setActionBar(toolbar);
  }
}
