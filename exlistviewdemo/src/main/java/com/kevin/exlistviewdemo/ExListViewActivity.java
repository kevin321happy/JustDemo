package com.kevin.exlistviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

/**
 * Created by kevin on 2017/3/21. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司) 可展开的列表界面
 */

public class ExListViewActivity extends Activity {

  private ExpandableListView mExlist;
  private static final String GROUP_TEXT = "group_text";//大组成员Map的key  (第几大组)
  private static final String CHILD_TEXT1 = "child_text1";//小组成员Map的第一个key (第几小组)
  private static final String CHILD_TEXT2 = "child_text2";//小组成员Map的第二个key  (第几小组的签名)

  List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();//大组成员
  List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();//小组成员

  private String[] gruoplist = {"同学", "朋友", "亲人", "同事", "基友", "未知"};
  private String[] childlist = {"吴卫", "老藤", "小肥羊", "思思", "小孔雀", "峰哥", "娟姐", "kevin"};
  private String[] signaturelist = {"哈哈", "呵呵", "喔喔", "嘻嘻", "恩恩", "额额", "嘿嘿", "...."};
  private ExAdapter mExAdapter;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.exlist_activity);
    initView();
  }

  private void initView() {
    mExlist = (ExpandableListView) findViewById(R.id.list);
    initData();


  }

  //添加数据
  private void initData() {
    for (int i = 0; i < 6; i++) {
      Map<String, String> groupMap = new HashMap<String, String>();
      //添加大组的成员
      groupData.add(groupMap);
      Log.i("test","添加的孩子 ;"+gruoplist[i]);
      groupMap.put(GROUP_TEXT, gruoplist[i]);
      //每个大组下面的孩子的集合
      List<Map<String, String>> child = new ArrayList<Map<String, String>>();
      for (int j = 0; j < 8; j++) {
        //添加 小组的成员
        Map<String, String> curchild = new HashMap<String, String>();
        //将小组的孩子对象添加到集合里面
        child.add(curchild);
        curchild.put(CHILD_TEXT1, childlist[i]);
        curchild.put(CHILD_TEXT2, signaturelist[i]);
      }
      //添加到所有孩子的大集合里面
      childData.add(child);
    }
    mExAdapter = new ExAdapter(this);
    mExlist.setAdapter(mExAdapter);
    mExlist.setGroupIndicator(null);//不设置大组的指示器图标,自定义设置了
    mExlist.setDivider(null);//设置图片可拉伸的

  }

  //关键代码
  class ExAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    public ExAdapter(ExListViewActivity exListViewActivity) {
      mContext = exListViewActivity;
    }

    @Override
    public int getGroupCount() {
      return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
      return childData.get(groupPosition).size();
    }

    //获得大组成员的名称
    @Override
    public Object getGroup(int groupPosition) {
      return groupData.get(groupPosition).get(GROUP_TEXT).toString();
    }

    //  获得小组组成员的名称
    @Override
    public Object getChild(int groupPosition, int childPosition) {
      return childData.get(groupPosition).get(childPosition).get(CHILD_TEXT1)
          .toString();
    }

    @Override
    public long getGroupId(int groupPosition) {
      return groupPosition;
    }

    //获得小组成员的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
      return childPosition;
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the underlying data.
     * 表明大組和小组id是否稳定的更改底层数据。
     * @return whether or not the same ID always refers to the same object
     * @see “Adapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
      return true;
    }

    //得到大组成员的View
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
        ViewGroup parent) {
      View view = convertView;
      if (view == null) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(
            Context.LAYOUT_INFLATER_SERVICE);
         view = inflater.inflate(R.layout.group_item, null);
      }
      TextView content01 = (TextView) view.findViewById(R.id.content_001);
      content01.setText(groupData.get(groupPosition).get(GROUP_TEXT).toString());//设置大组的成员的名称
      String s = groupData.get(groupPosition).toString();
      Log.i("test",s);
      ImageView tubiao = (ImageView) view.findViewById(R.id.tubiao);
      if (isExpanded) {
        //当展开的时候图标向上
        tubiao.setImageResource(R.drawable.btn_browser2);
      } else {
        //当未展开时
        tubiao.setImageResource(R.drawable.btn_browser);
      }
      return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
        View convertView, ViewGroup parent) {
      View view =convertView;
      if (view==null){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.child_item,null);
      }
      TextView title = (TextView) view.findViewById(R.id.child_text);
      title.setText(childData.get(groupPosition).get(childPosition).get(CHILD_TEXT1).toString());
      TextView title2 = (TextView) view.findViewById(R.id.child_text2);
      title2.setText(childData.get(groupPosition).get(childPosition).get(CHILD_TEXT2).toString());
      return view;
    }

    //小组成员是否被选择
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
      return true;
    }
  }
}
