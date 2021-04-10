package X;

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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.0jd  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02680jd {
    public final DeferredInitContentProvider A00;

    public abstract int A05(Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr);

    public abstract int A06(Uri uri, @Nullable String str, @Nullable String[] strArr);

    @Nullable
    public abstract Cursor A0A(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2);

    @Nullable
    public abstract Uri A0C(Uri uri, @Nullable ContentValues contentValues);

    @Nullable
    public abstract String A0F(Uri uri);

    public int A07(Uri uri, ContentValues[] contentValuesArr) {
        return this.A00.originalBulkInsert(uri, contentValuesArr);
    }

    @Nullable
    public AssetFileDescriptor A08(Uri uri, String str) throws FileNotFoundException {
        return this.A00.originalOpenAssetFile(uri, str);
    }

    @Nullable
    public AssetFileDescriptor A09(Uri uri, String str, @Nullable Bundle bundle) throws FileNotFoundException {
        return this.A00.originalOpenTypedAssetFile(uri, str, bundle);
    }

    public Bundle A0D(String str, @Nullable String str2, @Nullable Bundle bundle) {
        return this.A00.originalCall(str, str2, bundle);
    }

    @Nullable
    public ParcelFileDescriptor A0E(Uri uri, String str) throws FileNotFoundException {
        return this.A00.originalOpenFile(uri, str);
    }

    public void A0G() {
        this.A00.originalOnLowMemory();
    }

    public void A0H() {
        this.A00.originalShutdown();
    }

    public void A0I(int i) {
        this.A00.originalOnTrimMemory(i);
    }

    public void A0J(Configuration configuration) {
        this.A00.originalOnConfigurationChanged(configuration);
    }

    public boolean A0K() {
        return this.A00.originalIsTemporary();
    }

    public ContentProviderResult[] A0L(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return this.A00.originalApplyBatch(arrayList);
    }

    @Nullable
    public String[] A0M(Uri uri, String str) {
        return this.A00.originalGetStreamTypes(uri, str);
    }

    public AbstractC02680jd(DeferredInitContentProvider deferredInitContentProvider) {
        this.A00 = deferredInitContentProvider;
    }

    @Nullable
    public Cursor A0B(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2, @Nullable CancellationSignal cancellationSignal) {
        A0A(uri, strArr, str, strArr2, str2);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
