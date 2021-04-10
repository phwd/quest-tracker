package com.oculus.vrshell;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AssistantContentProvider extends ContentProvider {
    private static final String ASSISTANT_PANEL_STATUS = "assistant-panel-status";
    private static final int CODE_ASSISTANT_PANEL_STATUS = 1;
    private static final int PANEL_STATUS_AVAILABLE = 1;
    private static final String PREF_MODE = "mode";
    private static final String PREF_STATUS = "status";
    private final UriMatcher uriMatcher = new UriMatcher(-1);

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    private static SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences(ASSISTANT_PANEL_STATUS, 0);
    }

    public static void storePanelMode(Context context, int i) {
        getSharedPref(context).edit().putInt(PREF_MODE, i).apply();
    }

    public boolean onCreate() {
        this.uriMatcher.addURI("com.oculus.vrshell.assistant", "status", 1);
        return false;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (this.uriMatcher.match(uri) != 1) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"status", PREF_MODE});
        matrixCursor.addRow(new Integer[]{1, Integer.valueOf(getSharedPref(getContext()).getInt(PREF_MODE, 0))});
        return matrixCursor;
    }
}
