package com.custommultiexpand.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrchen.custommultiexpanddemo.R;
import com.example.mrchen.custommultiexpanddemo.item.SideBarItem;
import com.example.mrchen.custommultiexpanddemo.utils.DensityUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *侧边栏的适配器
 */


public class SideBarAdapter extends BaseAdapter {

	private List<SideBarItem> list;
	private Context context;
	private int dp8;
	private int dp10;
	private List<SideBarItem> tempList = new LinkedList<>();
	
	

	public SideBarAdapter(Context context, List<SideBarItem> list) {
		super();
		this.context = context;
		this.list = list;
		dp8 = DensityUtil.dip2px(context, 8);
		dp10 = DensityUtil.dip2px(context, 10);
		sortItem(list);
		removeUnExpandItem(list);
	}

	/**
	 * 对侧边栏的数据进行排序
	 * @param list
     */
	private void sortItem(List<SideBarItem> list) {

		Collections.sort(list, new Comparator<SideBarItem>() {

			@Override
			public int compare(SideBarItem lhs, SideBarItem rhs) {
				if (lhs.getOrder() - rhs.getOrder() > 0) {
					return 1;
				} else if (lhs.getOrder() - rhs.getOrder() < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		//设置侧边栏的层级和是否选中
		for (SideBarItem sideBarItem : list) {
			sideBarItem.setLevel(initItemLevel(sideBarItem));
			//因为默认只有一个选中，所以一开始进来，全部的没选中，设为false
			sideBarItem.setCheck(false);
		}

	}

	/**
	 * 移除没有展开的item。注意：父亲没有展开，儿子也不给他显示
	 * @param list
     */
	private void removeUnExpandItem(List<SideBarItem> list) {
		tempList.clear();
		for (SideBarItem sideBarItem : list) {
			if (sideBarItem.getParentItem() != null) {
				if (!sideBarItem.getParentItem().isExpand()) {
					sideBarItem.setExpand(false);
					tempList.add(sideBarItem);
				}
			}
		}
		list.removeAll(tempList);
	}

	// 递归设置level
	private int initItemLevel(SideBarItem item) {
		int level = 1;
		if (item.getParentItem() != null) {
			item.getParentItem().setHasChild(true);
			return (level + initItemLevel(item.getParentItem()));
		}
		return level;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public SideBarItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.adapter_sideritem, null);
			holder = new ViewHolder();
			holder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_item);
			holder.iv_arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SideBarItem item = getItem(position);
		//设置padding来达到层级的效果
		if (item.getLevel() == SideBarItem._First) {
			convertView.setPadding(dp10 / 2, dp8, dp8, dp10 / 2);
		} else {
			convertView.setPadding(2 * dp10 * item.getLevel() - dp10, dp8, dp8, dp10 / 2);
		}
		//如果有孩子，就有右边的小箭头，没有的话隐藏的
		if (item.isHasChild()) {
			holder.iv_arrow.setVisibility(View.VISIBLE);
			if (item.isExpand()) {
				holder.iv_arrow.setBackgroundResource(R.mipmap.inspect_bg_right_open);
			}else {
				holder.iv_arrow.setBackgroundResource(R.mipmap.inspect_bg_right_close);
			}
		} else {
			holder.iv_arrow.setVisibility(View.GONE);
		}
		//选中的字体颜色
		if (item.isCheck()) {
			holder.tv_item.setTextColor(0xFF0000FF);
		}else {
			holder.tv_item.setTextColor(0xFF000000);
		}
		holder.iv_icon.setBackgroundResource(item.getResId());
		holder.tv_item.setText(item.getItemName());
		convertView.setTag(R.id.sidebar, item);
		convertView.setTag(R.id.curPosition, position);
		convertView.setOnClickListener(clickListener);
		return convertView;
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			list.addAll(tempList);
			sortItem(list);
			SideBarItem item = (SideBarItem) v.getTag(R.id.sidebar);
			int curPosition = (int) v.getTag(R.id.curPosition);
			item.setCheck(!item.isCheck());
			item.setExpand(!item.isExpand());
			removeUnExpandItem(list);
			//设置回调
			if (listener!=null) {
				listener.onSideBarItemClick(item,curPosition);
			}
			notifyDataSetChanged();
		}
	};

	class ViewHolder {
		TextView tv_item;
		ImageView iv_icon;
		ImageView iv_arrow;
	}
	
	private SideBarListener listener;
	
	public void setSideBarListener(SideBarListener listener){
		this.listener = listener;
	}
	
	public interface SideBarListener{
		void onSideBarItemClick(SideBarItem item, int position);
	}

	/**
	 * 设置当前选中的是那个item
	 * @param index
     */
	public void setCurCheckItem(int index) {
		list.addAll(tempList);
		sortItem(list);
		SideBarItem item = getItem(index);
		item.setCheck(true);
		item.setExpand(true);
		removeUnExpandItem(list);
		notifyDataSetChanged();
	}

}
