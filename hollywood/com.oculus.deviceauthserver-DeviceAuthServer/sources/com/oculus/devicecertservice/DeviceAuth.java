package com.oculus.devicecertservice;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.oculus.devicecertservice.DeviceAuth;
import com.oculus.devicecertservice.DeviceCertInterface;
import com.oculus.restwrapper.ErrorCallback;
import com.oculus.restwrapper.RestWrapper;

public class DeviceAuth {
    private static final String ALIAS = "device_identity";
    private static final int HARDWARE_AUTH__DEVICE_NOT_REGISTERED = 2699001;
    private static final int HARDWARE_AUTH__REAUTH_REQUIRED = 2699012;
    private static final int MAX_RETRIES = 3;
    private static final String TAG = "DeviceAuth";
    private String appAccessToken;
    private String currentCertPem;
    private DeviceCert deviceCert;
    private RestWrapper restWrapper;

    public interface AccessTokenCallback {
        void call(String str, int i, String str2);
    }

    public static final class SignatureAlgorithm {
        public static final String SHA256 = "sha256WithRSAEncryption";
        public static final String SHA384 = "sha384WithRSAEncryption";
        public static final String SHA512 = "sha512WithRSAEncryption";

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0043 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String toDeviceCertFormat(java.lang.String r4) {
            /*
                int r0 = r4.hashCode()
                r1 = -1366527245(0xffffffffae8c72f3, float:-6.386882E-11)
                r2 = 2
                r3 = 1
                if (r0 == r1) goto L_0x002a
                r1 = 135607994(0x81536ba, float:4.4902387E-34)
                if (r0 == r1) goto L_0x0020
                r1 = 1083703126(0x4097ff56, float:4.749919)
                if (r0 == r1) goto L_0x0016
            L_0x0015:
                goto L_0x0034
            L_0x0016:
                java.lang.String r0 = "sha256WithRSAEncryption"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x0015
                r0 = 0
                goto L_0x0035
            L_0x0020:
                java.lang.String r0 = "sha384WithRSAEncryption"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x0015
                r0 = r3
                goto L_0x0035
            L_0x002a:
                java.lang.String r0 = "sha512WithRSAEncryption"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x0015
                r0 = r2
                goto L_0x0035
            L_0x0034:
                r0 = -1
            L_0x0035:
                if (r0 == 0) goto L_0x0043
                if (r0 == r3) goto L_0x0040
                if (r0 == r2) goto L_0x003d
                r0 = 0
                return r0
            L_0x003d:
                java.lang.String r0 = "SHA512withRSA"
                return r0
            L_0x0040:
                java.lang.String r0 = "SHA384withRSA"
                return r0
            L_0x0043:
                java.lang.String r0 = "SHA256withRSA"
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.devicecertservice.DeviceAuth.SignatureAlgorithm.toDeviceCertFormat(java.lang.String):java.lang.String");
        }
    }

    private class Request {
        public String access_token;
        public int auth_protocol_version;

        private Request() {
            this.auth_protocol_version = 1;
        }
    }

    /* access modifiers changed from: private */
    public class RegisterRequest extends Request {
        public String[] device_certificates;

        private RegisterRequest() {
            super();
        }
    }

    /* access modifiers changed from: private */
    public class RegisterResponse {
        public boolean success;

        private RegisterResponse() {
        }
    }

    /* access modifiers changed from: private */
    public class ChallengeRequest extends Request {
        public String device_certificate;
        public String[] supported_algorithms;

        private ChallengeRequest() {
            super();
            this.supported_algorithms = new String[]{SignatureAlgorithm.SHA256, SignatureAlgorithm.SHA384, SignatureAlgorithm.SHA512};
        }
    }

    /* access modifiers changed from: private */
    public class ChallengeResponse {
        public String challenge;
        public String signature_algorithm;

        private ChallengeResponse() {
        }
    }

    /* access modifiers changed from: private */
    public class LoginRequest extends Request {
        public String challenge;
        public String device_certificate;
        public String existing_device_ent_id;
        public String signed_challenge;

        private LoginRequest() {
            super();
        }
    }

    /* access modifiers changed from: private */
    public class LoginResponse {
        public String access_token;
        public String device_ent_id;
        public int session_ttl;

        private LoginResponse() {
        }
    }

    /* access modifiers changed from: private */
    public class LoginErrorData {
        public String new_device_ent_id;

        private LoginErrorData() {
        }
    }

    public DeviceAuth(Context context) {
        this(context, null);
    }

    public DeviceAuth(Context context, String sandbox) {
        this.currentCertPem = null;
        this.deviceCert = new DeviceCert();
        String host = "graph.facebook-hardware.com";
        if (sandbox != null) {
            host = "graph." + sandbox + ".facebook-hardware.com";
        }
        this.restWrapper = new RestWrapper(context, host, 443);
    }

    private String getCurrentCertPem() throws DeviceIdentityException {
        if (this.currentCertPem == null) {
            this.currentCertPem = getCertPem(DeviceCertInterface.SecureState.CURRENT);
        }
        return this.currentCertPem;
    }

    public void authenticate(String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback) {
        loginRequest(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback, 3);
    }

    private void loginRequest(String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback, int retryLimit) {
        Exception ex;
        try {
            ChallengeRequest request = new ChallengeRequest();
            try {
                request.access_token = appAccessToken2;
                request.device_certificate = getCurrentCertPem();
                this.restWrapper.send("/login_request", ChallengeResponse.class, request, new RestWrapper.Callback(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback, retryLimit) {
                    /* class com.oculus.devicecertservice.$$Lambda$DeviceAuth$SDcU05CHUiKoQmLlNIzf2xZ8ELo */
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ DeviceAuth.AccessTokenCallback f$3;
                    private final /* synthetic */ DeviceAuthErrorCallback f$4;
                    private final /* synthetic */ int f$5;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                        this.f$5 = r6;
                    }

                    @Override // com.oculus.restwrapper.RestWrapper.Callback
                    public final void call(Object obj) {
                        DeviceAuth.this.lambda$loginRequest$0$DeviceAuth(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (DeviceAuth.ChallengeResponse) obj);
                    }
                }, wrapError(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback));
            } catch (Exception e) {
                ex = e;
                errorCallback.call(ex.getMessage(), ex);
            }
        } catch (Exception e2) {
            ex = e2;
            errorCallback.call(ex.getMessage(), ex);
        }
    }

