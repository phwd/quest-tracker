package X;

import android.content.ContentProvider;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import com.facebook.assistant.common.config.tts.AssistantTtsLocalContentProvider;
import com.facebook.proxygen.TraceFieldType;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class J0 extends ContentProvider {
    public final AtomicBoolean A00 = new AtomicBoolean();

    public static void A00() {
        Systrace.A00(512);
    }

    private final void A01() {
        AtomicBoolean atomicBoolean = this.A00;
        synchronized (atomicBoolean) {
            if (!atomicBoolean.get()) {
                if (this instanceof AssistantTtsLocalContentProvider) {
                    AK ak = (AK) ((AssistantTtsLocalContentProvider) this).A00.getValue();
                    Context context = ak.A01;
                    C0514bB.A00(context);
                    AR ar = new AR();
                    ar.A00 = 2;
                    ar.A05 = "AssistantTtsConfigDbQuery";
                    ThreadPoolExecutor A002 = ar.A00();
                    C0514bB.A01(A002, "AssistantExecutors.newTh…sistantTtsConfigDbQuery\")");
                    AR ar2 = new AR();
                    ar2.A00 = 1;
                    ar2.A05 = "AssistantTtsConfigDbWrite";
                    ThreadPoolExecutor A003 = ar2.A00();
                    C0514bB.A01(A003, "AssistantExecutors.newTh…sistantTtsConfigDbWrite\")");
                    ak.A00 = new AH(context, A002, A003);
                }
                atomicBoolean.set(true);
            }
        }
    }

    private void A04(String str) {
        if (Systrace.A03(512)) {
            Systrace.A01(512, AnonymousClass08.A05(getClass().getSimpleName(), ".", str));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.database.Cursor A05(android.net.Uri r18, java.lang.String[] r19, java.lang.String r20, java.lang.String[] r21, java.lang.String r22) {
        /*
        // Method dump skipped, instructions count: 372
        */
        throw new UnsupportedOperationException("Method not decompiled: X.J0.A05(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private final boolean A06() {
        if (!(this instanceof AssistantTtsLocalContentProvider)) {
            return false;
        }
        Context context = getContext();
        try {
            int i = context.getApplicationInfo().uid;
            int callingUid = Binder.getCallingUid();
            try {
                int checkSignatures = context.getPackageManager().checkSignatures(i, callingUid);
                if (i == callingUid || checkSignatures == 0) {
                    return true;
                }
                return false;
            } catch (RuntimeException e) {
                throw new SecurityException(e);
            }
        } catch (SecurityException unused) {
            return false;
        }
    }

    @Override // android.content.ContentProvider
    public final ContentProviderResult[] applyBatch(ArrayList arrayList) {
        A04("applyBatch");
        try {
            A02();
            return super.applyBatch(arrayList);
        } finally {
            A00();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:88:0x02e6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02e7, code lost:
        X.C0495ai.A00(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02ea, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005b A[Catch:{ all -> 0x02f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00aa A[Catch:{ all -> 0x02f3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int bulkInsert(android.net.Uri r33, android.content.ContentValues[] r34) {
        /*
        // Method dump skipped, instructions count: 782
        */
        throw new UnsupportedOperationException("Method not decompiled: X.J0.bulkInsert(android.net.Uri, android.content.ContentValues[]):int");
    }

    public final Bundle call(String str, String str2, Bundle bundle) {
        A04("call");
        try {
            A02();
            return null;
        } finally {
            A00();
        }
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        A04("delete");
        try {
            A02();
            C0514bB.A02(uri, TraceFieldType.Uri);
            ((AssistantTtsLocalContentProvider) this).A00.getValue();
            C0514bB.A02(uri, TraceFieldType.Uri);
            throw new UnsupportedOperationException("Delete Not Supported");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    public final String[] getStreamTypes(Uri uri, String str) {
        A04("getStreamTypes");
        try {
            A03();
            return null;
        } finally {
            A00();
        }
    }

    public final String getType(Uri uri) {
        A04("getType");
        try {
            A03();
            C0514bB.A02(uri, TraceFieldType.Uri);
            ((AssistantTtsLocalContentProvider) this).A00.getValue();
            C0514bB.A02(uri, TraceFieldType.Uri);
            return null;
        } finally {
            A00();
        }
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        A04("insert");
        try {
            A02();
            C0514bB.A02(uri, TraceFieldType.Uri);
            C0514bB.A02(contentValues, "contentValues");
            ((AssistantTtsLocalContentProvider) this).A00.getValue();
            C0514bB.A02(uri, TraceFieldType.Uri);
            C0514bB.A02(contentValues, "contentValues");
            throw new UnsupportedOperationException("Insert Not Supported");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    public final boolean isTemporary() {
        A04("isTemporary");
        try {
            A03();
            return super.isTemporary();
        } finally {
            A00();
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        A04("onConfigurationChanged");
        try {
            if (this.A00.get()) {
                super.onConfigurationChanged(configuration);
            }
        } finally {
            A00();
        }
    }

    public final boolean onCreate() {
        A04("onCreate");
        A00();
        return true;
    }

    public final void onLowMemory() {
        A04("onLowMemory");
        try {
            if (this.A00.get()) {
                super.onLowMemory();
            }
        } finally {
            A00();
        }
    }

    public final void onTrimMemory(int i) {
        A04("onTrimMemory");
        try {
            if (this.A00.get()) {
                super.onTrimMemory(i);
            }
        } finally {
            A00();
        }
    }

    @Override // android.content.ContentProvider
    public final AssetFileDescriptor openAssetFile(Uri uri, String str) {
        A04("openAssetFile");
        try {
            if (str.contains("w")) {
                A02();
            } else {
                A03();
            }
            return super.openAssetFile(uri, str);
        } finally {
            A00();
        }
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        A04("openFile");
        try {
            if (str.contains("w")) {
                A02();
            } else {
                A03();
            }
            return super.openFile(uri, str);
        } finally {
            A00();
        }
    }

    @Override // android.content.ContentProvider
    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) {
        A04("openTypedAssetFile");
        try {
            A03();
            return super.openTypedAssetFile(uri, str, bundle);
        } finally {
            A00();
        }
    }

    public final void shutdown() {
        A04("shutdown");
        A00();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:91:0x026c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x026d, code lost:
        X.C0495ai.A00(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0270, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a1 A[Catch:{ all -> 0x0279 }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0054 A[Catch:{ all -> 0x0279 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int update(android.net.Uri r29, android.content.ContentValues r30, java.lang.String r31, java.lang.String[] r32) {
        /*
        // Method dump skipped, instructions count: 646
        */
        throw new UnsupportedOperationException("Method not decompiled: X.J0.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private final void A02() {
        A01();
        if (!A06()) {
            throw new SecurityException("Component access not allowed.");
        }
    }

    private final void A03() {
        A01();
        if (!A06()) {
            throw new SecurityException("Component access not allowed.");
        }
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        A04("query");
        try {
            A03();
            return A05(uri, strArr, str, strArr2, str2);
        } finally {
            A00();
        }
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        A04("query");
        try {
            A03();
            return A05(uri, strArr, str, strArr2, str2);
        } finally {
            A00();
        }
    }
}
