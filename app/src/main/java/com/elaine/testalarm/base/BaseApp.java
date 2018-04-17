package com.elaine.testalarm.base;

import android.app.Application;

import com.elaine.testalarm.greenManager.DaoSessionManager;

/**
 * @author llq
 * @time 2018/4/13  17:04.
 * @faction
 * @description
 * @modify
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoSessionManager.getInstance().init(getApplicationContext());
    }
}