    private void register(String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback) {
        try {
            RegisterRequest request = new RegisterRequest();
            request.access_token = appAccessToken2;
            request.device_certificates = new String[]{getCertPem(DeviceCertInterface.SecureState.INSECURE), getCertPem(DeviceCertInterface.SecureState.SECURE)};
            this.restWrapper.send("/register", RegisterResponse.class, request, new RestWrapper.Callback(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback) {
                /* class com.oculus.devicecertservice.$$Lambda$DeviceAuth$ny3PKu3ZR6MGKsmQi4AKLGdepM */
                private final /* synthetic */ String f$1;
                private final /* synthetic */ String f$2;
                private final /* synthetic */ DeviceAuth.AccessTokenCallback f$3;
                private final /* synthetic */ DeviceAuthErrorCallback f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                @Override // com.oculus.restwrapper.RestWrapper.Callback
                public final void call(Object obj) {
                    DeviceAuth.this.lambda$register$1$DeviceAuth(this.f$1, this.f$2, this.f$3, this.f$4, (DeviceAuth.RegisterResponse) obj);
                }
            }, passthroughError(errorCallback));
        } catch (Exception ex) {
            errorCallback.call(ex.getMessage(), ex);
        }
    }

    public /* synthetic */ void lambda$register$1$DeviceAuth(String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback, RegisterResponse r) {
        loginRequest(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback, 3);
    }

    /* access modifiers changed from: private */
    /* renamed from: handleChallengeResponse */
    public void lambda$loginRequest$0$DeviceAuth(ChallengeResponse response, String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback, int retryLimit) {
        try {
            byte[] challengeBytes = response.challenge.getBytes("UTF-8");
            String signatureAlgorithm = SignatureAlgorithm.toDeviceCertFormat(response.signature_algorithm);
            if (signatureAlgorithm != null) {
                String signedChallenge = Base64.encodeToString(this.deviceCert.sign(ALIAS, signatureAlgorithm, challengeBytes), 2);
                LoginRequest request = new LoginRequest();
                request.access_token = appAccessToken2;
                request.device_certificate = getCurrentCertPem();
                request.challenge = response.challenge;
                request.signed_challenge = signedChallenge;
                request.existing_device_ent_id = existingDeviceEntId != null ? existingDeviceEntId : "0";
                this.restWrapper.send("/login", LoginResponse.class, LoginErrorData.class, request, new RestWrapper.Callback() {
                    /* class com.oculus.devicecertservice.$$Lambda$DeviceAuth$quL93emxRmSl9kJBcdgCnbfeKOk */

                    @Override // com.oculus.restwrapper.RestWrapper.Callback
                    public final void call(Object obj) {
                        DeviceAuth.LoginResponse loginResponse;
                        DeviceAuth.AccessTokenCallback.this.call(loginResponse.access_token, loginResponse.session_ttl, ((DeviceAuth.LoginResponse) obj).device_ent_id);
                    }
                }, handleLoginError(appAccessToken2, tokenCallback, errorCallback, retryLimit));
                return;
            }
            throw new AssertionError("Received unsupported signature algorithm from server");
        } catch (Exception ex) {
            errorCallback.call(ex.getMessage(), ex);
        }
    }

