package com.oculus.horizon.provider;

import X.AbstractC06640p5;
import X.AnonymousClass04J;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import com.oculus.content.OculusPublicContentProvider;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.service.SignatureChecker;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.provider.OculusContent;
import javax.annotation.Nullable;

public class OVRPlatformContentProvider extends OculusPublicContentProvider {
    public static final String PERMISSION_STATUS_BLOCKED = "blocked";
    public static final String PERMISSION_STATUS_DENIED = "denied";
    public static final String PERMISSION_STATUS_GRANTED = "granted";
    public static final String PERMISSION_STATUS_UNKNOWN = "unknown";
    public static final String TAG = "OVRPlatformContentProvider";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, OVRPlatformContentProvider oVRPlatformContentProvider) {
        oVRPlatformContentProvider._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
    }

    private MatrixCursor getCursorForPermission(String str, String str2) {
        String str3;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{str, "permission_status"}, 1);
        int A01 = AnonymousClass04J.A01(getContext(), str2);
        if (A01 == 0) {
            str3 = "granted";
        } else {
            str3 = "unknown";
        }
        matrixCursor.addRow(new Object[]{Integer.valueOf(A01), str3});
        return matrixCursor;
    }

    private boolean isApplicationAllowed(Uri uri) {
        String str;
        String str2;
        if (uri == null || !uri.toString().startsWith(OculusContent.Platform.CONTENT_URI.toString())) {
            str = TAG;
            str2 = "Unsupported uri";
        } else {
            getContext();
            if (((UserProfileHelper) AnonymousClass0J2.A03(0, 68, this._UL_mInjectionContext)).mCredentialsProvider.get() != null) {
                return true;
            }
            str = TAG;
            str2 = "User is not signed in";
        }
        AnonymousClass0NO.A08(str, str2);
        return false;
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public static final void _UL_injectMe(Context context, OVRPlatformContentProvider oVRPlatformContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), oVRPlatformContentProvider);
    }

    private boolean isApplicationWhitelisted() {
        if (isCallerSystemActivities() || SignatureChecker.isCallerHorizon()) {
            return true;
        }
        return false;
    }

    private boolean isCallerSystemActivities() {
        return "com.oculus.systemactivities".equals(getContext().getPackageManager().getPackagesForUid(Binder.getCallingUid())[0]);
    }

    @Override // com.oculus.content.OculusPublicContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String str4;
        if (isApplicationAllowed(uri)) {
            String queryParameter = uri.getQueryParameter("request");
            if (OculusContent.Platform.MIC_PERMISSION_REQUEST_NAME.equals(queryParameter)) {
                str3 = "microphone_status";
                str4 = "android.permission.RECORD_AUDIO";
            } else if (OculusContent.Platform.WRITE_STORAGE_PERMISSION_REQUEST_NAME.equals(queryParameter)) {
                str3 = OculusContent.Platform.WRITE_STORAGE_PERMISSION_RESPONSE;
                str4 = OVRMediaServiceManager.EXTERNAL_STORAGE_PERMISSION;
            }
            return getCursorForPermission(str3, str4);
        }
        return null;
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (!isApplicationAllowed(uri) || !isApplicationWhitelisted()) {
            return 0;
        }
        return 1;
    }
}
