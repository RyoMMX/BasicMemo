package com.example.recoded.memo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.recoded.memo.data.DBHelper;
import com.example.recoded.memo.data.MemoAdapter;
import com.example.recoded.memo.databinding.ActivityMainBinding;

import java.util.ArrayList;

import static com.example.recoded.memo.data.Contract.Memo.DESCRIPTION;
import static com.example.recoded.memo.data.Contract.Memo.TABLE_NAME;
import static com.example.recoded.memo.data.Contract.Memo.TITLE;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MemoAdapter memoAdapter;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        helper = new DBHelper(this);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        loadData();

        super.onStart();
    }

    private void loadData() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                TITLE,
                DESCRIPTION
        };

        Cursor cursor = db
                .query(TABLE_NAME, projection, null, null, null, null, null);

        if (cursor != null) {
            ArrayList<Memo> memos = new ArrayList<>();
            while (cursor.moveToNext()) {
                memos.add(new Memo(cursor.getString(0), cursor.getString(1)));
            }
            cursor.close();
            binding.contentMain.memoLv.setAdapter(new MemoAdapter(this, memos));
        }
    }
}