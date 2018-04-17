package com.elaine.testalarm.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author llq
 * @time 2018/4/12  16:19.
 * @faction
 * @description
 * @modify
 */

@Entity
public class Alarm {

    @Unique
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String content;
    @NotNull
    private long time;
    @NotNull
    private int times;
    @NotNull
    private boolean isOpen;
    @NotNull
    private boolean isVoice;
    @NotNull
    private boolean isShake;
    @Generated(hash = 1671285535)
    public Alarm(Long id, @NotNull String content, long time, int times,
            boolean isOpen, boolean isVoice, boolean isShake) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.times = times;
        this.isOpen = isOpen;
        this.isVoice = isVoice;
        this.isShake = isShake;
    }
    @Generated(hash = 1972324134)
    public Alarm() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getTimes() {
        return this.times;
    }
    public void setTimes(int times) {
        this.times = times;
    }
    public boolean getIsOpen() {
        return this.isOpen;
    }
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public boolean getIsVoice() {
        return this.isVoice;
    }
    public void setIsVoice(boolean isVoice) {
        this.isVoice = isVoice;
    }
    public boolean getIsShake() {
        return this.isShake;
    }
    public void setIsShake(boolean isShake) {
        this.isShake = isShake;
    }




}
