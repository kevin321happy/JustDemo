package com.example.greendaodemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by kevin on 2017/3/16. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司) 实体类
 */
@Entity
public class StudentEntity {

  @Id(autoincrement = true)
  private Long id;
  private String name;
  private int age;

  private boolean isBoy;

  public boolean getIsBoy() {
    return this.isBoy;
  }

  public void setIsBoy(boolean isBoy) {
    this.isBoy = isBoy;
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

  @Generated(hash = 1670193960)
  public StudentEntity(Long id, String name, int age, boolean isBoy) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.isBoy = isBoy;
  }

  @Generated(hash = 634333355)
  public StudentEntity() {
  }

  @Override
  public String toString() {
    return "StudentEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", isBoy=" + isBoy +
        '}';
  }
}
