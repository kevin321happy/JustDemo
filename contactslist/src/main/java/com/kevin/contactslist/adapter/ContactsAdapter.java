package com.kevin.contactslist.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kevin.contactslist.MainActivity;
import com.kevin.contactslist.R;
import com.kevin.contactslist.bean.PersonDto;
import java.util.List;


public class ContactsAdapter extends BaseAdapter {

  private List<PersonDto> mList;
  private Context mContext;

  public ContactsAdapter(MainActivity mainActivity,
      List<PersonDto> personDtos) {
    mContext=mainActivity;
    mList = personDtos;
  }

  @Override
  public int getCount() {
    return mList == null ? 0 : mList.size();
  }

  @Override
  public Object getItem(int position) {
    return mList == null ? null : mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    PersonDto personDto = mList.get(position);
    if (personDto == null) {
      return null;
    }
    if (convertView == null) {
      holder = new ViewHolder();
      convertView = View.inflate(parent.getContext(), R.layout.contact_item, null);
      holder.catalog = (TextView) convertView.findViewById(R.id.catalog);
      holder.head = (ImageView) convertView.findViewById(R.id.head);
      holder.title = (TextView) convertView.findViewById(R.id.title);
      //绑定数据
      holder.catalog.setText(personDto.getSignature());
      holder.title.setText(personDto.getName());
      convertView.setTag(holder);
    }else {
      holder= (ViewHolder) convertView.getTag();
    }
    return convertView;
  }

  public void setData(List<PersonDto> data) {
    mList = data;
  }

  private class ViewHolder {

    private TextView catalog;
    private ImageView head;
    private TextView title;
  }
}
