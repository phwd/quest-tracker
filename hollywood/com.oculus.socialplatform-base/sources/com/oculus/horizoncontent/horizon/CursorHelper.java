package com.oculus.horizoncontent.horizon;

import android.database.Cursor;

public class CursorHelper {
    public static boolean getBoolFromIntIndex(Cursor cursor, int i) {
        return i >= 0 && cursor.getInt(i) != 0;
    }

    public static String getStringFromIndex(Cursor cursor, int i, String str) {
        if (i < 0) {
            return str;
        }
        return cursor.getString(i);
    }
}
