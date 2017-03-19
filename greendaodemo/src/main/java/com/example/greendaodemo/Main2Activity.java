package com.example.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.greendaodemo.bean.ActivityEntity;
import com.example.greendaodemo.bean.CourseEntity;
import com.wyk.greendaodemo.greendao.gen.ActivityEntityDao;
import com.wyk.greendaodemo.greendao.gen.CourseEntityDao;
import com.wyk.greendaodemo.greendao.gen.DaoSession;
import com.wyk.greendaodemo.greendao.gen.StudentEntityDao;
import com.wyk.greendaodemo.greendao.gen.UserEntityDao;
import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

//联查
public class Main2Activity extends AppCompatActivity implements OnClickListener {

  private Button mAdd;
  private Button mDel;
  private Button mUpdata;
  private Button mQue;
  private StudentEntityDao mStudentEntityDao;
  private UserEntityDao mUserEntityDao;
  private CourseEntityDao mCourseEntityDao;
  private ActivityEntityDao mActivityEntityDao;
  private int index;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
    initView();
  }

  private void initView() {
    mAdd = (Button) findViewById(R.id.addt);
    mDel = (Button) findViewById(R.id.delt);
    mUpdata = (Button) findViewById(R.id.updatat);
    mQue = (Button) findViewById(R.id.quet);

    mAdd.setOnClickListener(this);
    mDel.setOnClickListener(this);
    mUpdata.setOnClickListener(this);
    mQue.setOnClickListener(this);

    GreenDaoUtils daoUtils = GreenDaoUtils.getInstance();
    DaoSession daoSession = daoUtils.getmDaoSession();
    Log.i("test", "1");

//    mStudentEntityDao = daoSession.getStudentEntityDao();
//    mUserEntityDao = daoSession.getUserEntityDao();
    mCourseEntityDao = daoSession.getCourseEntityDao();
    mActivityEntityDao = daoSession.getActivityEntityDao();
    Schema schema=new Schema(1,"com.example.greendaodemo");//模式
    Entity courseentity = schema.addEntity("man");
    courseentity.setTableName("zhe");//重新定义表名
    //定义一个主键
    courseentity.addIdProperty().primaryKey();
    // 定义一个非空的列，列名为 NAME
    courseentity.addStringProperty("name").notNull();
    // 可以使用此方法定义实体类的属性名和数据库的列名不同，如下实体类名为 sex，列名为_SEX
    courseentity.addStringProperty("sex").columnName("_sex");
    try {
      new DaoGenerator().generateAll(schema, "../Study/greendaodemo/src/main/java-gen");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onClick(View v) {
    Log.i("test", "2");
    switch (v.getId()) {
      case R.id.addt:
        Log.i("test", "点击了添加 ：");
//        for (int i = 0; i < 10; i++) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("测试课程啊。。。" +index);
        courseEntity.setActivityid(index);
        courseEntity.setTime("2017年3月16日");
        mCourseEntityDao.insert(courseEntity);
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityid(index);
        activityEntity.setAddress("武汉市融科智谷");
        activityEntity.setCoutent("裘马草堂产品发布会啊");
        mActivityEntityDao.insert(activityEntity);
//        }
        index++;
        break;
      case R.id.delt:

        break;
      case R.id.updatat:

        break;
      case R.id.quet:

        break;
    }
  }
}
