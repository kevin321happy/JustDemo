package com.kevin.exlistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactActivity extends Activity {

  private ExpandableListView mExpandableListView;
  private List<ParentInfo> mParentInfos = new ArrayList<>();
  private Map<String, List<childInfo>> mMap;
  private ArrayList<String> mGroup;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact);
    mExpandableListView = (ExpandableListView) findViewById(R.id.ex_listview);

    initData();
    MyAdapter myAdapter = new MyAdapter();
    mExpandableListView.setAdapter(myAdapter);
    mExpandableListView.setGroupIndicator(null);//取消自带的箭头
    //设置分割线
//    lv.setDivider(getResources().getDrawable(R.drawable.diyline));
    mExpandableListView.setDivider(getResources().getDrawable(R.drawable.shape_fg_bg));
    mExpandableListView.setChildDivider(getResources().getDrawable(R.drawable.shape_child));
  }
  //初始化数据
  private void initData() {
    mMap = new HashMap<String, List<childInfo>>();
    mGroup = new ArrayList<>();
    //初始化父元素的数据
    for (int i = 0; i < 5; i++) {
      String title = "第：" + i + 1 + "条目";
      mGroup.add(title);
      ArrayList<childInfo> list = new ArrayList<>();
      for (int j = 0; j < 30; j++) {
        list.add(new childInfo("张三", "www.bai.com", "武汉市洪山区"));
      }
      mMap.put(title, list);
    }
  }
  //Organization
  //可展开的Adapters
  class MyAdapter extends BaseExpandableListAdapter {
    @Override
    public int getGroupCount() {
      return mGroup.size();//父标题的大小
    }
    @Override
    public int getChildrenCount(int groupPosition) {
      String key = mGroup.get(groupPosition);//当前父标题的值
      List<childInfo> childInfos = mMap.get(key);
      return childInfos.size();//当前父标题下面孩子的数量
    }
    @Override
    public Object getGroup(int groupPosition) {
      return mGroup.get(groupPosition);//获取当前的副标题的值
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
      String key = mGroup.get(groupPosition);
      List<childInfo> childInfos = mMap.get(key);//获取父亲下面的所有的孩子
      childInfo childInfo = childInfos.get(childPosition);//根据位置获取当前的还在
      return childInfo;
    }

    @Override
    public long getGroupId(int groupPosition) {
      return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
      return childPosition;
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView,
        ViewGroup parent) {
      //显示副父标题, 即当前的分组名
      if (convertView == null) {
        convertView = LayoutInflater.from(ContactActivity.this).inflate(R.layout.parent, null);
      }
        TextView tv_parent = (TextView) convertView.findViewById(R.id.tv_parent);
        TextView tv_count = (TextView) convertView.findViewById(R.id.tv_count);
        ImageView iv_arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
        if (isExpanded){
          iv_arrow.setImageResource(R.drawable.down);
        }else {
          iv_arrow.setImageResource(R.drawable.right  );
        }
        String key = ContactActivity.this.mGroup.get(groupPosition);
        tv_parent.setText(key + "");
        int size = mMap.get(key).size();
        tv_count.setText("条目个数" + size + "");

//      convertView.setOnClickListener(new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          if (isExpanded){
//            iv_arrow.setImageResource(R.drawable.down);
//          }else {
//            iv_arrow.setImageResource(R.drawable.right);
//          }
//        }
//      });
      return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
        View convertView, ViewGroup parent) {//将孩子列表显示出来
      if (convertView == null) {
        convertView = LayoutInflater.from(ContactActivity.this).inflate(R.layout.child, null);
      }
      String key = mGroup.get(groupPosition);
      List<childInfo> childInfos = mMap.get(key);
      childInfo childInfo = childInfos.get(childPosition);
      TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
      TextView tv_pic = (TextView) convertView.findViewById(R.id.tv_pic);
      TextView tv_address = (TextView) convertView.findViewById(R.id.tv_address);
      //绑定数据

      tv_name.setText(childInfo.getName() + "");
      tv_pic.setText(childInfo.getPic() + "");
      tv_address.setText(childInfo.getAddress());

      return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
      return true;
    }
  }
}
