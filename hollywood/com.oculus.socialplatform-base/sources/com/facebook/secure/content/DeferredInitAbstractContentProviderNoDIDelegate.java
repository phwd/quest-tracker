package com.facebook.secure.content;

import X.AbstractC02680jd;
import X.AnonymousClass006;
import android.annotation.TargetApi;
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
import com.facebook.secure.providerinit.DeferredInitContentProvider;
import com.facebook.systrace.Systrace;
import com.oculus.content.PermissionChecks;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public abstract class DeferredInitAbstractContentProviderNoDIDelegate extends AbstractC02680jd {
    public final AtomicBoolean A00 = new AtomicBoolean();

    public boolean A0N() {
        return false;
    }

    public static void A00() {
        Systrace.A00(512);
    }

    private final void A01() {
        AtomicBoolean atomicBoolean = this.A00;
        synchronized (atomicBoolean) {
            if (!atomicBoolean.get()) {
                atomicBoolean.set(true);
            }
        }
    }

    private void A04(String str) {
        if (Systrace.A03(512)) {
            Systrace.A01(512, AnonymousClass006.A09(getClass().getSimpleName(), ".", str));
        }
    }

    @Override // X.AbstractC02680jd
    public final int A05(Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        A04("update");
        try {
            A02();
            throw new NullPointerException("doUpdate");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    @Override // X.AbstractC02680jd
    public final int A06(Uri uri, @Nullable String str, String[] strArr) {
        A04("delete");
        try {
            A02();
            throw new NullPointerException("doDelete");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    @Override // X.AbstractC02680jd
    public final int A07(Uri uri, ContentValues[] contentValuesArr) {
        A04("bulkInsert");
        try {
            A02();
            return super.A07(uri, contentValuesArr);
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final AssetFileDescriptor A08(Uri uri, String str) throws FileNotFoundException {
        A04("openAssetFile");
        try {
            if (str.contains("w")) {
                A02();
            } else {
                A03();
            }
            return super.A08(uri, str);
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final AssetFileDescriptor A09(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        A04("openTypedAssetFile");
        try {
            A03();
            return null;
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final Cursor A0A(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        A04("query");
        try {
            A03();
            throw new NullPointerException("doQuery");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    @Override // X.AbstractC02680jd
    @TargetApi(16)
    public final Cursor A0B(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        A04("query");
        try {
            A03();
            throw new NullPointerException("doQuery");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    @Override // X.AbstractC02680jd
    public final Uri A0C(Uri uri, ContentValues contentValues) {
        A04("insert");
        try {
            A02();
            throw new NullPointerException("doInsert");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    @Override // X.AbstractC02680jd
    public final Bundle A0D(String str, String str2, Bundle bundle) {
        A04("call");
        try {
            A02();
            return null;
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final ParcelFileDescriptor A0E(Uri uri, String str) throws FileNotFoundException {
        A04("openFile");
        try {
            if (str.contains("w")) {
                A02();
            } else {
                A03();
            }
            return super.A0E(uri, str);
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final String A0F(Uri uri) {
        A04("getType");
        try {
            A03();
            throw new NullPointerException("doGetType");
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }

    @Override // X.AbstractC02680jd
    public final void A0G() {
        A04("onLowMemory");
        try {
            if (this.A00.get()) {
                super.A0G();
            }
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final void A0H() {
        A04("shutdown");
        A00();
    }

    @Override // X.AbstractC02680jd
    public final void A0I(int i) {
        A04("onTrimMemory");
        try {
            if (this.A00.get()) {
                super.A0I(i);
            }
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final void A0J(Configuration configuration) {
        A04("onConfigurationChanged");
        try {
            if (this.A00.get()) {
                super.A0J(configuration);
            }
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final boolean A0K() {
        A04("isTemporary");
        try {
            A03();
            return super.A0K();
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final ContentProviderResult[] A0L(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        A04("applyBatch");
        try {
            A02();
            return super.A0L(arrayList);
        } finally {
            A00();
        }
    }

    @Override // X.AbstractC02680jd
    public final String[] A0M(Uri uri, String str) {
        A04("getStreamTypes");
        try {
            A03();
            return null;
        } finally {
            A00();
        }
    }

    public DeferredInitAbstractContentProviderNoDIDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
        A04("onCreate");
        A00();
    }

    private final void A02() {
        A01();
        if (!A0N()) {
            throw new SecurityException(PermissionChecks.ACCESS_NOT_ALLOWED_MESSAGE);
        }
    }

    private final void A03() {
        A01();
        if (!A0N()) {
            throw new SecurityException(PermissionChecks.ACCESS_NOT_ALLOWED_MESSAGE);
        }
    }
}
