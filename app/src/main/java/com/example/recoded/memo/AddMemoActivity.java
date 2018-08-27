package com.example.recoded.memo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recoded.memo.data.Contract;
import com.example.recoded.memo.data.DBHelper;
import com.example.recoded.memo.databinding.ActivityAddMemoBinding;

import static com.example.recoded.memo.data.Contract.Memo.DESCRIPTION;
import static com.example.recoded.memo.data.Contract.Memo.TITLE;

public class AddMemoActivity extends AppCompatActivity {
    private ActivityAddMemoBinding binding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_memo);
        dbHelper = new DBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_memo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        addToDB();
        return false;
    }

    private void addToDB() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String title = binding.titleEt.getText().toString();
        String subtitle = binding.descriptionEt.getText().toString();

        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(DESCRIPTION, subtitle);
        Long newId = db.insert(Contract.Memo.TABLE_NAME, null, values);

        if (newId > 0) {
            Snackbar.make(binding.getRoot(), "dataSaved", Snackbar.LENGTH_INDEFINITE)
                    .setAction("finish", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    })
                    .setAction("add new one", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            binding.titleEt.setText("");
                            binding.descriptionEt.setText("");
                        }
                    }).show();
        } else {
            Toast.makeText(this, "some thing going wrong tray again !", Toast.LENGTH_SHORT).show();
        }
    }
}
