package com.example.imagematrixdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements OnClickListener {

  private Button mBtn1;
  private Button mBtn2;
  private Button mBtn3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launch);
    mBtn1 = (Button) findViewById(R.id.bt1);
    mBtn2 = (Button) findViewById(R.id.bt2);
    mBtn3 = (Button) findViewById(R.id.bt3);

    mBtn1.setOnClickListener(this);
    mBtn2.setOnClickListener(this);
    mBtn3.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt1:
        startActivity(new Intent(this,MainActivity.class));
        break;
      case R.id.bt2:
        startActivity(new Intent(this,ColorMatrix.class));
        break;
      case R.id.bt3:
        break;
      default:
        break;
    }
  }
}
