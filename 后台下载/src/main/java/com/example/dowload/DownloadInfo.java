package com.example.dowload;


public class DownloadInfo {
  private String url;
  private String id;

  @Override
  public String toString() {
    return "DownloadInfo{" +
        "url='" + url + '\'' +
        ", id='" + id + '\'' +
        '}';
  }

  public DownloadInfo(String url, String id) {
    this.url = url;
    this.id = id;
  }

  public DownloadInfo() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
