package com.example.contactquery.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 用来存放部门的表的部门表
 * depment_name//部门的名字
 * super_demped//上级部门
 * person_count//部门下面的人员总数
 */
//@DatabaseField(canBeNull = true, foreign = true, columnName = "user_id")
//private User user;
@DatabaseTable(tableName = "depement")
public class Department {
  @DatabaseField(generatedId = true)
  private long id;//自增ID
  @DatabaseField
  private String dep_name;  //部门的名字
  @DatabaseField(canBeNull=true,foreign = true,columnName = "super_dep")//外键引入父部门
  private Personnel personnel;
  @DatabaseField()
  private int person_count;//部门的人数

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDep_name() {
    return dep_name;
  }

  public void setDep_name(String dep_name) {
    this.dep_name = dep_name;
  }

  public Personnel getPersonnel() {
    return personnel;
  }

  public void setPersonnel(Personnel personnel) {
    this.personnel = personnel;
  }

  public int getPerson_count() {
    return person_count;
  }

  public void setPerson_count(int person_count) {
    this.person_count = person_count;
  }

  public Department() {
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", dep_name='" + dep_name + '\'' +
        ", personnel=" + personnel +
        ", person_count=" + person_count +
        '}';
  }
}
