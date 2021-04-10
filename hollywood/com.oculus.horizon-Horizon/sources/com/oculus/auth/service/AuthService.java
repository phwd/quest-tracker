package com.oculus.auth.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.C02800bY;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.oculus.auth.service.contract.ServiceContract;
import javax.inject.Inject;

public class AuthService extends IntentService {
    public static final String TAG = "AuthService";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public AuthManager mAuthManager;
    @Inject
    @Eager
    public AuthTrustedAppVerifier mTrustedAppVerified;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, AuthService authService) {
        authService._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
        authService.mAuthManager = AuthManager._UL__ULSEP_com_oculus_auth_service_AuthManager_ULSEP_ACCESS_METHOD(r2);
        authService.mTrustedAppVerified = AuthTrustedAppVerifier._UL__ULSEP_com_oculus_auth_service_AuthTrustedAppVerifier_ULSEP_ACCESS_METHOD(r2);
    }

    @Deprecated
    public enum AuthResult {
        SUCCESS(1),
        INVALID_CREDENTIALS(-1),
        ANOMALOUS_LOGIN(-2),
        TWO_FACTOR_LOGIN_REQUIRED(-8),
        LOGIN_APPROVALS_NOT_IN_CHECKPOINT(-9),
        LOGIN_APPROVALS_INVALID_CODE(-10),
        LOGIN_APPROVALS_RATE_LIMITED(-11),
        LIMITED_LOGIN(-3),
        NETWORK_ERROR(-4),
        RATE_LIMITED(-5),
        HTTP_ERROR(-6),
        UNKNOWN_ERROR(-7);
        
        public final int mCode;

        /* access modifiers changed from: public */
        AuthResult(int i) {
            this.mCode = i;
        }

        public int getIntCode() {
            return this.mCode;
        }
    }

    public AuthService() {
        super(TAG);
    }

    public void onHandleIntent(Intent intent) {
        String str;
        String str2;
        if (intent == null) {
            str = TAG;
            str2 = "Intent is null";
        } else {
            String action = intent.getAction();
            if (action == null) {
                str = TAG;
                str2 = "Intent action is null";
            } else if (!isCallerAppTrusted(intent)) {
                str = TAG;
                str2 = "Called from untrusted app.";
            } else {
                switch (action.hashCode()) {
                    case -2087783214:
                        if (action.equals(ServiceContract.ACTION_FETCH_FB_INFO_FOR_ACCOUNT_LINKING)) {
                            this.mAuthManager.performFetchFbInfoForAccountLinking(intent);
                            return;
                        }
                        return;
                    case -1814046758:
                        if (action.equals(ServiceContract.ACTION_FB_CHECK_MACHINE_APPROVAL)) {
                            ((FacebookAuthManager) AnonymousClass0J2.A03(0, 561, this._UL_mInjectionContext)).performFBPollForApproval(intent);
                            return;
                        }
                        return;
                    case -1788110998:
                        if (action.equals(ServiceContract.ACTION_RESEND_LOGIN_APPROVAL_CODE)) {
                            this.mAuthManager.performResendLoginApprovalsCode(intent);
                            return;
                        }
                        return;
                    case -1610220923:
                        if (action.equals(ServiceContract.ACTION_LOGOUT)) {
                            this.mAuthManager.performLogout(intent);
                            return;
                        }
                        return;
                    case -1405373203:
                        if (action.equals(ServiceContract.ACTION_STATUS)) {
                            this.mAuthManager.performQueryLogin(intent);
                            return;
                        }
                        return;
                    case -1071712214:
                        if (action.equals(ServiceContract.ACTION_VERIFY_PIN)) {
                            this.mAuthManager.performVerifyPin(intent);
                            return;
                        }
                        return;
                    case -1069513663:
                        if (action.equals(ServiceContract.ACTION_FB_LOGIN)) {
                            ((FacebookAuthManager) AnonymousClass0J2.A03(0, 561, this._UL_mInjectionContext)).performFBLogin(intent);
                            return;
                        }
                        return;
                    case -37132669:
                        if (action.equals(ServiceContract.ACTION_LOGIN_WITH_FB_AUTH)) {
                            this.mAuthManager.performLoginWithFbAuth(intent);
                            return;
                        }
                        return;
                    case 220365097:
                        if (action.equals(ServiceContract.ACTION_LOGIN_API)) {
                            this.mAuthManager.performLogin(intent);
                            return;
                        }
                        return;
                    case 340339005:
                        if (action.equals(ServiceContract.ACTION_FB_RESEND_SMS)) {
                            ((FacebookAuthManager) AnonymousClass0J2.A03(0, 561, this._UL_mInjectionContext)).performFBResendSms(intent);
                            return;
                        }
                        return;
                    case 434578704:
                        if (action.equals(ServiceContract.ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE)) {
                            ((FacebookAuthManager) AnonymousClass0J2.A03(0, 561, this._UL_mInjectionContext)).performFBVerifyLoginApprovalCode(intent);
                            return;
                        }
                        return;
                    case 480452461:
                        if (action.equals(ServiceContract.ACTION_FB_MACHINE_APPROVAL_LOGIN)) {
                            ((FacebookAuthManager) AnonymousClass0J2.A03(0, 561, this._UL_mInjectionContext)).performFBMachineApprovalLogin(intent);
                            return;
                        }
                        return;
                    case 873193662:
                        if (action.equals(ServiceContract.ACTION_VERIFY_LOGIN)) {
                            this.mAuthManager.performVerifyLogin(intent);
                            return;
                        }
                        return;
                    case 910704694:
                        if (action.equals(ServiceContract.ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS)) {
                            this.mAuthManager.performFetchDeviceScopedCredentials(intent);
                            return;
                        }
                        return;
                    case 1179106912:
                        if (action.equals(ServiceContract.ACTION_AUTH_WITH_OCULUS_EMAIL_AND_PASSWORD_FOR_ACCOUNT_LINKING)) {
                            this.mAuthManager.performAuthWithOculusEmailAndPasswordForAccountLinking(intent);
                            return;
                        }
                        return;
                    case 1206680178:
                        if (action.equals(ServiceContract.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS)) {
                            this.mAuthManager.performFetchHorizonDeviceScopedCredentials(intent);
                            return;
                        }
                        return;
                    case 1411596012:
                        if (action.equals(ServiceContract.ACTION_ENSURE_DEVICE_OWNERSHIP)) {
                            this.mAuthManager.performEnsureDeviceOwnership(intent);
                            return;
                        }
                        return;
                    case 1470279871:
                        if (action.equals(ServiceContract.ACTION_VERIFY_LOGIN_FOR_ACCOUNT_LINKING)) {
                            this.mAuthManager.performVerifyLoginForAccountLinking(intent);
                            return;
                        }
                        return;
                    case 1782077518:
                        if (action.equals(ServiceContract.ACTION_SEND_TWO_FACTOR_CODE)) {
                            this.mAuthManager.performSendTwoFactorCode(intent);
                            return;
                        }
                        return;
                    case 1939148884:
                        if (action.equals(ServiceContract.ACTION_LINK_FB_ACCOUNT)) {
                            ((FacebookAuthManager) AnonymousClass0J2.A03(0, 561, this._UL_mInjectionContext)).performFBLinkAccount(intent);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
        AnonymousClass0NO.A08(str, str2);
    }

    public static final void _UL_injectMe(Context context, AuthService authService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), authService);
    }

    private boolean isCallerAppTrusted(Intent intent) {
        return this.mTrustedAppVerified.isTrusted(C02800bY.A00(this, intent));
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }
}
