package com.elaine.testalarm.adapter;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.elaine.testalarm.R;
import com.elaine.testalarm.bean.Alarm;
import com.elaine.testalarm.utils.TimeStampUtil;

import java.util.List;

/**
 * @author llq
 * @time 2018/4/12  16:16.
 * @faction
 * @description
 * @modify
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> alarmList;
    private DeleteItemListener deleteItemListener;

    public AlarmAdapter(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(view);
    }

    public void setDeleteItemListener(DeleteItemListener listener) {
        this.deleteItemListener = listener;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTvContent.setText(alarmList.get(position).getContent());
        long time = alarmList.get(position).getTime();
        holder.mTvTime.setText(TimeStampUtil.getTime(time));
        if (alarmList.get(position).getTimes() == 1) {
            holder.mTvTimes.setText("一次");
        } else if (alarmList.get(position).getTimes() == 30) {
            holder.mTvTimes.setText("每天");
        } else if (alarmList.get(position).getTimes() == 4) {
            holder.mTvTimes.setText("每周");
        }
        holder.mTbIsOpen.setChecked(alarmList.get(position).getIsOpen());
        holder.mClItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemListener.onDeleteItemListener(alarmList.get(position));
            }
        });
        holder.mTbIsOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               /* if (isChecked) {
                    Log.i("isChecked", isChecked + "");
                } else {
                    Log.i("isChecked", isChecked + "");
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvContent;
        private TextView mTvTime;
        private TextView mTvTimes;
        private ToggleButton mTbIsOpen;
        private ConstraintLayout mClItem;

        AlarmViewHolder(View itemView) {
            super(itemView);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mTvTime = itemView.findViewById(R.id.tv_time);
            mTvTimes = itemView.findViewById(R.id.tv_times);
            mTbIsOpen = itemView.findViewById(R.id.tb_is_open);
            mClItem = itemView.findViewById(R.id.cl_item);
        }
    }

    public interface DeleteItemListener {
        void onDeleteItemListener(Alarm alarm);
    }
}
