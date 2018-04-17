package com.elaine.testalarm.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.elaine.testalarm.bean.Alarm;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ALARM".
*/
public class AlarmDao extends AbstractDao<Alarm, Long> {

    public static final String TABLENAME = "ALARM";

    /**
     * Properties of entity Alarm.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Time = new Property(2, long.class, "time", false, "TIME");
        public final static Property Times = new Property(3, int.class, "times", false, "TIMES");
        public final static Property IsOpen = new Property(4, boolean.class, "isOpen", false, "IS_OPEN");
        public final static Property IsVoice = new Property(5, boolean.class, "isVoice", false, "IS_VOICE");
        public final static Property IsShake = new Property(6, boolean.class, "isShake", false, "IS_SHAKE");
        public final static Property AlarmId = new Property(7, int.class, "alarmId", false, "ALARM_ID");
    }


    public AlarmDao(DaoConfig config) {
        super(config);
    }
    
    public AlarmDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ALARM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"CONTENT\" TEXT NOT NULL ," + // 1: content
                "\"TIME\" INTEGER NOT NULL ," + // 2: time
                "\"TIMES\" INTEGER NOT NULL ," + // 3: times
                "\"IS_OPEN\" INTEGER NOT NULL ," + // 4: isOpen
                "\"IS_VOICE\" INTEGER NOT NULL ," + // 5: isVoice
                "\"IS_SHAKE\" INTEGER NOT NULL ," + // 6: isShake
                "\"ALARM_ID\" INTEGER NOT NULL );"); // 7: alarmId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ALARM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Alarm entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getContent());
        stmt.bindLong(3, entity.getTime());
        stmt.bindLong(4, entity.getTimes());
        stmt.bindLong(5, entity.getIsOpen() ? 1L: 0L);
        stmt.bindLong(6, entity.getIsVoice() ? 1L: 0L);
        stmt.bindLong(7, entity.getIsShake() ? 1L: 0L);
        stmt.bindLong(8, entity.getAlarmId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Alarm entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getContent());
        stmt.bindLong(3, entity.getTime());
        stmt.bindLong(4, entity.getTimes());
        stmt.bindLong(5, entity.getIsOpen() ? 1L: 0L);
        stmt.bindLong(6, entity.getIsVoice() ? 1L: 0L);
        stmt.bindLong(7, entity.getIsShake() ? 1L: 0L);
        stmt.bindLong(8, entity.getAlarmId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Alarm readEntity(Cursor cursor, int offset) {
        Alarm entity = new Alarm( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // content
            cursor.getLong(offset + 2), // time
            cursor.getInt(offset + 3), // times
            cursor.getShort(offset + 4) != 0, // isOpen
            cursor.getShort(offset + 5) != 0, // isVoice
            cursor.getShort(offset + 6) != 0, // isShake
            cursor.getInt(offset + 7) // alarmId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Alarm entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent(cursor.getString(offset + 1));
        entity.setTime(cursor.getLong(offset + 2));
        entity.setTimes(cursor.getInt(offset + 3));
        entity.setIsOpen(cursor.getShort(offset + 4) != 0);
        entity.setIsVoice(cursor.getShort(offset + 5) != 0);
        entity.setIsShake(cursor.getShort(offset + 6) != 0);
        entity.setAlarmId(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Alarm entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Alarm entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Alarm entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