    private ErrorCallback<LoginErrorData> handleLoginError(String appAccessToken2, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback, int retryLimit) {
        return new ErrorCallback(retryLimit, appAccessToken2, tokenCallback, errorCallback) {
            /* class com.oculus.devicecertservice.$$Lambda$DeviceAuth$HNy9ZVWstmvG8ThpxpE8mf1D7l8 */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ DeviceAuth.AccessTokenCallback f$3;
            private final /* synthetic */ DeviceAuthErrorCallback f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // com.oculus.restwrapper.ErrorCallback
            public final void call(String str, Exception exc, int i, Object obj) {
                DeviceAuth.this.lambda$handleLoginError$3$DeviceAuth(this.f$1, this.f$2, this.f$3, this.f$4, str, exc, i, (DeviceAuth.LoginErrorData) obj);
            }
        };
    }

    public /* synthetic */ void lambda$handleLoginError$3$DeviceAuth(int retryLimit, String appAccessToken2, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback, String errorMessage, Exception exception, int errorSubcode, LoginErrorData errorData) {
        if (errorSubcode != HARDWARE_AUTH__REAUTH_REQUIRED || errorData == null || TextUtils.isEmpty(errorData.new_device_ent_id) || retryLimit <= 0) {
            errorCallback.call(errorMessage, exception);
            return;
        }
        Log.i(TAG, "Received HARDWARE_AUTH__REAUTH_REQUIRED, re-trying... (" + ((3 - retryLimit) + 1) + "/" + 3 + ")");
        loginRequest(appAccessToken2, errorData.new_device_ent_id, tokenCallback, errorCallback, retryLimit + -1);
    }

    private String getCertPem(DeviceCertInterface.SecureState secureState) throws DeviceIdentityException {
        try {
            return Base64.encodeToString(this.deviceCert.loadCertificate(ALIAS, secureState).getEncoded(), 0);
        } catch (Exception ex) {
            throw new DeviceIdentityException(ex);
        }
    }

    private ErrorCallback<Void> wrapError(String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback) {
        return new ErrorCallback(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback) {
            /* class com.oculus.devicecertservice.$$Lambda$DeviceAuth$Paxc6FAKgviPMfRbiEAuFxTtd38 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ DeviceAuth.AccessTokenCallback f$3;
            private final /* synthetic */ DeviceAuthErrorCallback f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // com.oculus.restwrapper.ErrorCallback
            public final void call(String str, Exception exc, int i, Object obj) {
                DeviceAuth.this.lambda$wrapError$4$DeviceAuth(this.f$1, this.f$2, this.f$3, this.f$4, str, exc, i, (Void) obj);
            }
        };
    }

    public /* synthetic */ void lambda$wrapError$4$DeviceAuth(String appAccessToken2, String existingDeviceEntId, AccessTokenCallback tokenCallback, DeviceAuthErrorCallback errorCallback, String errorMessage, Exception exception, int errorSubcode, Void errorData) {
        if (errorSubcode == HARDWARE_AUTH__DEVICE_NOT_REGISTERED) {
            register(appAccessToken2, existingDeviceEntId, tokenCallback, errorCallback);
        } else {
            errorCallback.call(errorMessage, exception);
        }
    }

    private ErrorCallback<Void> passthroughError(DeviceAuthErrorCallback errorCallback) {
        return new ErrorCallback() {
            /* class com.oculus.devicecertservice.$$Lambda$DeviceAuth$JgCkLMNxeudHMILoN7PacJdsC8 */

            @Override // com.oculus.restwrapper.ErrorCallback
            public final void call(String str, Exception exc, int i, Object obj) {
                Void r5 = (Void) obj;
                DeviceAuthErrorCallback.this.call(str, exc);
            }
        };
    }
}
