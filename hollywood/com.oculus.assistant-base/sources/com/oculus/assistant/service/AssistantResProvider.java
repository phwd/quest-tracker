package com.oculus.assistant.service;

import X.BX;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.assistant.R;

public class AssistantResProvider extends ContentProvider {
    public static final UriMatcher A00;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        A00 = uriMatcher;
        uriMatcher.addURI("com.oculus.assistant.AssistantResProvider", "default-icon", 1);
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public final String getType(Uri uri) {
        return null;
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public final boolean onCreate() {
        return false;
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        char c;
        MatrixCursor matrixCursor;
        Object[] objArr;
        Integer valueOf;
        if (A00.match(uri) == 1) {
            c = 0;
            matrixCursor = new MatrixCursor(new String[]{"default-icon"});
            objArr = new Object[1];
            valueOf = Integer.valueOf((int) R.drawable.ic_attn_default);
        } else if (uri == null || TextUtils.isEmpty(uri.getPath())) {
            return null;
        } else {
            String substring = uri.getPath().substring(1);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            Application A002 = BX.A00();
            int identifier = A002.getResources().getIdentifier(substring, "drawable", A002.getPackageName());
            c = 0;
            matrixCursor = new MatrixCursor(new String[]{uri.getPath()});
            objArr = new Object[1];
            valueOf = Integer.valueOf(identifier);
        }
        objArr[c] = valueOf;
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }
}
