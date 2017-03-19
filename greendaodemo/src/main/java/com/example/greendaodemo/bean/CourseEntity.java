package com.example.greendaodemo.bean;

import com.wyk.greendaodemo.greendao.gen.ActivityEntityDao;
import com.wyk.greendaodemo.greendao.gen.CourseEntityDao;
import com.wyk.greendaodemo.greendao.gen.DaoSession;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by kevin on 2017/3/16. 邮箱：kevin321vip@126.com 公司：锦绣氘(武汉科技有限公司)
 */
@Entity
public class CourseEntity {

  @Id(autoincrement = true)
  private long id;
  @Unique
  private String courseid;
  private String name;
  //外键
  private long activityid;
  @ToOne(joinProperty = "activityid")
  ActivityEntity mActivityEntity;
  private String time;
  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 1942392019)
  public void refresh() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
  }
  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 713229351)
  public void update() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
  }
  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 128553479)
  public void delete() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
  }
  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 657379802)
  public void setMActivityEntity(@NotNull ActivityEntity mActivityEntity) {
    if (mActivityEntity == null) {
      throw new DaoException(
          "To-one property 'activityid' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
      this.mActivityEntity = mActivityEntity;
      activityid = mActivityEntity.getId();
      mActivityEntity__resolvedKey = activityid;
    }
  }
  /** To-one relationship, resolved on first access. */
  @Generated(hash = 654498682)
  public ActivityEntity getMActivityEntity() {
    long __key = this.activityid;
    if (mActivityEntity__resolvedKey == null
        || !mActivityEntity__resolvedKey.equals(__key)) {
      final DaoSession daoSession = this.daoSession;
      if (daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      ActivityEntityDao targetDao = daoSession.getActivityEntityDao();
      ActivityEntity mActivityEntityNew = targetDao.load(__key);
      synchronized (this) {
        mActivityEntity = mActivityEntityNew;
        mActivityEntity__resolvedKey = __key;
      }
    }
    return mActivityEntity;
  }
  @Generated(hash = 466371606)
  private transient Long mActivityEntity__resolvedKey;
  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 1713382802)
  public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCourseEntityDao() : null;
  }
  /** Used for active entity operations. */
  @Generated(hash = 2133429133)
  private transient CourseEntityDao myDao;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  public String getTime() {
    return this.time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public long getActivityid() {
    return this.activityid;
  }
  public void setActivityid(long activityid) {
    this.activityid = activityid;
  }
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCourseid() {
    return this.courseid;
  }
  public void setCourseid(String courseid) {
    this.courseid = courseid;
  }
  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }
  @Generated(hash = 2032834240)
  public CourseEntity(long id, String courseid, String name, long activityid,
      String time) {
    this.id = id;
    this.courseid = courseid;
    this.name = name;
    this.activityid = activityid;
    this.time = time;
  }
  @Generated(hash = 483818505)
  public CourseEntity() {
  }
}
