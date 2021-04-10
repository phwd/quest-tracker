package com.facebook.secure.providerinit;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class DeferredInitContentProviderDelegate {
    public abstract ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException;

    public abstract int bulkInsert(Uri uri, ContentValues[] contentValuesArr);

    public abstract Bundle call(String str, String str2, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void customOnCreate();

    public abstract int delete(Uri uri, String str, String[] strArr);

    public abstract String[] getStreamTypes(Uri uri, String str);

    public abstract String getType(Uri uri);

    public abstract Uri insert(Uri uri, ContentValues contentValues);

    /* access modifiers changed from: protected */
    public abstract boolean isTemporary();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onLowMemory();

    public abstract void onTrimMemory(int i);

    public abstract AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException;

    public abstract ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException;

    public abstract AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException;

    public abstract Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2);

    public abstract Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

    public abstract void shutdown();

    public abstract int update(Uri uri, ContentValues contentValues, String str, String[] strArr);
}
