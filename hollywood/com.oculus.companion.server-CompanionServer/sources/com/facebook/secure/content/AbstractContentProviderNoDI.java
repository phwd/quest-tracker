package com.facebook.secure.content;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.secure.logger.ContentProviderLogger;
import com.facebook.secure.trustedapp.AppIdentity;
import com.facebook.secure.trustedapp.TrustedApp;
import com.facebook.systrace.Systrace;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractContentProviderNoDI extends ContentProvider {
    private final AtomicBoolean mInitialized = new AtomicBoolean();
    private ContentProviderLogger mLogger = null;

    /* access modifiers changed from: protected */
    public boolean allowOwnCallingProcess() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Bundle doCall(String str, String str2, Bundle bundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract int doDelete(Uri uri, String str, String[] strArr);

    /* access modifiers changed from: protected */
    public String[] doGetStreamTypes(Uri uri, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String doGetType(Uri uri);

    /* access modifiers changed from: protected */
    public abstract Uri doInsert(Uri uri, ContentValues contentValues);

    /* access modifiers changed from: protected */
    public void doOnConfigurationChanged(Configuration configuration) {
    }

    /* access modifiers changed from: protected */
    public void doOnLowMemory() {
    }

    /* access modifiers changed from: protected */
    public void doOnTrimMemory(int i) {
    }

    /* access modifiers changed from: protected */
    public AssetFileDescriptor doOpenTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2);

    /* access modifiers changed from: protected */
    public void doShutdown() {
    }

    /* access modifiers changed from: protected */
    public abstract int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr);

    /* access modifiers changed from: protected */
    public ContentProviderLogger getLogger() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean onCheckPermissions() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onInitialize() {
    }

    /* access modifiers changed from: protected */
    public ContentProviderResult[] doApplyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return super.applyBatch(arrayList);
    }

    /* access modifiers changed from: protected */
    public int doBulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return super.bulkInsert(uri, contentValuesArr);
    }

    /* access modifiers changed from: protected */
    public AssetFileDescriptor doOpenAssetFile(Uri uri, String str) throws FileNotFoundException {
        return super.openAssetFile(uri, str);
    }

    /* access modifiers changed from: protected */
    public ParcelFileDescriptor doOpenFile(Uri uri, String str) throws FileNotFoundException {
        return super.openFile(uri, str);
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        return doQuery(uri, strArr, str, strArr2, str2);
    }

    /* access modifiers changed from: protected */
    public boolean doIsTemporary() {
        return super.isTemporary();
    }

    /* access modifiers changed from: protected */
    public boolean onCheckReadOnlyPermissions() {
        return onCheckPermissions();
    }

    /* access modifiers changed from: protected */
    public final void ensureInitialized() {
        synchronized (this.mInitialized) {
            if (!this.mInitialized.get()) {
                onInitialize();
                this.mInitialized.set(true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void ensureInitializedAndEnforcePermissions() {
        ensureInitialized();
        if ((!allowOwnCallingProcess() || !isOwnCallingProcess()) && !onCheckPermissions()) {
            throw new SecurityException("Component access not allowed.");
        }
    }

    /* access modifiers changed from: protected */
    public final void ensureInitializedAndEnforceReadOnlyPermissions() {
        ensureInitialized();
        if ((!allowOwnCallingProcess() || !isOwnCallingProcess()) && !onCheckReadOnlyPermissions()) {
            throw new SecurityException("Component access not allowed.");
        }
    }

    /* access modifiers changed from: protected */
    public final void ensureInitializedAndEnforceOpenFilePermissions(String str) {
        if (str.contains("w")) {
            ensureInitializedAndEnforcePermissions();
        } else {
            ensureInitializedAndEnforceReadOnlyPermissions();
        }
    }

    public final boolean onCreate() {
        beginTraceSection("onCreate");
        endTraceSection();
        return true;
    }

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        beginTraceSection("bulkInsert");
        logUsage("bulkInsert");
        try {
            ensureInitializedAndEnforcePermissions();
            return doBulkInsert(uri, contentValuesArr);
        } finally {
            endTraceSection();
        }
    }

    public final Bundle call(String str, String str2, Bundle bundle) {
        beginTraceSection("call");
        logUsage("call");
        try {
            ensureInitializedAndEnforcePermissions();
            return doCall(str, str2, bundle);
        } finally {
            endTraceSection();
        }
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        beginTraceSection("delete");
        logUsage("delete");
        try {
            ensureInitializedAndEnforcePermissions();
            return doDelete(uri, str, strArr);
        } finally {
            endTraceSection();
        }
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        beginTraceSection("applyBatch");
        logUsage("applyBatch");
        try {
            ensureInitializedAndEnforcePermissions();
            return doApplyBatch(arrayList);
        } finally {
            endTraceSection();
        }
    }

    public final String[] getStreamTypes(Uri uri, String str) {
        beginTraceSection("getStreamTypes");
        logUsage("getStreamTypes");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doGetStreamTypes(uri, str);
        } finally {
            endTraceSection();
        }
    }

    public final String getType(Uri uri) {
        beginTraceSection("getType");
        logUsage("getType");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doGetType(uri);
        } finally {
            endTraceSection();
        }
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        beginTraceSection("insert");
        logUsage("insert");
        try {
            ensureInitializedAndEnforcePermissions();
            return doInsert(uri, contentValues);
        } finally {
            endTraceSection();
        }
    }

    @Override // android.content.ContentProvider
    public final AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        beginTraceSection("openAssetFile");
        logUsage("openAssetFile");
        try {
            ensureInitializedAndEnforceOpenFilePermissions(str);
            return doOpenAssetFile(uri, str);
        } finally {
            endTraceSection();
        }
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        beginTraceSection("openFile");
        logUsage("openFile");
        try {
            ensureInitializedAndEnforceOpenFilePermissions(str);
            return doOpenFile(uri, str);
        } finally {
            endTraceSection();
        }
    }

    @Override // android.content.ContentProvider
    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        beginTraceSection("openTypedAssetFile");
        logUsage("openTypedAssetFile");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doOpenTypedAssetFile(uri, str, bundle);
        } finally {
            endTraceSection();
        }
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        beginTraceSection("query");
        logUsage("query");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doQuery(uri, strArr, str, strArr2, str2);
        } finally {
            endTraceSection();
        }
    }

    @TargetApi(16)
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        beginTraceSection("query");
        logUsage("query");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doQuery(uri, strArr, str, strArr2, str2, cancellationSignal);
        } finally {
            endTraceSection();
        }
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        beginTraceSection("update");
        logUsage("update");
        try {
            ensureInitializedAndEnforcePermissions();
            return doUpdate(uri, contentValues, str, strArr);
        } finally {
            endTraceSection();
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isTemporary() {
        beginTraceSection("isTemporary");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doIsTemporary();
        } finally {
            endTraceSection();
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        beginTraceSection("onConfigurationChanged");
        try {
            if (this.mInitialized.get()) {
                super.onConfigurationChanged(configuration);
                doOnConfigurationChanged(configuration);
                endTraceSection();
            }
        } finally {
            endTraceSection();
        }
    }

    public final void shutdown() {
        beginTraceSection("shutdown");
        try {
            if (this.mInitialized.get()) {
                doShutdown();
                endTraceSection();
            }
        } finally {
            endTraceSection();
        }
    }

    public final void onTrimMemory(int i) {
        beginTraceSection("onTrimMemory");
        try {
            if (this.mInitialized.get()) {
                super.onTrimMemory(i);
                doOnTrimMemory(i);
                endTraceSection();
            }
        } finally {
            endTraceSection();
        }
    }

    public final void onLowMemory() {
        beginTraceSection("onLowMemory");
        try {
            if (this.mInitialized.get()) {
                super.onLowMemory();
                doOnLowMemory();
                endTraceSection();
            }
        } finally {
            endTraceSection();
        }
    }

    private void beginTraceSection(String str) {
        if (Systrace.isTracing(512)) {
            Class<?> cls = getClass();
            Systrace.beginSection(512, cls.getSimpleName() + "." + str);
        }
    }

    private void endTraceSection() {
        Systrace.endSection(512);
    }

    /* access modifiers changed from: protected */
    public boolean isOwnCallingProcess() {
        return Binder.getCallingUid() == Process.myUid() && Binder.getCallingPid() == Process.myPid();
    }

    private void logUsage(String str) {
        AppIdentity appIdentity;
        ContentProviderLogger logger = getLogger();
        if (logger != null) {
            Context context = getContext();
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("%s", getClass().getName());
            try {
                appIdentity = TrustedApp.getAppIdentity(Binder.getCallingUid(), context);
            } catch (Exception unused) {
                appIdentity = null;
            }
            if (appIdentity != null) {
                logger.logUsage(formatStrLocaleSafe, str, AppIdentity.print(appIdentity));
            } else {
                logger.logUsage(formatStrLocaleSafe, str);
            }
        }
    }
}
