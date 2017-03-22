package com.example.checkboxlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * description: 可以条目多选的ListView autour: Kevin company:锦绣氘(武汉)科技有限公司 date: 2017/3/22 18:22 update:
 * 2017/3/22 version: 1.21 站在峰顶 看世界 落在谷底 思人生
 */
public class MainActivity extends AppCompatActivity {

  private ListView mListView;
  private List<String> datas = new ArrayList<>();
  // 用来控制CheckBox的选中状况
  private static HashMap<Integer, Boolean> isSelected = new HashMap<>();
  private List<String> mStringList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initData();
    mListView = (ListView) findViewById(R.id.lv);
    Button button = (Button) findViewById(R.id.yes);
    //点击确定拿到所选中的数据
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        for (String s : mStringList) {
          Log.i("test", "选中的条目 ：" + s);
        }
      }
    });
    MyAdapter adapter = new MyAdapter(datas);
    mListView.setAdapter(adapter);
  }

  private void initData() {
    for (int i = 0; i < 20; i++) {
      datas.add("我是第" + i + "个条目啊");
    }
  }

  class MyAdapter extends BaseAdapter {

    private List<String> data;
    private HashMap<Integer, Boolean> mSelected;

    public MyAdapter(List<String> datas) {
      this.data = datas;
      initcbStatus(data);

    }

    @Override
    public int getCount() {
      return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
      return data == null ? null : data.get(position);
    }
    @Override
    public long getItemId(int position) {
      return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      ViewHolder holder = null;
      String name = data.get(position);
      if (convertView == null) {
        convertView = View.inflate(MainActivity.this, R.layout.list_view_item, null);
        holder = new ViewHolder();
        holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
        holder.mTextView = (TextView) convertView.findViewById(R.id.tv_device_name);
        //绑定viewholder
        convertView.setTag(holder);
      } else {
        //去除holder
        holder = (ViewHolder) convertView.getTag();
      }
      holder.mTextView.setText(name);
      // 监听checkBox并根据原来的状态来设置新的状态
      holder.mCheckBox.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          //判断当前是否选中
          if (isSelected.get(position)) {
            //当前已经选中,设置为未选中
            isSelected.put(position, false);
            setSelected(isSelected);
            //添加到选中的集合里面
            mStringList.remove(data.get(position));
          } else {
            isSelected.put(position, true);
            setSelected(isSelected);
            //移除选中的条目
            mStringList.add(data.get(position));

          }
        }
      });
      // 根据isSelected来设置checkbox的选中状况
      holder.mCheckBox.setChecked(getIsSelected().get(position));
      return convertView;
    }

    public void setSelected(HashMap<Integer, Boolean> selected) {
      isSelected = selected;
    }

    public HashMap<Integer, Boolean> getIsSelected() {
      return isSelected;
    }
  }

  //初始化checkbox 的状态
  private void initcbStatus(List<String> data) {
    for (int i = 0; i < data.size(); i++) {
      isSelected.put(i, false);
    }
  }

  class ViewHolder {

    TextView mTextView;
    CheckBox mCheckBox;
  }

}
