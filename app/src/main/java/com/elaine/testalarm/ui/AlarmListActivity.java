package com.elaine.testalarm.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.elaine.testalarm.R;
import com.elaine.testalarm.adapter.AlarmAdapter;
import com.elaine.testalarm.bean.Alarm;
import com.elaine.testalarm.greenManager.AlarmGreenManager;

import java.util.ArrayList;
import java.util.List;

public class AlarmListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRvAlarm;
    private Button mBtnIn;

    private List<Alarm> alarmList;
    private AlarmAdapter alarmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        initView();
        initListener();
        initList();
        initAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        mRvAlarm = findViewById(R.id.rv_alarm);
        mBtnIn = findViewById(R.id.btn_in);
    }

    private void initListener() {
        mBtnIn.setOnClickListener(this);
    }

    private void initList() {
        alarmList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_in:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void initAdapter() {
        alarmAdapter = new AlarmAdapter(alarmList);
        mRvAlarm.setLayoutManager(new LinearLayoutManager(this));
        mRvAlarm.setAdapter(alarmAdapter);
        alarmAdapter.setDeleteItemListener(new AlarmAdapter.DeleteItemListener() {
            @Override
            public void onDeleteItemListener(final Alarm alarm) {
                new AlertDialog.Builder(AlarmListActivity.this)
                        .setTitle("删除闹钟")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlarmGreenManager.getInstance().delete(alarm);
                                for (int i = 0; i < alarmList.size(); i++) {
                                    if (alarm.getId().equals(alarmList.get(i).getId())) {
                                        alarmList.remove(i);
                                    }
                                }
                                alarmAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    private void initData() {
        alarmList.clear();
        List<Alarm> alarms = AlarmGreenManager.getInstance().loadAllAlarm();
        if (alarms != null && alarms.size() > 0) {
            alarmList.addAll(alarms);
            alarmAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
