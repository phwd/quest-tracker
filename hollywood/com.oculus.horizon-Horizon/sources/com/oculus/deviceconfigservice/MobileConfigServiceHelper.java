package com.oculus.deviceconfigservice;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rp;
import X.AnonymousClass0il;
import X.AnonymousClass0p1;
import X.AnonymousClass1Ch;
import X.C003008y;
import X.C01010Iv;
import X.C02870bf;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import com.facebook.inject.ForAppContext;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.locale.LocaleModule;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_deviceconfigservice_DeviceConfigCallerVerifier_ULSEP_BINDING_ID"})
public class MobileConfigServiceHelper implements AnonymousClass0Rp {
    public static final String TAG = "MobileConfigServiceHelper";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @ForAppContext
    public final AnonymousClass0p1<Context> mContextLazy;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    public final Provider<Locale> mLocaleProvider;
    @Inject
    public final Provider<OkTigonServiceHolder> mOkTigonServiceHolderProvider;
    @Inject
    public final AnonymousClass0p1<AnonymousClass0il> mXAnalyticsProvider;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x007d, code lost:
        if (r16.isEmpty() != false) goto L_0x007f;
     */
    @Override // X.AnonymousClass0Rp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass0RX A25(java.lang.String r16, java.lang.String r17) {
        /*
        // Method dump skipped, instructions count: 190
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigservice.MobileConfigServiceHelper.A25(java.lang.String, java.lang.String):X.0RX");
    }

    @Override // X.AnonymousClass0Rp
    public final void A8T(final Runnable runnable) {
        AnonymousClass0DC.A07(new Callable<Void>() {
            /* class com.oculus.deviceconfigservice.MobileConfigServiceHelper.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                runnable.run();
                return null;
            }
        }, AnonymousClass0DC.A0C, null);
    }

    @Override // X.AnonymousClass0Rp
    public final String getUserId() {
        if (this.mCredentialsProvider.get() != null) {
            return this.mCredentialsProvider.get().mUserId;
        }
        return "";
    }

    @Inject
    public MobileConfigServiceHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mContextLazy = new C003008y(294, r3);
        this.mXAnalyticsProvider = new C003008y(193, r3);
        this.mOkTigonServiceHolderProvider = new C01010Iv(107, r3);
        this.mLocaleProvider = LocaleModule.A01(r3);
    }

    @Override // X.AnonymousClass0Rp
    public final void A2U(Context context) {
        if (Process.myPid() != Binder.getCallingPid()) {
            String str = null;
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                str = packageManager.getNameForUid(Binder.getCallingUid());
            }
            DeviceConfigCallerVerifier deviceConfigCallerVerifier = (DeviceConfigCallerVerifier) AnonymousClass0J2.A03(0, 424, this._UL_mInjectionContext);
            if (!AnonymousClass1Ch.A00(deviceConfigCallerVerifier.mTrustedCallerVerifier).A03) {
                if (str == null) {
                    AnonymousClass0NO.A08(DeviceConfigCallerVerifier.TAG, "Package Name could not be determined");
                } else {
                    try {
                        PackageManager packageManager2 = deviceConfigCallerVerifier.mContextLazy.get().getPackageManager();
                        if (packageManager2 == null) {
                            AnonymousClass0NO.A08(DeviceConfigCallerVerifier.TAG, "PackageManager not found");
                        } else if ((packageManager2.getApplicationInfo(str, 0).flags & 1) != 1) {
                            AnonymousClass0NO.A0E(DeviceConfigCallerVerifier.TAG, "Package %s is NOT allowed as it is NOT a system app", str);
                        } else {
                            return;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                AnonymousClass0NO.A0E(TAG, "Package %s is not allowed to use DeviceConfig", str);
                throw new SecurityException(AnonymousClass006.A07("Access denied for DeviceConfig: package '", str, "' is not allowed"));
            }
        }
    }

    @Override // X.AnonymousClass0Rp
    @Nullable
    public final String A3D() {
        if (Process.myPid() == Binder.getCallingPid()) {
            return this.mContextLazy.get().getPackageName();
        }
        return C02870bf.A01(this.mContextLazy.get()).A01();
    }

    @Override // X.AnonymousClass0Rp
    public final boolean A5A() {
        String userId = getUserId();
        if (userId == null || userId.isEmpty()) {
            return false;
        }
        return true;
    }
}
