package com.facebook.mobileconfigservice.contentprovider;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QA;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rp;
import X.AnonymousClass1ea;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.secure.content.PublicContentProvider;
import java.util.List;
import javax.annotation.Nullable;

public class MobileConfigContentProvider extends PublicContentProvider implements AnonymousClass0QA {
    public static final String TAG = "MobileConfigContentProvider";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, MobileConfigContentProvider mobileConfigContentProvider) {
        mobileConfigContentProvider._UL_mInjectionContext = new AnonymousClass0QC(2, r2);
    }

    private MatrixCursor createEmptyCursor() {
        return new MatrixCursor(new String[]{"val", "value_source"}, 1);
    }

    @Override // X.AbstractC09361bk
    public int doBulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return 0;
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AbstractC09361bk
    public String doGetType(Uri uri) {
        return AnonymousClass1ea.MIME_PLAINTEXT;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private MatrixCursor buildCursor(List<String[]> list, String[] strArr) {
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        for (String[] strArr2 : list) {
            if (strArr2 == null || strArr2.length != strArr.length) {
                AnonymousClass0NO.A0E(TAG, "Incorrect row from buildCursor: %s", TextUtils.join(", ", strArr2));
            } else {
                matrixCursor.addRow(strArr2);
            }
        }
        return matrixCursor;
    }

    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static final void _UL_injectMe(Context context, MobileConfigContentProvider mobileConfigContentProvider) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), mobileConfigContentProvider);
    }

    @SuppressLint({"CatchGeneralException"})
    @TargetApi(19)
    private String getCallingPackageOrFallback() {
        try {
            String callingPackage = getCallingPackage();
            if (callingPackage != null) {
                return callingPackage;
            }
            throw null;
        } catch (Exception unused) {
            Context context = getContext();
            if (context != null) {
                return context.getPackageName();
            }
            throw null;
        }
    }

    public static <E extends Enum<E>> String[] schemaToStringArray(Class<E> cls) {
        E[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            return new String[0];
        }
        int length = enumConstants.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = enumConstants[i].toString();
        }
        return strArr;
    }

    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC09361bk
    public boolean onCheckPermissions() {
        if (!(Binder.getCallingUid() == Process.myUid() || Binder.getCallingUid() == 0)) {
            ((AnonymousClass0Rp) AnonymousClass0J2.A03(1, 306, this._UL_mInjectionContext)).A2U(getContext());
        }
        return super.onCheckPermissions();
    }

    @Override // X.AbstractC09361bk
    public void onInitialize() {
        super.onInitialize();
        _UL_injectMe(getContext(), this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01f1  */
    @Override // X.AbstractC09361bk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor doQuery(android.net.Uri r23, java.lang.String[] r24, java.lang.String r25, java.lang.String[] r26, java.lang.String r27) {
        /*
        // Method dump skipped, instructions count: 1210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfigservice.contentprovider.MobileConfigContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }
}
