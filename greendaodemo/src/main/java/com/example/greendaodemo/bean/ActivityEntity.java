package com.example.greendaodemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by kevin on 2017/3/16. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 *
 */
@Entity
public class ActivityEntity {
  @Id(autoincrement = true)
  private long id;
  private long activityid;
  private String address;
  private String coutent;
  public String getCoutent() {
    return this.coutent;
  }
  public void setCoutent(String coutent) {
    this.coutent = coutent;
  }
  public String getAddress() {
    return this.address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public long getActivityid() {
    return this.activityid;
  }
  public void setActivityid(long activityid) {
    this.activityid = activityid;
  }
  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }
  @Generated(hash = 1938936190)
  public ActivityEntity(long id, long activityid, String address, String coutent) {
    this.id = id;
    this.activityid = activityid;
    this.address = address;
    this.coutent = coutent;
  }
  @Generated(hash = 184590599)
  public ActivityEntity() {
  }

}
