package com.elaine.testalarm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;

import java.io.IOException;

public class AlarmReceiver extends BroadcastReceiver {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i("AlarmReceiver", "yes");
        startMediaPlayer(context);
        startVibrator(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopMediaPlayer();
                stopVibrator();
                resumeMediaPlayer();
            }
        }, 6000);
    }

    /**
     * 初始化和启动MediaPlay
     */
    void startMediaPlayer(Context context) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        //Uri uri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止MediaPlay
     */
    public void stopMediaPlayer() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    /**
     * 释放mediaPlayer
     */
    void resumeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    /**
     * 开始震动
     */
    void startVibrator(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {800, 1500, 500, 1300};
        if (vibrator != null) {
            vibrator.vibrate(pattern, 2);
        }
    }

    /**
     * 停止震动
     */
    public void stopVibrator() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

}
