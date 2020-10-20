package com.example.habitmanagement;

public class HabitListItem {
    private String mHabit;
    private boolean mChecked;

    public HabitListItem(String habit, boolean checked) {
        mHabit = habit;
        mChecked = checked;
    }

    public String getHabit() {
        return mHabit;
    }

    public void setHabit(String habit) {
        mHabit = habit;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }
}
