package com.oculus.auth.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0b1;
import X.AnonymousClass1Ch;
import X.AnonymousClass1E5;
import X.C02790bO;
import X.C02840bc;
import X.C02870bf;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.HashMultimap;
import com.oculus.aidl.IAuthService2;
import com.oculus.aidl.IAuthenticationCallback;
import com.oculus.common.build.BuildConstants;
import com.oculus.signature.SignatureHelper;
import java.util.List;

public class AuthService2 extends Service {
    public static final String FBPERMISSION_OCULUS_AUTHSERVICE2 = "com.oculus.horizon.fbpermission.AUTHSERVICE2";
    public static final String TAG = "AuthService2";
    @Inject
    @Eager
    public AuthManager mAuthManager;
    public final IAuthService2.Stub mBinder = new IAuthService2.Stub() {
        /* class com.oculus.auth.service.AuthService2.AnonymousClass2 */

        @Override // com.oculus.aidl.IAuthService2
        public void fetchCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback) {
            AuthService2.this.isCallerAppTrusted();
            AuthService2.this.mAuthManager.performFetchCredentials(str, str2, iAuthenticationCallback);
            throw new SecurityException("Access denied. Caller is not trusted.");
        }

        @Override // com.oculus.aidl.IAuthService2
        public void fetchDeviceScopedCredentials(String str, String str2, boolean z, IAuthenticationCallback iAuthenticationCallback) {
            AuthService2.this.isCallerAppTrusted();
            AuthService2.this.mAuthManager.performFetchDeviceScopedCredentials(str, str2, Boolean.valueOf(z), iAuthenticationCallback);
            throw new SecurityException("Access denied. Caller is not trusted.");
        }

        @Override // com.oculus.aidl.IAuthService2
        @Deprecated
        public Bundle getCredentials() {
            AuthService2.this.isCallerAppTrusted();
            return AuthService2.this.mAuthManager.getCredentialsBundle(false);
        }

        @Override // com.oculus.aidl.IAuthService2
        @Deprecated
        public Bundle getDeviceScopedCredentials() {
            AuthService2.this.isCallerAppTrusted();
            return AuthService2.this.mAuthManager.getCredentialsBundle(true);
        }
    };
    public final AnonymousClass0b1 mFbPermissionReporter = new AnonymousClass0b1() {
        /* class com.oculus.auth.service.AuthService2.AnonymousClass1 */

        @Override // X.AnonymousClass0b1
        public void report(String str) {
            AnonymousClass0NO.A08(AuthService2.TAG, str);
        }

        @Override // X.AnonymousClass0b1
        public void report(String str, String str2, Throwable th) {
            AnonymousClass0NO.A0B(str, str2, th);
        }
    };
    @Inject
    @Eager
    public AuthTrustedAppVerifier mTrustedAppVerified;
    public AnonymousClass1Ch mTrustedCallerVerifier;

    private boolean isFbPermissionGranted(Context context, C02790bO r10) {
        List<String> list = r10.A04;
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                if (C02840bc.A04(context, str)) {
                    C02840bc A01 = C02840bc.A01(this.mFbPermissionReporter);
                    if (C02840bc.A05(context, str, FBPERMISSION_OCULUS_AUTHSERVICE2) || C02840bc.A06(A01, context, str, FBPERMISSION_OCULUS_AUTHSERVICE2)) {
                        return true;
                    }
                    C02840bc.A03(A01, String.format("FBPermission '%s' was not granted to package '%s'", FBPERMISSION_OCULUS_AUTHSERVICE2, str), false);
                } else {
                    this.mFbPermissionReporter.report(StringFormatUtil.formatStrLocaleSafe("App %s is not FbPermission signed", str));
                }
            }
        }
        return false;
    }

    public static final void _UL_injectMe(Context context, AuthService2 authService2) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), authService2);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, AuthService2 authService2) {
        authService2.mAuthManager = AuthManager._UL__ULSEP_com_oculus_auth_service_AuthManager_ULSEP_ACCESS_METHOD(r1);
        authService2.mTrustedAppVerified = AuthTrustedAppVerifier._UL__ULSEP_com_oculus_auth_service_AuthTrustedAppVerifier_ULSEP_ACCESS_METHOD(r1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isCallerAppTrusted() {
        C02790bO A01 = C02870bf.A01(this);
        if (this.mTrustedAppVerified.isTrusted(A01) || isFbPermissionGranted(this, A01)) {
            return true;
        }
        AnonymousClass1E5 A00 = AnonymousClass1Ch.A00(this.mTrustedCallerVerifier);
        if (A00.A03) {
            return true;
        }
        StringBuilder sb = new StringBuilder("Access denied. Caller is not trusted: ");
        sb.append(A00);
        throw new SecurityException(sb.toString());
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
        HashMultimap hashMultimap = new HashMultimap();
        hashMultimap.A7h(SignatureHelper.OCULUS_PROD_RELEASE_SIGNATURE, "com.oculus.ocms");
        hashMultimap.A7h(SignatureHelper.GO_PLATFORM_RELEASE_SIGNATURE, BuildConstants.PACKAGE_NAME_BUGREPORTER2);
        hashMultimap.A7h(SignatureHelper.QUEST_PLATFORM_RELEASE_SIGNATURE, BuildConstants.PACKAGE_NAME_BUGREPORTER2);
        hashMultimap.A7h(SignatureHelper.MIRAMAR_PLATFORM_RELEASE_SIGNATURE, BuildConstants.PACKAGE_NAME_BUGREPORTER2);
        this.mTrustedCallerVerifier = new AnonymousClass1Ch(hashMultimap, getPackageManager());
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
