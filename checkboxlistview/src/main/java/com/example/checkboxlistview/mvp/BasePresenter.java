package com.example.checkboxlistview.mvp;

/**
 * MVPPlugin
 * �? 邮箱 784787081@qq.com
 */

public interface  BasePresenter <V extends BaseView>{
    void attachView(V view);

    void detachView();
}
