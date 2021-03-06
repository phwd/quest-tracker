package com.oculus.http.core.interceptor;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0358df;
import X.C0359dg;
import X.C0366dn;
import X.C0515sp;
import X.HY;
import X.KC;
import X.M7;
import X.Mu;
import X.QC;
import X.ci;
import X.dZ;
import X.eJ;
import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.storage.AuthDatastore;
import com.oculus.auth.storage.StorageModule;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.base.app.AppInfo;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.os.DeviceAuth;
import com.oculus.util.constants.OculusConstants;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusAuthorizationInterceptor extends AuthorizationInterceptor {
    public static final String ACTION_AUTO_LOGOUT = "com.oculus.auth.ACTION_AUTO_LOGOUT";
    public static final long CREDENTIALS_UPDATE_WINDOW_MS = TimeUnit.SECONDS.toMillis(5);
    public static final long DSAT_FETCH_TIMEOUT_SECONDS = 30;
    public static final String EVENT_AUTH_ACTION = "oculus_mobile_auth_action";
    public static final String EXTRA_ACTION = "action";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_ERROR_MESSAGE = "error_message";
    public static final String EXTRA_ERROR_SUBCODE = "error_subcode";
    public static final String EXTRA_ERROR_TYPE = "error_type";
    public static final String EXTRA_ERROR_USER_MESSAGE = "error_user_message";
    public static final String EXTRA_ERROR_USER_TITLE = "error_user_title";
    public static final String TAG = "OculusAuthorizationInterceptor";
    public QC _UL_mInjectionContext;
    @Inject
    public final eJ<AuthDatastore> mAuthDatastoreProvider;
    @Inject
    public final eJ<Credentials> mCredentialsProvider;

    private String A02() {
        String str;
        try {
            str = ((DeviceAuth) AbstractC0096Hu.A03(1, 85, this._UL_mInjectionContext)).fetchToken(OculusConstants.ALPENGLOW_HW_LOGINTOKEN).value();
        } catch (DeviceAuth.BackendException | DeviceAuth.DeviceIdentityException | DeviceAuth.NetworkException | InterruptedException e) {
            Mu.A08(TAG, e, "Error fetching device auth token");
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return AccessTokenUtils.createLoggedOutToken((AppInfo) AbstractC0096Hu.A03(0, 111, this._UL_mInjectionContext), null);
        }
        return AccessTokenUtils.createLoggedOutToken((AppInfo) AbstractC0096Hu.A03(0, 111, this._UL_mInjectionContext), str);
    }

    @AutoGeneratedAccessMethod
    public static final OculusAuthorizationInterceptor A00(AbstractC0247Xu xu) {
        return (OculusAuthorizationInterceptor) C0515sp.A00(40, xu);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusAuthorizationInterceptor A01(AbstractC0247Xu xu) {
        return new OculusAuthorizationInterceptor(xu);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d6, code lost:
        if (android.text.TextUtils.isEmpty(r1) != false) goto L_0x00d8;
     */
    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0363dk A03(X.C0362dj r8) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 261
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.http.core.interceptor.OculusAuthorizationInterceptor.A03(X.dj):X.dk");
    }

    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public final void A04(C0359dg dgVar) {
        Charset charset;
        Charset charset2;
        ApiError.FBApiErrorResponse.Error error;
        if (Math.abs(System.currentTimeMillis() - this.mAuthDatastoreProvider.get().getCredentialsUpdateTimeMillis()) >= CREDENTIALS_UPDATE_WINDOW_MS) {
            AbstractC0358df dfVar = dgVar.A0B;
            KC A02 = dfVar.A02();
            try {
                C0366dn A01 = dfVar.A01();
                if (A01 != null) {
                    charset2 = dZ.A04;
                    charset = charset2;
                    String str = A01.A00;
                    if (str != null) {
                        charset = Charset.forName(str);
                    }
                } else {
                    charset = dZ.A04;
                    charset2 = charset;
                }
                ci ciVar = dZ.A0D;
                if (A02.A4P(0, ciVar)) {
                    A02.A5F((long) ciVar.A07());
                    charset = charset2;
                } else {
                    ci ciVar2 = dZ.A09;
                    if (A02.A4P(0, ciVar2)) {
                        A02.A5F((long) ciVar2.A07());
                        charset = dZ.A00;
                    } else {
                        ci ciVar3 = dZ.A0A;
                        if (A02.A4P(0, ciVar3)) {
                            A02.A5F((long) ciVar3.A07());
                            charset = dZ.A01;
                        } else {
                            ci ciVar4 = dZ.A0B;
                            if (A02.A4P(0, ciVar4)) {
                                A02.A5F((long) ciVar4.A07());
                                charset = dZ.A02;
                            } else {
                                ci ciVar5 = dZ.A0C;
                                if (A02.A4P(0, ciVar5)) {
                                    A02.A5F((long) ciVar5.A07());
                                    charset = dZ.A03;
                                }
                            }
                        }
                    }
                }
                String A4b = A02.A4b(charset);
                dZ.A06(A02);
                Mu.A05(TAG, "Logging user out, response: %s", A4b);
                ApiError.FBApiErrorResponse fBApiErrorResponse = (ApiError.FBApiErrorResponse) new HY().A06(A4b, ApiError.FBApiErrorResponse.class);
                Event A1h = ((EventManager) AbstractC0096Hu.A03(3, 106, this._UL_mInjectionContext)).A1h(EVENT_AUTH_ACTION);
                A1h.A18(EXTRA_ACTION, ACTION_AUTO_LOGOUT);
                if (!(fBApiErrorResponse == null || (error = fBApiErrorResponse.error) == null)) {
                    A1h.A17("error_code", error.code);
                    A1h.A17("error_subcode", error.error_subcode);
                    String str2 = error.type;
                    if (str2 != null) {
                        A1h.A18(EXTRA_ERROR_TYPE, str2);
                    }
                    String str3 = error.message;
                    if (str3 != null) {
                        A1h.A18("error_message", str3);
                    }
                    String str4 = error.error_user_title;
                    if (str4 != null) {
                        A1h.A18(EXTRA_ERROR_USER_TITLE, str4);
                    }
                    String str5 = error.error_user_msg;
                    if (str5 != null) {
                        A1h.A18(EXTRA_ERROR_USER_MESSAGE, str5);
                    }
                }
                A1h.A3Q();
            } catch (M7 | IOException e) {
                Mu.A09(TAG, e, "Error processing response");
            } catch (Throwable th) {
                dZ.A06(A02);
                throw th;
            }
            this.mAuthDatastoreProvider.get().clearCredentials();
        }
    }

    @Inject
    public OculusAuthorizationInterceptor(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(4, xu);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(xu);
        this.mAuthDatastoreProvider = StorageModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_storage_AuthDatastore_ULGT__ULSEP_ACCESS_METHOD(xu);
    }
}
