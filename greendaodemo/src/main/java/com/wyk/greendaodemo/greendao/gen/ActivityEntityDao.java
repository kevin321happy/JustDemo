package com.wyk.greendaodemo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.greendaodemo.bean.ActivityEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACTIVITY_ENTITY".
*/
public class ActivityEntityDao extends AbstractDao<ActivityEntity, Long> {

    public static final String TABLENAME = "ACTIVITY_ENTITY";

    /**
     * Properties of entity ActivityEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Activityid = new Property(1, long.class, "activityid", false, "ACTIVITYID");
        public final static Property Address = new Property(2, String.class, "address", false, "ADDRESS");
        public final static Property Coutent = new Property(3, String.class, "coutent", false, "COUTENT");
    }


    public ActivityEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ActivityEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACTIVITY_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"ACTIVITYID\" INTEGER NOT NULL ," + // 1: activityid
                "\"ADDRESS\" TEXT," + // 2: address
                "\"COUTENT\" TEXT);"); // 3: coutent
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACTIVITY_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ActivityEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getActivityid());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(3, address);
        }
 
        String coutent = entity.getCoutent();
        if (coutent != null) {
            stmt.bindString(4, coutent);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ActivityEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getActivityid());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(3, address);
        }
 
        String coutent = entity.getCoutent();
        if (coutent != null) {
            stmt.bindString(4, coutent);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public ActivityEntity readEntity(Cursor cursor, int offset) {
        ActivityEntity entity = new ActivityEntity( //
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // activityid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // address
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // coutent
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ActivityEntity entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setActivityid(cursor.getLong(offset + 1));
        entity.setAddress(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCoutent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ActivityEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ActivityEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ActivityEntity entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
