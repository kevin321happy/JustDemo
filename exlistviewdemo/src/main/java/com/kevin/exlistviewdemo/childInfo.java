package com.kevin.exlistviewdemo;

//子条目
public class childInfo {
  private String name;
  private String pic;
  private String address;

  @Override
  public String toString() {
    return "childInfo{" +
        "name='" + name + '\'' +
        ", pic='" + pic + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

  public childInfo() {
  }

  public childInfo(String name, String pic, String address) {
    this.name = name;
    this.pic = pic;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
