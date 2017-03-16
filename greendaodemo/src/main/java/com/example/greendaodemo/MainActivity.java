package com.example.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.greendaodemo.bean.StudentEntity;
import com.example.greendaodemo.bean.UserEntity;
import com.wyk.greendaodemo.greendao.gen.DaoSession;
import com.wyk.greendaodemo.greendao.gen.StudentEntityDao;
import com.wyk.greendaodemo.greendao.gen.UserEntityDao;
import com.wyk.greendaodemo.greendao.gen.UserEntityDao.Properties;
import java.util.List;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private Button mAdd;
  private Button mDel;
  private Button mUpdata;
  private Button mQue;
  private StudentEntityDao mStudentEntityDao;
  private UserEntityDao mUserEntityDao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    mAdd = (Button) findViewById(R.id.add);
    mDel = (Button) findViewById(R.id.del);
    mUpdata = (Button) findViewById(R.id.updata);
    mQue = (Button) findViewById(R.id.que);

    mAdd.setOnClickListener(this);
    mDel.setOnClickListener(this);
    mUpdata.setOnClickListener(this);
    mQue.setOnClickListener(this);

    GreenDaoUtils daoUtils = GreenDaoUtils.getInstance();
    DaoSession daoSession = daoUtils.getmDaoSession();

    mStudentEntityDao = daoSession.getStudentEntityDao();
    mUserEntityDao = daoSession.getUserEntityDao();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.add:
        //增加
        for (int i = 0; i < 5; i++) {
          StudentEntity studentEntity = new StudentEntity();
          studentEntity.setAge(24 + i);
          studentEntity.setIsBoy(i % 2 == 0 ? true : false);
          studentEntity.setName(i % 2 == 0 ? "老藤" : "吴卫");
          UserEntity userEntity = new UserEntity();
          userEntity.setName(i % 2 == 0 ? "老藤" : "吴卫");
          userEntity.setIsBoy(i % 2 == 0 ? true : false);
          userEntity.setAge(24 - i);
          userEntity.setAddredd(i % 2 == 0 ? "武汉市融科智谷" : "江汉区火凤凰");
          mStudentEntityDao.insert(studentEntity);
          Log.i("test", "添加学生 ：" + (i + 1) + studentEntity.toString());
          mUserEntityDao.insert(userEntity);
          Log.i("test", "添加用户 ：" + (i + 1) + userEntity.toString());
        }
        break;
      case R.id.del:
        //根据ID来删除数据
        mStudentEntityDao.deleteByKey(Long.parseLong("10"));
        Log.i("test", "删除用户 ：" + "老藤");
        //删除表里面的对象
        Query<UserEntity> query = mUserEntityDao.queryBuilder()
            .where(Properties.Name.eq("吴卫"), Properties.IsBoy.eq(true))
            .build();
        Log.i("test", "查询结果长度 ：" + query.list().size());
        List<UserEntity> userEntities = query.list();
        for (UserEntity userEntity : userEntities) {
          Log.i("test", "查询到的用户对象 ：" + userEntity.toString());
        }
        mUserEntityDao.deleteInTx(userEntities);
        break;
      case R.id.updata:
        List<StudentEntity> entities = mStudentEntityDao.loadAll();
        for (int i = 0; i < entities.size(); i++) {
          if (i < 10) {
            StudentEntity studentEntity = entities.get(i);
            studentEntity.setName("小肥羊");
            mStudentEntityDao.update(studentEntity);
          }
        }
        break;
      case R.id.que:
        List<StudentEntity> studentEntities = mStudentEntityDao.loadAll();
        Log.i("test",
            "搂出来ID为30的学生： " + mStudentEntityDao.loadByRowId(Long.parseLong("30")).toString());
        List<UserEntity> userEntities1 = mUserEntityDao.loadAll();
        QueryBuilder<UserEntity> queryBuilder = mUserEntityDao.queryBuilder()
            .where(Properties.Name.eq("老藤"), Properties.Addredd.eq("武汉市融科智谷"));
        for (UserEntity userEntity : queryBuilder.list()) {
          Log.i("test", "low出来的老藤 :" + userEntity);
        }
        QueryBuilder<StudentEntity> querySutdent = mStudentEntityDao.queryBuilder()
            .where(StudentEntityDao.Properties.Name.eq("吴卫"),
                StudentEntityDao.Properties.IsBoy.eq(false));
        for (StudentEntity studentEntity : querySutdent.list()) {
          Log.i("test", "low出来的吴卫 ：" + studentEntity);
        }
        mStudentEntityDao.deleteInTx(querySutdent.list());
        break;
    }
  }
}
