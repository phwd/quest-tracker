package com.facebook.secure.providerinit;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.annotation.Nullable;

public abstract class DeferredInitContentProviderDelegate {
    private final DeferredInitContentProvider mParent;

    /* access modifiers changed from: protected */
    public void customOnCreate() {
    }

    public abstract int delete(Uri uri, @Nullable String str, @Nullable String[] strArr);

    @Nullable
    public abstract String getType(Uri uri);

    @Nullable
    public abstract Uri insert(Uri uri, @Nullable ContentValues contentValues);

    @Nullable
    public abstract Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2);

    public abstract int update(Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr);

    public DeferredInitContentProviderDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        this.mParent = deferredInitContentProvider;
    }

    @SuppressLint({"Return Not Nullable"})
    public Context getContext() {
        return this.mParent.getContext();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final ProviderInfo getProviderInfo() {
        return this.mParent.getProviderInfo();
    }

    @Nullable
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        return query(uri, strArr, str, strArr2, str2);
    }

    @Nullable
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return this.mParent.originalOpenFile(uri, str);
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return this.mParent.originalBulkInsert(uri, contentValuesArr);
    }

    public Bundle call(String str, @Nullable String str2, @Nullable Bundle bundle) {
        return this.mParent.originalCall(str, str2, bundle);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return this.mParent.originalApplyBatch(arrayList);
    }

    @Nullable
    public String[] getStreamTypes(Uri uri, String str) {
        return this.mParent.originalGetStreamTypes(uri, str);
    }

    @Nullable
    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        return this.mParent.originalOpenAssetFile(uri, str);
    }

    @Nullable
    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        return this.mParent.originalOpenTypedAssetFile(uri, str, bundle);
    }

    /* access modifiers changed from: protected */
    public boolean isTemporary() {
        return this.mParent.originalIsTemporary();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mParent.originalOnConfigurationChanged(configuration);
    }

    public void shutdown() {
        this.mParent.originalShutdown();
    }

    public void onTrimMemory(int i) {
        this.mParent.originalOnTrimMemory(i);
    }

    public void onLowMemory() {
        this.mParent.originalOnLowMemory();
    }
}
