package X;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.facebook.secure.providerinit.DeferredInitContentProvider;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.1bn  reason: invalid class name */
public abstract class AnonymousClass1bn {
    public final DeferredInitContentProvider A00;

    public int A05(Uri uri, ContentValues[] contentValuesArr) {
        return this.A00.originalBulkInsert(uri, contentValuesArr);
    }

    @Nullable
    public AssetFileDescriptor A06(Uri uri, String str) throws FileNotFoundException {
        return this.A00.originalOpenAssetFile(uri, str);
    }

    @Nullable
    public ParcelFileDescriptor A07(Uri uri, String str) throws FileNotFoundException {
        return this.A00.originalOpenFile(uri, str);
    }

    public void A08() {
        this.A00.originalOnLowMemory();
    }

    public void A09(int i) {
        this.A00.originalOnTrimMemory(i);
    }

    public void A0A(Configuration configuration) {
        this.A00.originalOnConfigurationChanged(configuration);
    }

    public boolean A0B() {
        return this.A00.originalIsTemporary();
    }

    public ContentProviderResult[] A0C(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        return this.A00.originalApplyBatch(arrayList);
    }

    public AnonymousClass1bn(DeferredInitContentProvider deferredInitContentProvider) {
        this.A00 = deferredInitContentProvider;
    }
}
