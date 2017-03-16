package com.wyk.greendaodemo.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.greendaodemo.bean.StudentEntity;
import com.example.greendaodemo.bean.UserEntity;

import com.wyk.greendaodemo.greendao.gen.StudentEntityDao;
import com.wyk.greendaodemo.greendao.gen.UserEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentEntityDaoConfig;
    private final DaoConfig userEntityDaoConfig;

    private final StudentEntityDao studentEntityDao;
    private final UserEntityDao userEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentEntityDaoConfig = daoConfigMap.get(StudentEntityDao.class).clone();
        studentEntityDaoConfig.initIdentityScope(type);

        userEntityDaoConfig = daoConfigMap.get(UserEntityDao.class).clone();
        userEntityDaoConfig.initIdentityScope(type);

        studentEntityDao = new StudentEntityDao(studentEntityDaoConfig, this);
        userEntityDao = new UserEntityDao(userEntityDaoConfig, this);

        registerDao(StudentEntity.class, studentEntityDao);
        registerDao(UserEntity.class, userEntityDao);
    }
    
    public void clear() {
        studentEntityDaoConfig.clearIdentityScope();
        userEntityDaoConfig.clearIdentityScope();
    }

    public StudentEntityDao getStudentEntityDao() {
        return studentEntityDao;
    }

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

}
