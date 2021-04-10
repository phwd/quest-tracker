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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.annotation.Nullable;

public abstract class DeferredInitContentProvider extends ContentProvider {
    private static final String TAG = "DeferredInitContentProvider";
    private DeferredInitContentProviderDelegate mDelegate;
    private volatile ProviderInfo mProviderInfo;

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

    /* access modifiers changed from: protected */
    public DeferredInitContentProviderDelegate getDelegateInstance() {
        try {
            return (DeferredInitContentProviderDelegate) Class.forName(getDelegateClassName()).getDeclaredConstructor(DeferredInitContentProvider.class).newInstance(this);
        } catch (InvocationTargetException e) {
            e = e;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    private String getDelegateClassName() {
        return getClass().getName() + "$Impl";
    }

    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        this.mProviderInfo = providerInfo;
        super.attachInfo(context, providerInfo);
        validateProviderInfo(context, providerInfo);
    }

    @Nullable
    public final ProviderInfo getProviderInfo() {
        return this.mProviderInfo;
    }

    @Nullable
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        return getDelegate().query(uri, strArr, str, strArr2, str2);
    }

    @Nullable
    @TargetApi(16)
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        return getDelegate().query(uri, strArr, str, strArr2, str2, cancellationSignal);
    }

    @Nullable
    public final String getType(Uri uri) {
        return getDelegate().getType(uri);
    }

    @Nullable
    public final Uri insert(Uri uri, @Nullable ContentValues contentValues) {
        return getDelegate().insert(uri, contentValues);
    }

    public final int delete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return getDelegate().delete(uri, str, strArr);
    }

    public final int update(Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return getDelegate().update(uri, contentValues, str, strArr);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().openFile(uri, str);
    }

    @Nullable
    public final ParcelFileDescriptor originalOpenFile(Uri uri, String str) throws FileNotFoundException {
        return super.openFile(uri, str);
    }

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return getDelegate().bulkInsert(uri, contentValuesArr);
    }

    public final int originalBulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return super.bulkInsert(uri, contentValuesArr);
    }

    public final Bundle call(String str, @Nullable String str2, @Nullable Bundle bundle) {
        return getDelegate().call(str, str2, bundle);
    }

    @Nullable
    public final Bundle originalCall(String str, @Nullable String str2, @Nullable Bundle bundle) {
        return super.call(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return getDelegate().applyBatch(arrayList);
    }

    public final ContentProviderResult[] originalApplyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return super.applyBatch(arrayList);
    }

    @Nullable
    public final String[] getStreamTypes(Uri uri, String str) {
        return getDelegate().getStreamTypes(uri, str);
    }

    @Nullable
    public final String[] originalGetStreamTypes(Uri uri, String str) {
        return super.getStreamTypes(uri, str);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().openAssetFile(uri, str);
    }

    @Nullable
    public final AssetFileDescriptor originalOpenAssetFile(Uri uri, String str) throws FileNotFoundException {
        return super.openAssetFile(uri, str);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        return getDelegate().openTypedAssetFile(uri, str, bundle);
    }

    @Nullable
    public final AssetFileDescriptor originalOpenTypedAssetFile(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        return super.openTypedAssetFile(uri, str, bundle);
    }

    /* access modifiers changed from: protected */
    public final boolean isTemporary() {
        return getDelegate().isTemporary();
    }

    /* access modifiers changed from: protected */
    public final boolean originalIsTemporary() {
        return super.isTemporary();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        getDelegate().onConfigurationChanged(configuration);
    }

    public final void originalOnConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public final void shutdown() {
        getDelegate().shutdown();
    }

    public final void originalShutdown() {
        super.shutdown();
    }

    public final void onTrimMemory(int i) {
        if (ProviderInitStatus.isSafeForInit()) {
            getDelegate().onTrimMemory(i);
        }
    }

    public final void originalOnTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    public final void onLowMemory() {
        if (ProviderInitStatus.isSafeForInit()) {
            getDelegate().onLowMemory();
        }
    }

    public final void originalOnLowMemory() {
        super.onLowMemory();
    }
}
