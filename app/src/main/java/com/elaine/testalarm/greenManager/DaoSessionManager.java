package com.elaine.testalarm.greenManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.elaine.testalarm.greenDao.DaoMaster;
import com.elaine.testalarm.greenDao.DaoSession;

/**
 * @author llq
 * @time 2018/4/13  17:50.
 * @faction
 * @description
 * @modify
 */
public class DaoSessionManager {
    private static DaoSessionManager daoSessionManager;
    private DaoSession daoSession;

    public static DaoSessionManager getInstance() {
        if (daoSessionManager == null) {
            daoSessionManager = new DaoSessionManager();
        }
        return daoSessionManager;
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "greenDao.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
