package com.oculus.vrapi;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class PublicLibraryUtil {
    private static final String TAG = "PublicLibraryUtil";

    static String getAppDataFromPublicLibrary(Context context, String packageName, String attributeColumnName, String defaultValue) {
        if (context == null) {
            Log.e(TAG, "getAppDataFromPublicLibrary with null context");
            return defaultValue;
        }
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor2 = contentResolver.query(Uri.parse("content://com.oculus.ocms.publiclibrary/apps/" + packageName), null, null, null, null);
            if (cursor2 != null) {
                if (cursor2.moveToNext()) {
                    String string = cursor2.getString(cursor2.getColumnIndexOrThrow(attributeColumnName));
                    cursor2.close();
                    return string;
                }
            }
            Log.i(TAG, "getAppDataFromPublicLibrary no result for " + packageName);
            if (cursor2 != null) {
                cursor2.close();
            }
            return defaultValue;
        } catch (Exception ex) {
            Log.e(TAG, "getAppDataFromPublicLibrary " + ex + " for " + packageName + " : " + attributeColumnName);
            if (0 != 0) {
                cursor.close();
            }
            return defaultValue;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }
}
