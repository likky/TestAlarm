package com.elaine.testalarm.ui;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.elaine.testalarm.R;
import com.elaine.testalarm.bean.Alarm;
import com.elaine.testalarm.greenManager.AlarmGreenManager;
import com.elaine.testalarm.utils.AlarmClockUtil;
import com.google.gson.Gson;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button mBtnTime;
    private RadioGroup mRgRepeat;
    private RadioButton mRbOnce;
    private RadioButton mRbEveryday;
    private RadioButton mRbWeekly;
    private CheckBox mCbVoice;
    private CheckBox mCbShake;
    private Button mBtnCreate;
    private EditText mEtContent;

    private Alarm alarm;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initAlarm();
        initTime();
    }

    private void initView() {
        mBtnTime = findViewById(R.id.btn_time);
        mRgRepeat = findViewById(R.id.rg_repeat);
        mRbOnce = findViewById(R.id.rb_once);
        mRbEveryday = findViewById(R.id.rb_everyday);
        mRbWeekly = findViewById(R.id.rb_weekly);
        mCbVoice = findViewById(R.id.cb_voice);
        mCbShake = findViewById(R.id.cb_shake);
        mBtnCreate = findViewById(R.id.btn_create);
        mEtContent = findViewById(R.id.et_content);
    }

    private void initListener() {
        mBtnCreate.setOnClickListener(this);
        mRgRepeat.setOnCheckedChangeListener(this);
        mBtnTime.setOnClickListener(this);
    }

    private void initAlarm() {
        alarm = new Alarm();
    }

    private void initTime() {
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        mBtnTime.setText(hour + ":" + minute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                createAlarm();
                break;
            case R.id.btn_time:
                new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.i("onTimeSet", "hourOfDay:" + hourOfDay + "minute:" + minute);
                        mBtnTime.setText(hourOfDay + ":" + minute);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        alarm.setTime(calendar.getTimeInMillis());
                    }
                }, hour, minute, true).show();
                break;
        }
    }

    private void createAlarm() {
        String content = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
        } else {
            alarm.setContent(content);
            if (mCbVoice.isChecked()) {
                alarm.setIsVoice(true);
            } else {
                alarm.setIsVoice(false);
            }
            if (mCbShake.isChecked()) {
                alarm.setIsShake(true);
            } else {
                alarm.setIsShake(false);
            }
            alarm.setIsOpen(true);
            Log.i("alarm++++", new Gson().toJson(alarm));
            AlarmGreenManager.getInstance().insert(alarm);
            if (alarm.getTimes() == 1) {
                AlarmClockUtil.getInstance(this).setOnceAlarm(alarm.getTime());
            } else if (alarm.getTimes() == 30) {
                AlarmClockUtil.getInstance(this).setEveryDayAlarm(alarm.getTime());
            } else if (alarm.getTimes() == 4) {
                AlarmClockUtil.getInstance(this).setEveryWeekAlarm(alarm.getTime());
            }
            finish();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_once:
                alarm.setTimes(1);
                break;
            case R.id.rb_everyday:
                alarm.setTimes(30);
                break;
            case R.id.rb_weekly:
                alarm.setTimes(4);
                break;
        }
    }
}
