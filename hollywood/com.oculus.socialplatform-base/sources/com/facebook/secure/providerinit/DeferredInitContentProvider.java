package com.facebook.secure.providerinit;

import X.AbstractC02680jd;
import X.AnonymousClass006;
import X.C02690je;
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
    public static final String TAG = "DeferredInitContentProvider";
    public AbstractC02680jd mDelegate;
    public volatile ProviderInfo mProviderInfo;

    /* access modifiers changed from: private */
    public synchronized AbstractC02680jd getDelegate() {
        AbstractC02680jd r0;
        r0 = this.mDelegate;
        if (r0 == null) {
            C02690je.A00.block();
            r0 = getDelegateInstance();
            this.mDelegate = r0;
        }
        return r0;
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
        if (C02690je.A00.block(-1)) {
            getDelegate().A0G();
        }
    }

    public final void onTrimMemory(int i) {
        if (C02690je.A00.block(-1)) {
            getDelegate().A0I(i);
        }
    }

    private String getDelegateClassName() {
        return AnonymousClass006.A07(getClass().getName(), "$Impl");
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return getDelegate().A0L(arrayList);
    }

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return getDelegate().A07(uri, contentValuesArr);
    }

    public final Bundle call(String str, @Nullable String str2, @Nullable Bundle bundle) {
        return getDelegate().A0D(str, str2, bundle);
    }

    public final int delete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        getDelegate().A06(uri, str, strArr);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public AbstractC02680jd getDelegateInstance() {
        try {
            return (AbstractC02680jd) Class.forName(getDelegateClassName()).getDeclaredConstructor(DeferredInitContentProvider.class).newInstance(this);
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
        return getDelegate().A0M(uri, str);
    }

    @Nullable
    public final String getType(Uri uri) {
        getDelegate().A0F(uri);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Nullable
    public final Uri insert(Uri uri, @Nullable ContentValues contentValues) {
        getDelegate().A0C(uri, contentValues);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final boolean isTemporary() {
        return getDelegate().A0K();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        getDelegate().A0J(configuration);
    }

    public final boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().A08(uri, str);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return getDelegate().A0E(uri, str);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        return getDelegate().A09(uri, str, bundle);
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
        getDelegate().A0H();
    }

    public final int update(Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        getDelegate().A05(uri, contentValues, str, strArr);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void originalOnConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public final void originalOnTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    @Nullable
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        getDelegate().A0A(uri, strArr, str, strArr2, str2);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Nullable
    @TargetApi(16)
    public Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        getDelegate().A0B(uri, strArr, str, strArr2, str2, cancellationSignal);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
