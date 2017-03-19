package com.example.greendaodemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by kevin on 2017/3/16. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 *
 */
@Entity
public class UserEntity {
  @Id(autoincrement = true)
  private Long id;
  private String name;
  private int age;

  private String addredd;
  private boolean isBoy;
  public boolean getIsBoy() {
    return this.isBoy;
  }
  public void setIsBoy(boolean isBoy) {
    this.isBoy = isBoy;
  }
  public String getAddredd() {
    return this.addredd;
  }
  public void setAddredd(String addredd) {
    this.addredd = addredd;
  }
  public int getAge() {
    return this.age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Long getId() {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  @Generated(hash = 294824998)
  public UserEntity(Long id, String name, int age, String addredd, boolean isBoy) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.addredd = addredd;
    this.isBoy = isBoy;
  }
  @Generated(hash = 1433178141)
  public UserEntity() {
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", addredd='" + addredd + '\'' +
        ", isBoy=" + isBoy +
        '}';
  }
}
