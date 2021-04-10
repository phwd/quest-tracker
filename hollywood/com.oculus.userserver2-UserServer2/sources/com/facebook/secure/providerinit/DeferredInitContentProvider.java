package com.facebook.secure.providerinit;

import X.AnonymousClass06;
import X.f3;
import X.f4;
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
import com.facebook.secure.content.DeferredInitAbstractContentProviderNoDIDelegate;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.annotation.Nullable;

public abstract class DeferredInitContentProvider extends ContentProvider {
    public static final String TAG = "DeferredInitContentProvider";
    public f3 mDelegate;
    public volatile ProviderInfo mProviderInfo;

    /* access modifiers changed from: private */
    public synchronized f3 getDelegate() {
        f3 f3Var;
        f3Var = this.mDelegate;
        if (f3Var == null) {
            f4.A00.block();
            f3Var = getDelegateInstance();
            this.mDelegate = f3Var;
        }
        return f3Var;
    }

    public boolean runCustomOnCreate() {
        return false;
    }

    public void validateProviderInfo(Context context, ProviderInfo providerInfo) {
    }

    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        this.mProviderInfo = providerInfo;
        super.attachInfo(context, providerInfo);
    }

    public final void onLowMemory() {
        if (f4.A00.block(-1)) {
            getDelegate().A08();
        }
    }

    public final void onTrimMemory(int i) {
        if (f4.A00.block(-1)) {
            getDelegate().A09(i);
        }
    }

    private String getDelegateClassName() {
        return AnonymousClass06.A03(getClass().getName(), "$Impl");
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return getDelegate().A0C(arrayList);
    }

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return getDelegate().A05(uri, contentValuesArr);
    }

    public final Bundle call(String str, @Nullable String str2, @Nullable Bundle bundle) {
        f3 delegate = getDelegate();
        if (!(delegate instanceof DeferredInitAbstractContentProviderNoDIDelegate)) {
            return delegate.A00.originalCall(str, str2, bundle);
        }
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) delegate;
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "call");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A02(deferredInitAbstractContentProviderNoDIDelegate);
            return null;
        } finally {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
        }
    }

    public final int delete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) getDelegate();
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "delete");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A02(deferredInitAbstractContentProviderNoDIDelegate);
            throw null;
        } catch (Throwable th) {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
            throw th;
        }
    }

    public f3 getDelegateInstance() {
        try {
            return (f3) Class.forName(getDelegateClassName()).getDeclaredConstructor(DeferredInitContentProvider.class).newInstance(this);
        } catch (InvocationTargetException e) {
            e = e;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            if (e instanceof RuntimeException) {
                throw e;
            }
            throw new RuntimeException(e);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Nullable
    public final ProviderInfo getProviderInfo() {
        return this.mProviderInfo;
    }

    @Nullable
    public final String[] getStreamTypes(Uri uri, String str) {
        f3 delegate = getDelegate();
        if (!(delegate instanceof DeferredInitAbstractContentProviderNoDIDelegate)) {
            return delegate.A00.originalGetStreamTypes(uri, str);
        }
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) delegate;
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "getStreamTypes");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A03(deferredInitAbstractContentProviderNoDIDelegate);
            return null;
        } finally {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
        }
    }

    @Nullable
    public final String getType(Uri uri) {
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) getDelegate();
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "getType");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A03(deferredInitAbstractContentProviderNoDIDelegate);
            throw null;
        } catch (Throwable th) {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
            throw th;
        }
    }

    @Nullable
    public final Uri insert(Uri uri, @Nullable ContentValues contentValues) {
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) getDelegate();
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "insert");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A02(deferredInitAbstractContentProviderNoDIDelegate);
            throw null;
        } catch (Throwable th) {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
            throw th;
        }
    }

    public final boolean isTemporary() {
        return getDelegate().A0B();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        getDelegate().A0A(configuration);
    }

    public final boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().A06(uri, str);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().A07(uri, str);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        f3 delegate = getDelegate();
        if (!(delegate instanceof DeferredInitAbstractContentProviderNoDIDelegate)) {
            return delegate.A00.originalOpenTypedAssetFile(uri, str, bundle);
        }
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) delegate;
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "openTypedAssetFile");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A03(deferredInitAbstractContentProviderNoDIDelegate);
            return null;
        } finally {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
        }
    }

    public final ContentProviderResult[] originalApplyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return super.applyBatch(arrayList);
    }

    public final int originalBulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return super.bulkInsert(uri, contentValuesArr);
    }

    @Nullable
    public final Bundle originalCall(String str, @Nullable String str2, @Nullable Bundle bundle) {
        return super.call(str, str2, bundle);
    }

    @Nullable
    public final String[] originalGetStreamTypes(Uri uri, String str) {
        return super.getStreamTypes(uri, str);
    }

    public final boolean originalIsTemporary() {
        return super.isTemporary();
    }

    public final void originalOnLowMemory() {
        super.onLowMemory();
    }

    @Nullable
    public final AssetFileDescriptor originalOpenAssetFile(Uri uri, String str) throws FileNotFoundException {
        return super.openAssetFile(uri, str);
    }

    @Nullable
    public final ParcelFileDescriptor originalOpenFile(Uri uri, String str) throws FileNotFoundException {
        return super.openFile(uri, str);
    }

    @Nullable
    public final AssetFileDescriptor originalOpenTypedAssetFile(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        return super.openTypedAssetFile(uri, str, bundle);
    }

    public final void originalShutdown() {
        super.shutdown();
    }

    public final void shutdown() {
        f3 delegate = getDelegate();
        if (!(delegate instanceof DeferredInitAbstractContentProviderNoDIDelegate)) {
            delegate.A00.originalShutdown();
            return;
        }
        DeferredInitAbstractContentProviderNoDIDelegate.A04((DeferredInitAbstractContentProviderNoDIDelegate) delegate, "shutdown");
        DeferredInitAbstractContentProviderNoDIDelegate.A00();
    }

    public final int update(Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) getDelegate();
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "update");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A02(deferredInitAbstractContentProviderNoDIDelegate);
            throw null;
        } catch (Throwable th) {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
            throw th;
        }
    }

    public final void originalOnConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public final void originalOnTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    @Nullable
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) getDelegate();
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "query");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A03(deferredInitAbstractContentProviderNoDIDelegate);
            throw null;
        } catch (Throwable th) {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
            throw th;
        }
    }

    @Nullable
    @TargetApi(16)
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        f3 delegate = getDelegate();
        boolean z = delegate instanceof DeferredInitAbstractContentProviderNoDIDelegate;
        DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate = (DeferredInitAbstractContentProviderNoDIDelegate) delegate;
        DeferredInitAbstractContentProviderNoDIDelegate.A04(deferredInitAbstractContentProviderNoDIDelegate, "query");
        try {
            DeferredInitAbstractContentProviderNoDIDelegate.A03(deferredInitAbstractContentProviderNoDIDelegate);
            throw null;
        } catch (Throwable th) {
            DeferredInitAbstractContentProviderNoDIDelegate.A00();
            throw th;
        }
    }
}
