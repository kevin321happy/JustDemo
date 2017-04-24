package com.example.contactquery.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 组织表(里面包含了当前组织里面的所有的部门和人员)
 * 姓名
 * 头像
 * uid
 * 部门名称
 * 岗位
 * dep_name部门的名字(外键里面有若干的小部门)
 * 改层的部门 usper_depname//当前在那个部门下面
 * dep_name
 */
@DatabaseTable(tableName = "organization")
public class Personnel {

  @DatabaseField(generatedId = true)
  private long id;//自增的ID;
  @DatabaseField
  private String name;//姓名
  @DatabaseField(unique = true)
  private String uid;//用户id唯一的字段
  @DatabaseField
  private String uicon_path;//用户头像的路径
  @DatabaseField
  private String usper_depname;//当前所属的上级的部门

  public Personnel() {
  }

  @Override
  public String toString() {
    return "Personnel{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", uid='" + uid + '\'' +
        ", uicon_path='" + uicon_path + '\'' +
        ", usper_depname='" + usper_depname + '\'' +
        '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getUicon_path() {
    return uicon_path;
  }

  public void setUicon_path(String uicon_path) {
    this.uicon_path = uicon_path;
  }

  public String getUsper_depname() {
    return usper_depname;
  }

  public void setUsper_depname(String usper_depname) {
    this.usper_depname = usper_depname;
  }
}
