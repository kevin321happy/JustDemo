package com.custommultiexpand.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * @author czc
 * @date 2016年9月20日 下午8:01:54
 * @Description fragment父类
 */
public abstract class BaseFragment extends Fragment {

	protected Context context;
	
	@Override
	public void onAttach(Activity activity) {
		this.context = activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = initView();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initData();
		setListener();
		super.onActivityCreated(savedInstanceState);
	}

	public abstract View initView();

	public abstract void initData();

	public void setListener() {

	}
}
