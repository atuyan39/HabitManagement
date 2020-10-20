package com.example.habitmanagement.database;

import androidx.annotation.NonNull;

public class Habit {
    private int mId;
    private String mHabit;

    public Habit(int id, @NonNull String habit) {
        mId = id;
        mHabit = habit;
    }

    public int getId() {
        return mId;
    }

    public String getHabit() {
        return mHabit;
    }
}
