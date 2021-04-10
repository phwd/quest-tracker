package com.oculus.horizon.sso;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass117;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import com.facebook.internal.NativeProtocol;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.content.OculusFbPermissionsContentProvider;
import com.oculus.horizon.abuse_prevention.AudioCapture;

public class SsoContentProvider extends OculusFbPermissionsContentProvider {
    public static final String AUTHORITY;
    public static final Uri CONTENT_URI;
    public static final String TAG = "SsoContentProvider";
    @Inject
    @Eager
    public SsoManager mSsoManager;

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return "com.oculus.horizon.fbpermission.SSO_CONTENT_PROVIDER";
    }

    static {
        String A05 = AnonymousClass006.A05("com.oculus.horizon", ".sso");
        AUTHORITY = A05;
        CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, A05, "/cookies"));
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, SsoContentProvider ssoContentProvider) {
        ssoContentProvider.mSsoManager = (SsoManager) AnonymousClass117.A00(AudioCapture.AUDIO_RECORDER_INTERVAL_MS, r1);
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC09361bk
    public String doGetType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC09361bk
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0179, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x017a, code lost:
        X.AnonymousClass0NO.A0B(com.oculus.horizon.sso.SsoContentProvider.TAG, "Error getting session", r2);
     */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0179 A[ExcHandler: FeatureDisabledException | NoFacebookUserException | ServiceException | GeneralSecurityException (r2v5 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:14:0x0067] */
    @Override // X.AbstractC09361bk
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor doQuery(android.net.Uri r14, java.lang.String[] r15, java.lang.String r16, java.lang.String[] r17, java.lang.String r18) {
        /*
        // Method dump skipped, instructions count: 386
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.sso.SsoContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    public static final void _UL_injectMe(Context context, SsoContentProvider ssoContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), ssoContentProvider);
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }

    public String getCallingPackageName() {
        return getContext().getPackageManager().getPackagesForUid(Binder.getCallingUid())[0];
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        if (super.onCheckPermissions() || this.mSsoManager.A00(getCallingPackageName())) {
            return true;
        }
        return false;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public final boolean onCheckReadOnlyPermissions() {
        if (super.onCheckReadOnlyPermissions() || this.mSsoManager.A00(getCallingPackageName())) {
            return true;
        }
        return false;
    }
}
