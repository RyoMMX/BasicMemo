package com.example.recoded.memo.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.recoded.memo.Memo;
import com.example.recoded.memo.R;

import java.util.List;

public class MemoAdapter extends ArrayAdapter<Memo> {

    public MemoAdapter(@NonNull Context context, List<Memo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable V iew convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_list_item, parent, false);
        }

        TextView titleTv = convertView.findViewById(R.id.title_tv);
        TextView descriptionTV = convertView.findViewById(R.id.description_tv);

        Memo memo = getItem(position);
        if (memo != null) {
            if (memo.getTitle() != null) {
                titleTv.setText(memo.getTitle());
            }

            if (memo.getDescription() != null) {
                descriptionTV.setText(memo.getDescription());
            }
        }
        return convertView;
    }
}
