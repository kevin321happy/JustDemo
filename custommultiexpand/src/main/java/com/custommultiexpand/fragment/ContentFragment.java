package com.custommultiexpand.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.custommultiexpand.MainActivity;
import com.custommultiexpand.R;
import com.custommultiexpand.bean.SideBarItem;


/**
 * Created by Mr.chen on 2016/9/30.
 * Description 内容栏
 */
public class ContentFragment extends BaseFragment {

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_content, null);
        textView = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void initData() {
        ((MainActivity)context).getLeftFragment().setCurCheckItem(0);
    }

    public void setContentText(SideBarItem item) {
        if (item != null)
            textView.setText(item.getItemName());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
