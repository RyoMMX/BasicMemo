package com.example.recoded.memo.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

public class Contract {
    public static final String AUTHORITY = "com.example.recoded.memo.provider";

    public static class Memo implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String ID = _ID;
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static Uri URI = new Uri.Builder()
                .authority(AUTHORITY)
                .scheme("content")
                .appendPath(TABLE_NAME)
                .build();
    }
}
