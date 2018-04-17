package com.elaine.testalarm.utils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.elaine.testalarm.receiver.AlarmReceiver;

import static android.content.Context.ALARM_SERVICE;

/**
 * @author llq
 * @time 2018/4/16  11:22.
 * @faction
 * @description
 * @modify
 */
public class AlarmClockUtil {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @SuppressLint("StaticFieldLeak")
    private static AlarmClockUtil alarmClockUtil;

    private AlarmClockUtil(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("Alarm_clock");
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public static AlarmClockUtil getInstance(Context context) {
        if (alarmClockUtil == null) {
            alarmClockUtil = new AlarmClockUtil(context);
        }
        return alarmClockUtil;
    }

    public void setOnceAlarm(long time) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public void setEveryDayAlarm(long time) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void setEveryWeekAlarm(long time) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 7 * AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelAlarm() {
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

}
