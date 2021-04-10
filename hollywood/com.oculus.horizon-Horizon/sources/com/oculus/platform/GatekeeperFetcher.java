package com.oculus.platform;

import X.AnonymousClass006;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class GatekeeperFetcher {
    public static final String COLUMN = "gk_enabled";
    public static final String CONTENT_URI = "content://com.oculus.horizon.gatekeeper/fetch";
    public static final String TAG = "GatekeeperFetcher";

    public static boolean checkGatekeeper(Context context, String str) {
        String str2 = CONTENT_URI;
        if (str != null) {
            str2 = AnonymousClass006.A07(str2, "?name=", str);
        }
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(str2), null, null, null, null);
            boolean z = false;
            if (query != null) {
                if (query.moveToNext() && query.getInt(query.getColumnIndexOrThrow("gk_enabled")) == 1) {
                    z = true;
                }
                query.close();
            }
            return z;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }
}
