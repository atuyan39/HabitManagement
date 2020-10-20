package com.example.habitmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.DialogCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habitmanagement.database.DatabaseHelper;
import com.example.habitmanagement.dialog.AddHabitDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView mDateView;
    TextView mAchiView;

    ListView mListView;
    HabitListAdapter mAdapter;

    private View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_button:
                    showAddPopup();
                    break;
                case R.id.delete_button:
                    // TODO : 削除画面対応
                    break;
                case R.id.detail_button:
                    // TODO : 詳細画面対応
                    Log.d("debug", "count : " + mAdapter.getCount());

                    int count = 0;
                    for (int i = 0; i < mAdapter.getCount(); i++) {
                        HabitListItem item = mAdapter.getItem(i);
                        if (item.isChecked()) {
                            count ++;
                        }
                    }
                    Log.d("debug", "isCheckedCount=" + count);
                    mAchiView.setText(getAchievement(mAdapter.getCount(), count));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateView = (TextView) findViewById(R.id.data);
        mAchiView = (TextView) findViewById(R.id.achievement);

        Button addButton = (Button) findViewById(R.id.add_button);
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        Button detailButton = (Button) findViewById(R.id.detail_button);

        String[] habits = new String[] {
                "教学", "朝祈", "反省", "瞑想", "夜祈",
                "HSIs", "両親", "感謝", "読書", "英語",
                "作務", "笑顔", "仕事", "Android", "AI",
                "資格", "技術書", "アイデア", "△", "体力",
                "立志"
        };

        ArrayList<HabitListItem> arrays = new ArrayList<>();
        for (String habit : habits) {
            HabitListItem item = new HabitListItem(habit, false);
            arrays.add(item);
        }

        mAdapter = new HabitListAdapter(this, R.layout.habit_list_item, arrays);
        mListView = (ListView) findViewById(R.id.habit_list);
        mListView.setAdapter(mAdapter);

        mDateView.setText(getDate());
        mAchiView.setText(getAchievement(habits.length, 0));

        addButton.setOnClickListener(mButtonListener);
        deleteButton.setOnClickListener(mButtonListener);
        detailButton.setOnClickListener(mButtonListener);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void showAddPopup () {
        DialogFragment dialogFragment = new AddHabitDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "add_dialog");
    }

    private String getDate() {
        String date;
        Calendar cal = Calendar.getInstance();
        date = String.valueOf(cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE);
        Log.d("debug", date);
        return date;
    }

    private String getAchievement(int sum, int checked) {
        String achievement = "達成率 : " + checked + "/" + sum;
        // 登録されているリストのチェックの数を計算して、"達成した数/習慣数"で表示する。
        return achievement;
    }

    private void updateView() {
    }
}