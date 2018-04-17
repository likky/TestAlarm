package com.elaine.testalarm.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author llq
 * @time 2018/4/14  15:35.
 * @faction
 * @description
 * @modify
 */
public class TimeStampUtil {
    /**
     * 显示如：17:40
     *
     * @param timestamp 时间(毫秒)
     * @return 字符串
     */
    public static String getTime(long timestamp) {
        String result;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat yearFormat = new SimpleDateFormat(" HH:mm");
        result = yearFormat.format(new Date(timestamp));
        return result;
    }
}
