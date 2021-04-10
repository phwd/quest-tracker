package com.oculus.horizoncontent.utils;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.lang.ref.WeakReference;

public class AsyncQueryHandlerBase<CallbackType> extends AsyncQueryHandler {
    public static final String TAG = LoggingUtil.tag(AsyncQueryHandlerBase.class);
    public static final int TOKEN = 1;
    public final WeakReference<CallbackType> mCallback;

    public void destroy() {
        super.cancelOperation(1);
        this.mCallback.clear();
    }

    public void onQueryComplete(CallbackType callbacktype, Cursor cursor) {
    }

    public void onUpdateComplete(CallbackType callbacktype, Object obj, int i) {
    }

    public final void startUpdate(Object obj, Uri uri, ContentValues contentValues, String str, String[] strArr) {
        super.startUpdate(1, obj, uri, contentValues, str, strArr);
    }

    public AsyncQueryHandlerBase(ContentResolver contentResolver, CallbackType callbacktype) {
        super(contentResolver);
        this.mCallback = new WeakReference<>(callbacktype);
    }

    public final void onQueryComplete(int i, Object obj, Cursor cursor) {
        CallbackType callbacktype = this.mCallback.get();
        if (callbacktype != null) {
            onQueryComplete(callbacktype, cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public final void onUpdateComplete(int i, Object obj, int i2) {
        CallbackType callbacktype = this.mCallback.get();
        if (callbacktype != null) {
            onUpdateComplete(callbacktype, obj, i2);
        }
    }

    public final void startQuery(int i, Object obj, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Log.w(TAG, "Overriding token value, call startQuery without token instead.");
        startQuery(obj, uri, strArr, str, strArr2, str2);
    }

    public void startQuery(Object obj, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        super.startQuery(1, obj, uri, strArr, str, strArr2, str2);
    }
}
