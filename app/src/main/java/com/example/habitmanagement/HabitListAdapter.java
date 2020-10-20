package com.example.habitmanagement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HabitListAdapter extends ArrayAdapter<HabitListItem> {

    private int mResource;
    private List<HabitListItem> mItems;
    private LayoutInflater mInflater;

    public HabitListAdapter(Context context, int resource, List<HabitListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        final HabitListItem item = mItems.get(position);

        TextView habit = (TextView) view.findViewById(R.id.habit_text);
        habit.setText(item.getHabit());

        CheckBox isChecked = (CheckBox) view.findViewById(R.id.checked_habit);
        isChecked.setChecked(item.isChecked());
        isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
                Log.d("debug", "habit : " + item.getHabit() + ", isChecked : " + item.isChecked());
            }
        });

        return view;
    }
}
