package com.kevin.exlistviewdemo;


public class ParentInfo {
  private String rank;
  private String count;

  @Override
  public String toString() {
    return "ParentInfo{" +
        "rank='" + rank + '\'' +
        ", count='" + count + '\'' +
        '}';
  }

  public ParentInfo(String rank) {
    this.rank = rank;
  }

  public ParentInfo() {
  }

  public ParentInfo(String rank, String count) {
    this.rank = rank;
    this.count = count;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }
}
