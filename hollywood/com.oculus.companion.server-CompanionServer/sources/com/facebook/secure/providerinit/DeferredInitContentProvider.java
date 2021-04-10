package com.facebook.secure.providerinit;

import android.annotation.TargetApi;
import android.content.ContentProvider;
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

public abstract class DeferredInitContentProvider extends ContentProvider {
    private DeferredInitContentProviderDelegate mDelegate;
    private volatile ProviderInfo mProviderInfo;

    /* access modifiers changed from: protected */
    public abstract DeferredInitContentProviderDelegate getDelegateInstance();

    /* access modifiers changed from: protected */
    public boolean runCustomOnCreate() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void validateProviderInfo(Context context, ProviderInfo providerInfo) {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized DeferredInitContentProviderDelegate getDelegate() {
        if (this.mDelegate != null) {
            return this.mDelegate;
        }
        ProviderInitStatus.waitUntilSafeForInit();
        this.mDelegate = getDelegateInstance();
        return this.mDelegate;
    }

    public final boolean onCreate() {
        if (!runCustomOnCreate()) {
            return true;
        }
        new Thread(new Runnable() {
            /* class com.facebook.secure.providerinit.DeferredInitContentProvider.AnonymousClass1 */

            public void run() {
                DeferredInitContentProvider.this.getDelegate().customOnCreate();
            }
        }).start();
        return true;
    }

    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        this.mProviderInfo = providerInfo;
        super.attachInfo(context, providerInfo);
        validateProviderInfo(context, providerInfo);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return getDelegate().query(uri, strArr, str, strArr2, str2);
    }

    @TargetApi(16)
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        return getDelegate().query(uri, strArr, str, strArr2, str2, cancellationSignal);
    }

    public final String getType(Uri uri) {
        return getDelegate().getType(uri);
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        return getDelegate().insert(uri, contentValues);
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        return getDelegate().delete(uri, str, strArr);
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return getDelegate().update(uri, contentValues, str, strArr);
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().openFile(uri, str);
    }

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return getDelegate().bulkInsert(uri, contentValuesArr);
    }

    public final Bundle call(String str, String str2, Bundle bundle) {
        return getDelegate().call(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return getDelegate().applyBatch(arrayList);
    }

    public final String[] getStreamTypes(Uri uri, String str) {
        return getDelegate().getStreamTypes(uri, str);
    }

    @Override // android.content.ContentProvider
    public final AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().openAssetFile(uri, str);
    }

    @Override // android.content.ContentProvider
    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        return getDelegate().openTypedAssetFile(uri, str, bundle);
    }

    /* access modifiers changed from: protected */
    public final boolean isTemporary() {
        return getDelegate().isTemporary();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        getDelegate().onConfigurationChanged(configuration);
    }

    public final void shutdown() {
        getDelegate().shutdown();
    }

    public final void onTrimMemory(int i) {
        if (ProviderInitStatus.isSafeForInit()) {
            getDelegate().onTrimMemory(i);
        }
    }

    public final void onLowMemory() {
        if (ProviderInitStatus.isSafeForInit()) {
            getDelegate().onLowMemory();
        }
    }
}
