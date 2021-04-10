package com.oculus.horizon.provider;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.inject.name.Named;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.config.trusted_user.TrustedUserModule;
import com.oculus.content.OculusPublicReadContentProvider;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.horizon.service.ExternalPlatformLocal;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.locale.LocaleModule;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

public class ProfileContentProvider extends OculusPublicReadContentProvider {
    public static final String TAG = "ProfileContentProvider";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public Provider<Credentials> mCredentialsProvider;
    @Named(TrustedUserModule.IS_TRUSTED_USER_GK)
    @Inject
    public Provider<Boolean> mIsTrustedUser;
    @Inject
    public Provider<Locale> mLocaleProvider;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, ProfileContentProvider profileContentProvider) {
        profileContentProvider._UL_mInjectionContext = new AnonymousClass0QC(5, r2);
        profileContentProvider.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r2);
        profileContentProvider.mLocaleProvider = LocaleModule.A01(r2);
        profileContentProvider.mIsTrustedUser = TrustedUserModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_is_ULUNDERSCORE_trusted_ULUNDERSCORE_user_ULSEP_ACCESS_METHOD(r2);
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

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return "com.oculus.horizon.fbpermission.PROFILE_WRITE_CONTENT_PROVIDER";
    }

    private App getApp(String str, String str2) throws IOException, ExternalPlatformLocal.PackageNotInLibraryException {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putString("app_id", str2);
        }
        String appID = ExternalPlatformLocal.getAppID(bundle, str, (OVRLibrary) AnonymousClass0J2.A03(0, 569, this._UL_mInjectionContext));
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            App A02 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, this._UL_mInjectionContext)).A02(appID);
            if (A02 != null) {
                return A02;
            }
            throw new ExternalPlatformLocal.PackageNotInLibraryException(str);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    private boolean isTrustedUser(Context context) {
        if (this.mIsTrustedUser.get().booleanValue() || ((UnlockulusHelper) AnonymousClass0J2.A03(1, 296, this._UL_mInjectionContext)).A00(context)) {
            return true;
        }
        return false;
    }

    public static final void _UL_injectMe(Context context, ProfileContentProvider profileContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), profileContentProvider);
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        super.doInitialization();
        _UL_injectMe(getContext(), this);
    }

    public void notifyPreferenceChange(ContentValues contentValues) {
        for (String str : contentValues.keySet()) {
            if (!TextUtils.isEmpty(str)) {
                getContext().getContentResolver().notifyChange(new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority("com.oculus.horizon").path(FBConnectHelper.FACEBOOK_DATA_PROFILE_KEY).appendPath(str).build(), null);
            } else {
                throw new IllegalArgumentException("NUX Preference cannot be null/empty");
            }
        }
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        if (super.onCheckPermissions() || ((ProfileProviderTrustedApps) AnonymousClass0J2.A03(3, 364, this._UL_mInjectionContext)).isTrusted(getContext())) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.content.OculusPublicReadContentProvider, com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public final boolean onCheckReadOnlyPermissions() {
        if (super.onCheckReadOnlyPermissions() || ((ProfileProviderTrustedApps) AnonymousClass0J2.A03(3, 364, this._UL_mInjectionContext)).isTrusted(getContext())) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0064, code lost:
        if (com.oculus.signature.SignatureHelper.A02(r8, r10, isTrustedUser(r8)) != false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x02f0, code lost:
        if (r1 != false) goto L_0x02f2;
     */
    @Override // X.AbstractC09361bk
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor doQuery(android.net.Uri r16, java.lang.String[] r17, java.lang.String r18, java.lang.String[] r19, java.lang.String r20) {
        /*
        // Method dump skipped, instructions count: 1384
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.provider.ProfileContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x02a8, code lost:
        if (r3 != false) goto L_0x029f;
     */
    @Override // X.AbstractC09361bk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int doUpdate(android.net.Uri r6, final android.content.ContentValues r7, java.lang.String r8, java.lang.String[] r9) {
        /*
        // Method dump skipped, instructions count: 686
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.provider.ProfileContentProvider.doUpdate(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }
}
