package com.elaine.testalarm.greenManager;

import com.elaine.testalarm.bean.Alarm;
import com.elaine.testalarm.greenDao.AlarmDao;

import java.util.List;

/**
 * @author llq
 * @time 2018/4/13  16:05.
 * @faction
 * @description
 * @modify
 */
public class AlarmGreenManager {
    private static AlarmGreenManager instance;
    private AlarmDao alarmDao;

    private AlarmGreenManager() {
        if (instance == null) {
            alarmDao = DaoSessionManager.getInstance().getDaoSession().getAlarmDao();
        }
    }

    public static AlarmGreenManager getInstance() {
        if (instance == null) {
            instance = new AlarmGreenManager();
        }
        return instance;
    }

    public void insert(Alarm alarm) {
        if (alarm != null) {
            alarmDao.insert(alarm);
        }
    }

    public void delete(Alarm alarm) {
        if (alarm != null) {
            alarmDao.delete(alarm);
        }
    }

    public List<Alarm> loadAllAlarm() {
        return alarmDao.loadAll();
    }


}
