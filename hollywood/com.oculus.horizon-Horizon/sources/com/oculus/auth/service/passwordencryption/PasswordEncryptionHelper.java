package com.oculus.auth.service.passwordencryption;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.AnonymousClass1Dp;
import X.AnonymousClass1Dq;
import X.AnonymousClass1Ds;
import X.AnonymousClass1Du;
import X.AnonymousClass1E0;
import X.AnonymousClass1E1;
import X.C003008y;
import X.C01020Iw;
import android.os.Bundle;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_libraries_passwordencryption_PasswordEncryptionController_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_service_passwordencryption_PasswordEncryptionLogger_ULSEP_BINDING_ID"})
public class PasswordEncryptionHelper {
    public static final int MAX_ATTEMPTS = 3;
    public AnonymousClass0QC _UL_mInjectionContext;
    public int mAttemptTime = 0;

    public static AnonymousClass1Ds createPasswordDecryptionErrorFromBundle(@Nullable Bundle bundle) {
        int i;
        if (bundle == null) {
            i = -1;
        } else {
            i = bundle.getInt("error_subcode", -1);
        }
        if (i == -1) {
            return new AnonymousClass1Ds(2779004, null);
        }
        int i2 = bundle.getInt("key_id", -1);
        String string = bundle.getString("public_key");
        long j = bundle.getLong(ServiceContract.EXTRA_ENCRYPTION_KEY_PKG_SECONDS_TO_LIVE, -1);
        if (i2 == -1 || string == null || j == -1) {
            return new AnonymousClass1Ds(i, null);
        }
        return new AnonymousClass1Ds(i, new AnonymousClass1Dq(i2, string, j));
    }

    private void logDecryptionFailure(@Nullable Bundle bundle) {
        int i = -1;
        if (bundle != null) {
            i = bundle.getInt("error_subcode", -1);
        }
        ((PasswordEncryptionLogger) AnonymousClass0J2.A03(1, 359, this._UL_mInjectionContext)).logDecryptionEvent(PasswordEncryptionLogger.EVENT_DECRYPTION_FAILURE, i, this.mAttemptTime);
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_service_passwordencryption_PasswordEncryptionHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(42, r2);
    }

    @AutoGeneratedAccessMethod
    public static final PasswordEncryptionHelper _UL__ULSEP_com_oculus_auth_service_passwordencryption_PasswordEncryptionHelper_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (PasswordEncryptionHelper) AnonymousClass117.A00(42, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final PasswordEncryptionHelper _UL__ULSEP_com_oculus_auth_service_passwordencryption_PasswordEncryptionHelper_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new PasswordEncryptionHelper(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_service_passwordencryption_PasswordEncryptionHelper_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(42, r2);
    }

    private String encrypt(String str) {
        AnonymousClass1E1 A02 = AnonymousClass1Du.A02((AnonymousClass1Du) AnonymousClass0J2.A03(0, 572, this._UL_mInjectionContext), str, null);
        logEncryptionEvent(A02.A01);
        return A02.A00;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
        if (r2 == 2779005) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String encryptAfterDecryptionError(java.lang.String r5, X.AnonymousClass1Ds r6) {
        /*
            r4 = this;
            r2 = 572(0x23c, float:8.02E-43)
            X.0QC r1 = r4._UL_mInjectionContext
            r0 = 0
            java.lang.Object r3 = X.AnonymousClass0J2.A03(r0, r2, r1)
            X.1Du r3 = (X.AnonymousClass1Du) r3
            monitor-enter(r3)
            if (r6 == 0) goto L_0x0023
            int r2 = r6.A00     // Catch:{ all -> 0x0038 }
            r0 = 2779004(0x2a677c, float:3.894214E-39)
            if (r2 == r0) goto L_0x001b
            r1 = 2779005(0x2a677d, float:3.894215E-39)
            r0 = 0
            if (r2 != r1) goto L_0x001c
        L_0x001b:
            r0 = 1
        L_0x001c:
            if (r0 == 0) goto L_0x0023
            X.1E1 r1 = X.AnonymousClass1Du.A02(r3, r5, r6)     // Catch:{ all -> 0x0038 }
            goto L_0x002f
        L_0x0023:
            com.oculus.auth.service.passwordencryption.OculusPasswordEncryptionKeyStore r1 = r3.A00     // Catch:{ all -> 0x0038 }
            X.1Dq r0 = r6.A01     // Catch:{ all -> 0x0038 }
            r1.storeKey(r0)     // Catch:{ all -> 0x0038 }
            r0 = 0
            X.1E1 r1 = X.AnonymousClass1Du.A02(r3, r5, r0)     // Catch:{ all -> 0x0038 }
        L_0x002f:
            monitor-exit(r3)
            X.1E0 r0 = r1.A01
            r4.logEncryptionEvent(r0)
            java.lang.String r0 = r1.A00
            return r0
        L_0x0038:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.auth.service.passwordencryption.PasswordEncryptionHelper.encryptAfterDecryptionError(java.lang.String, X.1Ds):java.lang.String");
    }

    public boolean canEncryptAgain() {
        if (this.mAttemptTime < 3) {
            return true;
        }
        return false;
    }

    public String encryptPassword(String str, @Nullable Bundle bundle) {
        int i = this.mAttemptTime;
        if (i == 0) {
            str = encrypt(str);
        } else if (i != 1) {
            logDecryptionFailure(bundle);
            logEncryptionEvent((String) null);
        } else {
            str = encryptAfterDecryptionError(str, createPasswordDecryptionErrorFromBundle(bundle));
            logDecryptionFailure(bundle);
        }
        this.mAttemptTime++;
        return str;
    }

    public void triggerKeyFetching() {
        ((AnonymousClass1Du) AnonymousClass0J2.A03(0, 572, this._UL_mInjectionContext)).A03(AnonymousClass1Dp.APP_FOREGROUND);
    }

    @Inject
    public PasswordEncryptionHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    public int getAttemptTimes() {
        return this.mAttemptTime;
    }

    private void logEncryptionEvent(@Nullable String str) {
        ((PasswordEncryptionLogger) AnonymousClass0J2.A03(1, 359, this._UL_mInjectionContext)).logEncryptionEvent(str, this.mAttemptTime);
    }

    private void logEncryptionEvent(@Nullable AnonymousClass1E0 r2) {
        String obj;
        if (r2 == null) {
            obj = null;
        } else {
            obj = r2.mExceptionType.toString();
        }
        logEncryptionEvent(obj);
    }
}