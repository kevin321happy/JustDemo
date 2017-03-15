package com.example.frescodemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {

  private SimpleDraweeView mSimpleDraweeView;
  private String mUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.SimpleDraweeView);
    mUrl = "http://v1.52qmct.com/qmct.jpg";
    initView();
  }

  private void initView() {
    //代码设置属性
//    GenericDraweeHierarchy genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(
//        getResources())
//        .setPlaceholderImage(R.mipmap.ic_launcher)
//        .setFailureImage(R.mipmap.ic_launcher)
//        .setFailureImageScaleType(ScaleType.FIT_XY)
//        .build();
//    mSimpleDraweeView.setHierarchy(genericDraweeHierarchyBuilder);

    //加载本地资源
//    FrescoUtils.showRes(mSimpleDraweeView, R.drawable.a);
    //加载网络图片
    //FrescoUtils.showUrl(mSimpleDraweeView,mUrl);
    //设置图片的高斯模糊
    FrescoUtils.showUrlBlur(mSimpleDraweeView,mUrl,1,10);


  }

  //高斯模糊
  public static void showUrlBlur(SimpleDraweeView draweeView, String url, int iterations,
      int blurRadius) {
    try {
      Uri uri = Uri.parse(url);
      ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
          .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
          .build();
      AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
          .setOldController(draweeView.getController())
          .setImageRequest(request)
          .build();
      draweeView.setController(controller);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
