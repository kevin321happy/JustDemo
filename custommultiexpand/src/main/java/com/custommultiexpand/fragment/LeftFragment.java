package com.custommultiexpand.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.custommultiexpand.MainActivity;
import com.custommultiexpand.R;
import com.custommultiexpand.bean.SideBarItem;
import com.custommultiexpand.ui.adapter.SideBarAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class LeftFragment extends BaseFragment {

  private ListView mListView;
  private Collection<SideBarItem> list=new ArrayList<>();
  private SideBarAdapter adapter;

  @Override
  public View initView() {
    View view=View.inflate(getActivity(), R.layout.fragment_left,null);
    mListView =  (ListView) view.findViewById(R.id.lv_sidebar);
    return view;
  }


    /**
     * 结构视图（顺序与命名都采用层级式递增）
     *	 -1
     *    -11
     *    -12
     *    -13
     */
    @Override
    public void initData() {
      SideBarItem item1 = new SideBarItem("处理流程", 1, R.drawable.icon_mark1);
      SideBarItem item2 = new SideBarItem("申报流程", 2, R.drawable.icon_mark1);

      SideBarItem item21 = new SideBarItem("预约", 2.1f, R.drawable.icon_mark1);
      SideBarItem item22 = new SideBarItem("申请", 2.2f, R.drawable.icon_mark2);
      SideBarItem item23 = new SideBarItem("通报", 2.3f, R.drawable.icon_mark3);

      SideBarItem item221 = new SideBarItem("处理", 2.21f, R.drawable.icon_mark1);
      SideBarItem item222 = new SideBarItem("反馈", 2.22f, R.drawable.icon_mark2);
      SideBarItem item223 = new SideBarItem("错误", 2.23f, R.drawable.icon_mark3);

      item22.setParentItem(item2);
      item23.setParentItem(item2);
      item21.setParentItem(item2);

      item222.setParentItem(item22);
      item223.setParentItem(item22);
      item221.setParentItem(item22);


      list.add(item1);
      list.add(item2);
      list.add(item21);
      list.add(item22);
      list.add(item23);
      list.add(item221);
      list.add(item222);
      list.add(item223);

      adapter = new SideBarAdapter(context, (List<SideBarItem>) list);
      mListView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
      adapter.setSideBarListener(new SideBarAdapter.SideBarListener() {
        @Override
        public void onSideBarItemClick(SideBarItem item, int position) {

          ((MainActivity)context).getContentFragment().setContentText(item);
          Toast.makeText(context,item.getItemName(),Toast.LENGTH_SHORT).show();
        }
      });
      super.setListener();
    }

    public void setCurCheckItem(int index){
      adapter.setCurCheckItem(index);
    }

}
