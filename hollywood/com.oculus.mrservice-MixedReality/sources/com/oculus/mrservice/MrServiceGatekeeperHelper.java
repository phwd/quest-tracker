package com.oculus.mrservice;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class MrServiceGatekeeperHelper {
    public static final String COLUMN = "gk_enabled";
    private static final String TAG = "MrServiceGatekeeperHelper";

    private static Uri getContentUri(String gkName) {
        return Uri.parse("content://com.oculus.horizon.gatekeeper/fetch?name=" + gkName);
    }

    public static boolean checkGatekeeper(Context context, String gatekeeper) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(getContentUri(gatekeeper), null, null, null, null);
            boolean z = false;
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    if (cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN)) == 1) {
                        z = true;
                    }
                    cursor.close();
                    return z;
                }
            }
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
