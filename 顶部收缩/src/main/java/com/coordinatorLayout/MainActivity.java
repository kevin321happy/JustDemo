package com.coordinatorLayout;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private TabLayout tabLayout;
  private CollapsingToolbarLayout collapsingToolbarLayout;
  private RecyclerView recyclerView;
  private List<String> mStringList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initData();
    initView();
  }

  private void initData() {
    for (int i = 0; i < 30; i++) {
      mStringList.add("当前条目 ：" + i);
    }
  }

  private void initView() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    setSupportActionBar(toolbar);
    //显示左上返回按钮
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //设置是否显示左下角大标题
    collapsingToolbarLayout.setTitleEnabled(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    MyAdapter myAdapter = new MyAdapter();
    recyclerView.setAdapter(myAdapter);
  }

  class MyAdapter extends RecyclerView.Adapter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my, null);
      return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      String s = mStringList.get(position);
      MyViewHolder holder1 = (MyViewHolder) holder;
      if (s != null) {
        holder1.tv.setText(s);
      }
    }

    @Override
    public int getItemCount() {
      return mStringList.size();
    }

    class MyViewHolder extends ViewHolder {

      private TextView tv;

      public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
      }
    }
  }
}
