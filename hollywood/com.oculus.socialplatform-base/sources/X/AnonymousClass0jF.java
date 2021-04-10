package X;

import android.annotation.SuppressLint;
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
import com.facebook.systrace.Systrace;
import com.oculus.content.PermissionChecks;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassContentProvider.SecureContentProvider"})
/* renamed from: X.0jF  reason: invalid class name */
public abstract class AnonymousClass0jF extends ContentProvider {
    public final AtomicBoolean mInitialized = new AtomicBoolean();
    @Nullable
    public AbstractC02630jR mLogger = null;

    public boolean allowOwnCallingProcess() {
        return false;
    }

    @Nullable
    public Bundle doCall(String str, String str2, Bundle bundle) {
        return null;
    }

    public abstract int doDelete(Uri uri, String str, String[] strArr);

    @Nullable
    public String[] doGetStreamTypes(Uri uri, String str) {
        return null;
    }

    public abstract String doGetType(Uri uri);

    public abstract Uri doInsert(Uri uri, ContentValues contentValues);

    public void doOnConfigurationChanged(Configuration configuration) {
    }

    public void doOnLowMemory() {
    }

    public void doOnTrimMemory(int i) {
    }

    public abstract Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2);

    public void doShutdown() {
    }

    public abstract int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr);

    @Nullable
    public AbstractC02630jR getLogger() {
        return null;
    }

    public boolean onCheckPermissions() {
        return false;
    }

    public void onInitialize() {
    }

    private void beginTraceSection(String str) {
        if (Systrace.A03(512)) {
            Systrace.A01(512, AnonymousClass006.A09(getClass().getSimpleName(), ".", str));
        }
    }

    private void endTraceSection() {
        Systrace.A00(512);
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        beginTraceSection("applyBatch");
        logUsage("applyBatch");
        try {
            ensureInitializedAndEnforcePermissions();
            return super.applyBatch(arrayList);
        } finally {
            endTraceSection();
        }
    }

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        beginTraceSection("bulkInsert");
        logUsage("bulkInsert");
        try {
            ensureInitializedAndEnforcePermissions();
            return super.bulkInsert(uri, contentValuesArr);
        } finally {
            endTraceSection();
        }
    }

    public final Bundle call(String str, String str2, Bundle bundle) {
        beginTraceSection("call");
        logUsage("call");
        try {
            ensureInitializedAndEnforcePermissions();
            return null;
        } finally {
            endTraceSection();
        }
    }

    public final int delete(Uri uri, @Nullable String str, String[] strArr) {
        beginTraceSection("delete");
        logUsage("delete");
        try {
            ensureInitializedAndEnforcePermissions();
            return doDelete(uri, str, strArr);
        } finally {
            endTraceSection();
        }
    }

    public final void ensureInitialized() {
        synchronized (this.mInitialized) {
            if (!this.mInitialized.get()) {
                onInitialize();
                this.mInitialized.set(true);
            }
        }
    }

    public final void ensureInitializedAndEnforceOpenFilePermissions(String str) {
        if (str.contains("w")) {
            ensureInitializedAndEnforcePermissions();
        } else {
            ensureInitializedAndEnforceReadOnlyPermissions();
        }
    }

    public final String[] getStreamTypes(Uri uri, String str) {
        beginTraceSection("getStreamTypes");
        logUsage("getStreamTypes");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return null;
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

    public final boolean isTemporary() {
        beginTraceSection("isTemporary");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return super.isTemporary();
        } finally {
            endTraceSection();
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        beginTraceSection("onConfigurationChanged");
        try {
            if (this.mInitialized.get()) {
                super.onConfigurationChanged(configuration);
            }
        } finally {
            endTraceSection();
        }
    }

    public final boolean onCreate() {
        beginTraceSection("onCreate");
        endTraceSection();
        return true;
    }

    public final void onLowMemory() {
        beginTraceSection("onLowMemory");
        try {
            if (this.mInitialized.get()) {
                super.onLowMemory();
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
            }
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
            return super.openAssetFile(uri, str);
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
            return super.openFile(uri, str);
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
            return super.openTypedAssetFile(uri, str, bundle);
        } finally {
            endTraceSection();
        }
    }

    public final void shutdown() {
        beginTraceSection("shutdown");
        endTraceSection();
    }

    public final int update(Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        beginTraceSection("update");
        logUsage("update");
        try {
            ensureInitializedAndEnforcePermissions();
            return doUpdate(uri, contentValues, str, strArr);
        } finally {
            endTraceSection();
        }
    }

    private void logUsage(String str) {
        AbstractC02630jR logger = getLogger();
        if (logger != null) {
            Context context = getContext();
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("%s", getClass().getName());
            try {
                logger.logUsage(formatStrLocaleSafe, str, AnonymousClass0kO.A00(Binder.getCallingUid(), context).toString());
            } catch (Exception unused) {
                logger.logUsage(formatStrLocaleSafe, str);
            }
        }
    }

    public ContentProviderResult[] doApplyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return super.applyBatch(arrayList);
    }

    public int doBulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return super.bulkInsert(uri, contentValuesArr);
    }

    public boolean doIsTemporary() {
        return super.isTemporary();
    }

    public AssetFileDescriptor doOpenAssetFile(Uri uri, String str) throws FileNotFoundException {
        return super.openAssetFile(uri, str);
    }

    public ParcelFileDescriptor doOpenFile(Uri uri, String str) throws FileNotFoundException {
        return super.openFile(uri, str);
    }

    @Nullable
    public AssetFileDescriptor doOpenTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        return super.openTypedAssetFile(uri, str, bundle);
    }

    public final void ensureInitializedAndEnforcePermissions() {
        ensureInitialized();
        if ((!allowOwnCallingProcess() || !isOwnCallingProcess()) && !onCheckPermissions()) {
            throw new SecurityException(PermissionChecks.ACCESS_NOT_ALLOWED_MESSAGE);
        }
    }

    public final void ensureInitializedAndEnforceReadOnlyPermissions() {
        ensureInitialized();
        if ((!allowOwnCallingProcess() || !isOwnCallingProcess()) && !onCheckReadOnlyPermissions()) {
            throw new SecurityException(PermissionChecks.ACCESS_NOT_ALLOWED_MESSAGE);
        }
    }

    public boolean isOwnCallingProcess() {
        if (Binder.getCallingUid() == Process.myUid() && Binder.getCallingPid() == Process.myPid()) {
            return true;
        }
        return false;
    }

    public boolean onCheckReadOnlyPermissions() {
        return onCheckPermissions();
    }

    @TargetApi(16)
    public Cursor doQuery(Uri uri, String[] strArr, @Nullable String str, String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        return doQuery(uri, strArr, str, strArr2, str2);
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
    public final Cursor query(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        beginTraceSection("query");
        logUsage("query");
        try {
            ensureInitializedAndEnforceReadOnlyPermissions();
            return doQuery(uri, strArr, str, strArr2, str2);
        } finally {
            endTraceSection();
        }
    }
}
