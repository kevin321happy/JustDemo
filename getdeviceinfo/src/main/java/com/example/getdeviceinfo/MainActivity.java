package com.example.getdeviceinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.version_code)
  TextView mVersionCode;
  @Bind(R.id.version_name)
  TextView mVersionName;
  @Bind(R.id.deviceId)
  TextView mDeviceId;
  @Bind(R.id.PhoneBrand)
  TextView mPhoneBrand;
  @Bind(R.id.PhoneModel)
  TextView mPhoneModel;
  @Bind(R.id.BuildLevel)
  TextView mBuildLevel;
  @Bind(R.id.BuildVersion)
  TextView mBuildVersion;
  @Bind(R.id.activity_main)
  LinearLayout mActivityMain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    bindView();

  }

  //绑定控件
  private void bindView() {
//    DeviceUtil deviceUtil = DeviceUtil.getInstance();
    mVersionCode.setText("设备的版本号" + DeviceUtil.getVersionCode(getApplicationContext()));
    mVersionName.setText("设备的版本名" + DeviceUtil.getVersionName(getApplicationContext()));
    mBuildLevel.setText("Android的API版本" + DeviceUtil.getBuildLevel());
    mBuildVersion.setText("设备当前系统" + DeviceUtil.getBuildVersion());
    mPhoneBrand.setText("设备的品牌：" + DeviceUtil.getPhoneBrand());
    mPhoneModel.setText("设备的型号 ：" + DeviceUtil.getPhoneModel());
    mDeviceId.setText("设备的唯一标识 ：" + DeviceUtil.getDeviceId(getApplication()));

  }
}
