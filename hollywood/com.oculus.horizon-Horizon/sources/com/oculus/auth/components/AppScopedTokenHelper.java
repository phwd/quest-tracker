package com.oculus.auth.components;

import X.AbstractC06640p5;
import X.AnonymousClass0Pi;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C01010Iv;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_api_AuthMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_components_AuthComponentRunner_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID"})
@ApplicationScoped
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppScopedTokenHelper {
    public static final String TAG = "AppScopedTokenHelper";
    public static volatile AppScopedTokenHelper _UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @GuardedBy("mNonHorizonTokenCache")
    @Nullable
    public String mMostRecentHorizonToken;
    @GuardedBy("mNonHorizonTokenCache")
    public final Map<String, String> mNonHorizonTokenCache = new HashMap();

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_components_AppScopedTokenHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(211, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AppScopedTokenHelper _UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (AppScopedTokenHelper) AnonymousClass117.A00(211, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final AppScopedTokenHelper _UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_FACTORY_METHOD(AbstractC06640p5 r4) {
        if (_UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_INSTANCE == null) {
            synchronized (AppScopedTokenHelper.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_INSTANCE = new AppScopedTokenHelper(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_auth_components_AppScopedTokenHelper_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_components_AppScopedTokenHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(211, r2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        r14.mIsQuiet = false;
        r5 = ((android.content.Context) X.AnonymousClass0J2.A03(0, 294, r11._UL_mInjectionContext)).getSharedPreferences(com.oculus.horizon.push.PushTokenSharedPreferencesHelper.SHARED_PREFS_NAME, 0).getString(com.oculus.horizon.push.PushTokenSharedPreferencesHelper.PUSH_TOKEN_KEY_NAME, null);
        r0 = ((android.content.Context) X.AnonymousClass0J2.A03(0, 294, r11._UL_mInjectionContext)).getPackageManager();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007e, code lost:
        if (r0 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        r7 = r0.getInstallerPackageName(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0084, code lost:
        r8 = X.AnonymousClass0bU.A01((android.content.Context) X.AnonymousClass0J2.A03(0, 294, r11._UL_mInjectionContext), r12).versionCode;
        r6 = X.AnonymousClass0bU.A03((android.content.Context) X.AnonymousClass0J2.A03(0, 294, r11._UL_mInjectionContext), r12).sha256Hash;
        r10 = new com.oculus.horizon.api.ApiTaskCallback();
        ((com.oculus.auth.api.AuthMethods) X.AnonymousClass0J2.A03(1, 84, r11._UL_mInjectionContext)).authenticateApplication(r3, r13, r5, r6, r7, r8, false, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d1, code lost:
        return ((com.oculus.auth.components.AuthComponentRunner) X.AnonymousClass0J2.A03(2, 6, r11._UL_mInjectionContext)).runAsync(r10.mCompletionSource.A00.A0B(new com.oculus.auth.components.AppScopedTokenHelper.AnonymousClass1(r11)), com.oculus.auth.components.AuthLogger.COMPONENT_OBTAIN_DSAT, r14);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.AnonymousClass0DC<com.google.common.base.Optional<java.lang.String>> fetchAppScopedTokenAsync(final java.lang.String r12, java.lang.String r13, com.oculus.auth.components.AuthAction r14) {
        /*
        // Method dump skipped, instructions count: 213
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.auth.components.AppScopedTokenHelper.fetchAppScopedTokenAsync(java.lang.String, java.lang.String, com.oculus.auth.components.AuthAction):X.0DC");
    }

    @Inject
    public AppScopedTokenHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
    }
}
