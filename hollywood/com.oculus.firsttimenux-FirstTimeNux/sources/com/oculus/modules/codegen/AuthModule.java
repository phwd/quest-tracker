package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AuthModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = AuthModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void authWithOculusEmailAndPasswordForAccountLinkingImpl(String str, String str2, Resolver<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void checkFbMachineApprovalImpl(String str, String str2, Resolver<AuthCheckFbMachineApprovalResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void completeFbLoginAfterMachineApprovalImpl(String str, String str2, String str3, Resolver<AuthFbLoginResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void completeFbLoginWithTwoFactorCodeImpl(String str, String str2, String str3, String str4, String str5, Resolver<AuthFbLoginResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void ensureDeviceOwnershipImpl(Resolver<AuthResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchDeviceScopedCredentialsImpl(Resolver<CredentialsResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchFbInfoForAccountLinkingImpl(String str, Resolver<AuthFetchFbInfoForAccountLinkingResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void linkFbAccountImpl(String str, String str2, Resolver<AuthFbLinkResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void loginToFbImpl(String str, String str2, Resolver<AuthFbLoginResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void loginWithEmailAndPasswordImpl(String str, String str2, Resolver<AuthLoginResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void loginWithFbAuthImpl(String str, Resolver<AuthLoginWithFbAuthResult> resolver);

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeAccountConflict();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeAnomalousLogin();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeCannotMergeAccountsBecauseAlreadyLinked();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeDeviceAlreadyClaimed();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeHttpError();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeInvalidCreds();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeLoginApprovalsInvalidCode();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeNetworkError();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeNoDeviceIdentity();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeNoLinkedAccount();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeRateLimited();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeTwoFactorLoginRequired();

    /* access modifiers changed from: protected */
    public abstract double marshallJSConstantErrorCodeUserAlreadyOnDevice();

    /* access modifiers changed from: protected */
    public abstract void reloginWithEmailAndPasswordImpl(String str, String str2, Resolver<AuthLoginResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void reloginWithFbAuthImpl(String str, Resolver<AuthLoginWithFbAuthResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void sendTwoFactorCodeImpl(String str, String str2, Resolver<AuthSendTwoFactorCodeResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void statusImpl(Resolver<AuthStatusResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void verifyLoginForAccountLinkingImpl(String str, String str2, String str3, String str4, Resolver<AuthVerifyLoginForAccountLinkingResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void verifyLoginImpl(String str, String str2, String str3, String str4, boolean z, Resolver<AuthVerifyLoginResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void verifyReloginImpl(String str, String str2, String str3, String str4, boolean z, Resolver<AuthVerifyLoginResult> resolver);

    public AuthModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("authWithOculusEmailAndPasswordForAccountLinking", "+ssii"));
        list.add(new Pair<>("checkFbMachineApproval", "+ssii"));
        list.add(new Pair<>("completeFbLoginAfterMachineApproval", "+sssii"));
        list.add(new Pair<>("completeFbLoginWithTwoFactorCode", "+sssssii"));
        list.add(new Pair<>("ensureDeviceOwnership", "+ii"));
        list.add(new Pair<>("fetchDeviceScopedCredentials", "+ii"));
        list.add(new Pair<>("fetchFbInfoForAccountLinking", "+sii"));
        list.add(new Pair<>("linkFbAccount", "+ssii"));
        list.add(new Pair<>("loginToFb", "+ssii"));
        list.add(new Pair<>("loginWithEmailAndPassword", "+ssii"));
        list.add(new Pair<>("loginWithFbAuth", "+sii"));
        list.add(new Pair<>("reloginWithEmailAndPassword", "+ssii"));
        list.add(new Pair<>("reloginWithFbAuth", "+sii"));
        list.add(new Pair<>("sendTwoFactorCode", "+ssii"));
        list.add(new Pair<>("status", "+ii"));
        list.add(new Pair<>("verifyLogin", "+ssssbii"));
        list.add(new Pair<>("verifyLoginForAccountLinking", "+ssssii"));
        list.add(new Pair<>("verifyRelogin", "+ssssbii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("ERROR_CODE_ACCOUNT_CONFLICT", marshallJSConstantErrorCodeAccountConflict());
            result.put("ERROR_CODE_ANOMALOUS_LOGIN", marshallJSConstantErrorCodeAnomalousLogin());
            result.put("ERROR_CODE_CANNOT_MERGE_ACCOUNTS_BECAUSE_ALREADY_LINKED", marshallJSConstantErrorCodeCannotMergeAccountsBecauseAlreadyLinked());
            result.put("ERROR_CODE_DEVICE_ALREADY_CLAIMED", marshallJSConstantErrorCodeDeviceAlreadyClaimed());
            result.put("ERROR_CODE_HTTP_ERROR", marshallJSConstantErrorCodeHttpError());
            result.put("ERROR_CODE_INVALID_CREDS", marshallJSConstantErrorCodeInvalidCreds());
            result.put("ERROR_CODE_LOGIN_APPROVALS_INVALID_CODE", marshallJSConstantErrorCodeLoginApprovalsInvalidCode());
            result.put("ERROR_CODE_NETWORK_ERROR", marshallJSConstantErrorCodeNetworkError());
            result.put("ERROR_CODE_NO_DEVICE_IDENTITY", marshallJSConstantErrorCodeNoDeviceIdentity());
            result.put("ERROR_CODE_NO_LINKED_ACCOUNT", marshallJSConstantErrorCodeNoLinkedAccount());
            result.put("ERROR_CODE_RATE_LIMITED", marshallJSConstantErrorCodeRateLimited());
            result.put("ERROR_CODE_TWO_FACTOR_LOGIN_REQUIRED", marshallJSConstantErrorCodeTwoFactorLoginRequired());
            result.put("ERROR_CODE_USER_ALREADY_ON_DEVICE", marshallJSConstantErrorCodeUserAlreadyOnDevice());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final void emitOnLogin(JSONObject data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "AuthModule_onLogin", String.valueOf(data));
    }

    /* access modifiers changed from: protected */
    public final void authWithOculusEmailAndPasswordForAccountLinking(String email, String password, int resolveID, int rejectID) {
        authWithOculusEmailAndPasswordForAccountLinkingImpl(email, password, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void checkFbMachineApproval(String fbUserId, String machineId, int resolveID, int rejectID) {
        checkFbMachineApprovalImpl(fbUserId, machineId, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void completeFbLoginAfterMachineApproval(String fbUserId, String machineId, String authToken, int resolveID, int rejectID) {
        completeFbLoginAfterMachineApprovalImpl(fbUserId, machineId, authToken, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void completeFbLoginWithTwoFactorCode(String email, String fbUserId, String machineId, String firstFactor, String twoFactorCode, int resolveID, int rejectID) {
        completeFbLoginWithTwoFactorCodeImpl(email, fbUserId, machineId, firstFactor, twoFactorCode, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void ensureDeviceOwnership(int resolveID, int rejectID) {
        ensureDeviceOwnershipImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchDeviceScopedCredentials(int resolveID, int rejectID) {
        fetchDeviceScopedCredentialsImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchFbInfoForAccountLinking(String fbAccessToken, int resolveID, int rejectID) {
        fetchFbInfoForAccountLinkingImpl(fbAccessToken, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void linkFbAccount(String fbUserId, String fbAccessToken, int resolveID, int rejectID) {
        linkFbAccountImpl(fbUserId, fbAccessToken, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void loginToFb(String emailOrPhone, String password, int resolveID, int rejectID) {
        loginToFbImpl(emailOrPhone, password, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void loginWithEmailAndPassword(String email, String password, int resolveID, int rejectID) {
        loginWithEmailAndPasswordImpl(email, password, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void loginWithFbAuth(String fbAccessToken, int resolveID, int rejectID) {
        loginWithFbAuthImpl(fbAccessToken, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void reloginWithEmailAndPassword(String email, String password, int resolveID, int rejectID) {
        reloginWithEmailAndPasswordImpl(email, password, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void reloginWithFbAuth(String fbAccessToken, int resolveID, int rejectID) {
        reloginWithFbAuthImpl(fbAccessToken, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void sendTwoFactorCode(String nonce, String methodId, int resolveID, int rejectID) {
        sendTwoFactorCodeImpl(nonce, methodId, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void status(int resolveID, int rejectID) {
        statusImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void verifyLogin(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, int resolveID, int rejectID) {
        verifyLoginImpl(nonce, pin, methodId, fbAccessToken, isSecuredAction, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void verifyLoginForAccountLinking(String nonce, String pin, String methodId, String fbAccessToken, int resolveID, int rejectID) {
        verifyLoginForAccountLinkingImpl(nonce, pin, methodId, fbAccessToken, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void verifyRelogin(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, int resolveID, int rejectID) {
        verifyReloginImpl(nonce, pin, methodId, fbAccessToken, isSecuredAction, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule, com.oculus.panellib.modules.EarlyDestroyable
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }

    public static class AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult extends NativeModuleParcel {
        public String accessToken;
        public AuthLoginError error;
        public String userId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.accessToken != null) {
                    parcel.put("accessToken", this.accessToken);
                }
                if (this.error != null) {
                    parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
                }
                if (this.userId != null) {
                    parcel.put("userId", this.userId);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.accessToken = json.isNull("accessToken") ? null : json.optString("accessToken");
            this.error = json.isNull("error") ? null : AuthLoginError.makeFromJSONObject(json.optJSONObject("error"));
            if (!json.isNull("userId")) {
                str = json.optString("userId");
            }
            this.userId = str;
        }

        public static final AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult result = new AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthLoginError extends NativeModuleParcel {
        public double code;
        public String message;
        public String methodId;
        public String nonce;
        public String title;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("code", this.code);
                parcel.put("message", this.message);
                parcel.put("methodId", this.methodId);
                parcel.put(ServiceContract.EXTRA_NONCE, this.nonce);
                parcel.put("title", this.title);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.code = json.optDouble("code", 0.0d);
            this.message = json.isNull("message") ? null : json.optString("message");
            this.methodId = json.optString("methodId");
            this.nonce = json.isNull(ServiceContract.EXTRA_NONCE) ? null : json.optString(ServiceContract.EXTRA_NONCE);
            if (!json.isNull("title")) {
                str = json.optString("title");
            }
            this.title = str;
        }

        public static final AuthLoginError makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthLoginError result = new AuthLoginError();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthCheckFbMachineApprovalResult extends NativeModuleParcel {
        public Boolean approved;
        public AuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("approved", this.approved);
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            AuthError authError = null;
            this.approved = json.isNull("approved") ? null : Boolean.valueOf(json.optBoolean("approved"));
            if (!json.isNull("error")) {
                authError = AuthError.makeFromJSONObject(json.optJSONObject("error"));
            }
            this.error = authError;
        }

        public static final AuthCheckFbMachineApprovalResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthCheckFbMachineApprovalResult result = new AuthCheckFbMachineApprovalResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthError extends NativeModuleParcel {
        public double code;
        public String message;
        public String methodId;
        public String nonce;
        public String title;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("code", this.code);
                parcel.put("message", this.message);
                parcel.put("methodId", this.methodId);
                parcel.put(ServiceContract.EXTRA_NONCE, this.nonce);
                parcel.put("title", this.title);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.code = json.optDouble("code", 0.0d);
            this.message = json.isNull("message") ? null : json.optString("message");
            this.methodId = json.optString("methodId");
            this.nonce = json.isNull(ServiceContract.EXTRA_NONCE) ? null : json.optString(ServiceContract.EXTRA_NONCE);
            if (!json.isNull("title")) {
                str = json.optString("title");
            }
            this.title = str;
        }

        public static final AuthError makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthError result = new AuthError();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthFbLoginResult extends NativeModuleParcel {
        public AuthFbLoginError error;
        public String fbAccessToken;
        public String fbUserId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
                parcel.put("fbAccessToken", this.fbAccessToken);
                parcel.put("fbUserId", this.fbUserId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.error = json.isNull("error") ? null : AuthFbLoginError.makeFromJSONObject(json.optJSONObject("error"));
            this.fbAccessToken = json.isNull("fbAccessToken") ? null : json.optString("fbAccessToken");
            if (!json.isNull("fbUserId")) {
                str = json.optString("fbUserId");
            }
            this.fbUserId = str;
        }

        public static final AuthFbLoginResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthFbLoginResult result = new AuthFbLoginResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthFbLoginError extends NativeModuleParcel {
        public String authToken;
        public double code;
        public String fbUserId;
        public String loginFirstFactor;
        public String machineId;
        public String message;
        public String methodId;
        public String nonce;
        public String title;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("code", this.code);
                parcel.put("message", this.message);
                parcel.put("methodId", this.methodId);
                parcel.put(ServiceContract.EXTRA_NONCE, this.nonce);
                parcel.put("title", this.title);
                parcel.put("authToken", this.authToken);
                parcel.put("fbUserId", this.fbUserId);
                parcel.put("loginFirstFactor", this.loginFirstFactor);
                parcel.put("machineId", this.machineId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.code = json.optDouble("code", 0.0d);
            this.message = json.isNull("message") ? null : json.optString("message");
            this.methodId = json.optString("methodId");
            this.nonce = json.isNull(ServiceContract.EXTRA_NONCE) ? null : json.optString(ServiceContract.EXTRA_NONCE);
            this.title = json.isNull("title") ? null : json.optString("title");
            this.authToken = json.isNull("authToken") ? null : json.optString("authToken");
            this.fbUserId = json.isNull("fbUserId") ? null : json.optString("fbUserId");
            this.loginFirstFactor = json.isNull("loginFirstFactor") ? null : json.optString("loginFirstFactor");
            if (!json.isNull("machineId")) {
                str = json.optString("machineId");
            }
            this.machineId = str;
        }

        public static final AuthFbLoginError makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthFbLoginError result = new AuthFbLoginError();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthResult extends NativeModuleParcel {
        public AuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.error = json.isNull("error") ? null : AuthError.makeFromJSONObject(json.optJSONObject("error"));
        }

        public static final AuthResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthResult result = new AuthResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class CredentialsResult extends NativeModuleParcel {
        public String accessToken;
        public AuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("accessToken", this.accessToken);
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            AuthError authError = null;
            this.accessToken = json.isNull("accessToken") ? null : json.optString("accessToken");
            if (!json.isNull("error")) {
                authError = AuthError.makeFromJSONObject(json.optJSONObject("error"));
            }
            this.error = authError;
        }

        public static final CredentialsResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            CredentialsResult result = new CredentialsResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthFetchFbInfoForAccountLinkingResult extends NativeModuleParcel {
        public String email;
        public AuthFetchFbInfoForAccountLinkingError error;
        public String name;
        public String profilePicUri;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.email != null) {
                    parcel.put(ServiceContract.EXTRA_EMAIL, this.email);
                }
                if (this.error != null) {
                    parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
                }
                if (this.name != null) {
                    parcel.put("name", this.name);
                }
                if (this.profilePicUri != null) {
                    parcel.put("profilePicUri", this.profilePicUri);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.email = json.isNull(ServiceContract.EXTRA_EMAIL) ? null : json.optString(ServiceContract.EXTRA_EMAIL);
            this.error = json.isNull("error") ? null : AuthFetchFbInfoForAccountLinkingError.makeFromJSONObject(json.optJSONObject("error"));
            this.name = json.isNull("name") ? null : json.optString("name");
            if (!json.isNull("profilePicUri")) {
                str = json.optString("profilePicUri");
            }
            this.profilePicUri = str;
        }

        public static final AuthFetchFbInfoForAccountLinkingResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthFetchFbInfoForAccountLinkingResult result = new AuthFetchFbInfoForAccountLinkingResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthFetchFbInfoForAccountLinkingError extends NativeModuleParcel {
        public String accessToken;
        public double code;
        public String message;
        public String methodId;
        public String nonce;
        public String title;
        public String userId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("code", this.code);
                parcel.put("message", this.message);
                parcel.put("methodId", this.methodId);
                parcel.put(ServiceContract.EXTRA_NONCE, this.nonce);
                parcel.put("title", this.title);
                parcel.put("accessToken", this.accessToken);
                parcel.put("userId", this.userId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.code = json.optDouble("code", 0.0d);
            this.message = json.isNull("message") ? null : json.optString("message");
            this.methodId = json.optString("methodId");
            this.nonce = json.isNull(ServiceContract.EXTRA_NONCE) ? null : json.optString(ServiceContract.EXTRA_NONCE);
            this.title = json.isNull("title") ? null : json.optString("title");
            this.accessToken = json.isNull("accessToken") ? null : json.optString("accessToken");
            if (!json.isNull("userId")) {
                str = json.optString("userId");
            }
            this.userId = str;
        }

        public static final AuthFetchFbInfoForAccountLinkingError makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthFetchFbInfoForAccountLinkingError result = new AuthFetchFbInfoForAccountLinkingError();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthFbLinkResult extends NativeModuleParcel {
        public AuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.error = json.isNull("error") ? null : AuthError.makeFromJSONObject(json.optJSONObject("error"));
        }

        public static final AuthFbLinkResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthFbLinkResult result = new AuthFbLinkResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthLoginResult extends NativeModuleParcel {
        public AuthLoginError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.error = json.isNull("error") ? null : AuthLoginError.makeFromJSONObject(json.optJSONObject("error"));
        }

        public static final AuthLoginResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthLoginResult result = new AuthLoginResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthLoginWithFbAuthResult extends NativeModuleParcel {
        public AuthLoginWithFbAuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.error = json.isNull("error") ? null : AuthLoginWithFbAuthError.makeFromJSONObject(json.optJSONObject("error"));
        }

        public static final AuthLoginWithFbAuthResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthLoginWithFbAuthResult result = new AuthLoginWithFbAuthResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthLoginWithFbAuthError extends NativeModuleParcel {
        public List<String> allEmails;
        public double code;
        public String email;
        public String existingEmailConflict;
        public String fbAccessToken;
        public String fbUserId;
        public String message;
        public String methodId;
        public String name;
        public String nonce;
        public String profilePicUri;
        public String title;
        public List<String> usernameSuggestions;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("code", this.code);
                parcel.put("message", this.message);
                parcel.put("methodId", this.methodId);
                parcel.put(ServiceContract.EXTRA_NONCE, this.nonce);
                parcel.put("title", this.title);
                parcel.put("allEmails", this.allEmails == null ? JSONObject.NULL : NativeModuleParcel.convertStringListToJSONArray(this.allEmails));
                parcel.put(ServiceContract.EXTRA_EMAIL, this.email);
                parcel.put("existingEmailConflict", this.existingEmailConflict);
                parcel.put("fbAccessToken", this.fbAccessToken);
                parcel.put("fbUserId", this.fbUserId);
                parcel.put("name", this.name);
                parcel.put("profilePicUri", this.profilePicUri);
                parcel.put("usernameSuggestions", this.usernameSuggestions == null ? JSONObject.NULL : NativeModuleParcel.convertStringListToJSONArray(this.usernameSuggestions));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            ArrayList<String> arrayList = null;
            this.code = json.optDouble("code", 0.0d);
            this.message = json.isNull("message") ? null : json.optString("message");
            this.methodId = json.optString("methodId");
            this.nonce = json.isNull(ServiceContract.EXTRA_NONCE) ? null : json.optString(ServiceContract.EXTRA_NONCE);
            this.title = json.isNull("title") ? null : json.optString("title");
            this.allEmails = json.isNull("allEmails") ? null : NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("allEmails"));
            this.email = json.isNull(ServiceContract.EXTRA_EMAIL) ? null : json.optString(ServiceContract.EXTRA_EMAIL);
            this.existingEmailConflict = json.isNull("existingEmailConflict") ? null : json.optString("existingEmailConflict");
            this.fbAccessToken = json.isNull("fbAccessToken") ? null : json.optString("fbAccessToken");
            this.fbUserId = json.isNull("fbUserId") ? null : json.optString("fbUserId");
            this.name = json.isNull("name") ? null : json.optString("name");
            this.profilePicUri = json.isNull("profilePicUri") ? null : json.optString("profilePicUri");
            if (!json.isNull("usernameSuggestions")) {
                arrayList = NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("usernameSuggestions"));
            }
            this.usernameSuggestions = arrayList;
        }

        public static final AuthLoginWithFbAuthError makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthLoginWithFbAuthError result = new AuthLoginWithFbAuthError();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthSendTwoFactorCodeResult extends NativeModuleParcel {
        public AuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.error = json.isNull("error") ? null : AuthError.makeFromJSONObject(json.optJSONObject("error"));
        }

        public static final AuthSendTwoFactorCodeResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthSendTwoFactorCodeResult result = new AuthSendTwoFactorCodeResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthStatusResult extends NativeModuleParcel {
        public String accessToken;
        public AuthError error;
        public String userId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("accessToken", this.accessToken);
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
                parcel.put("userId", this.userId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.accessToken = json.isNull("accessToken") ? null : json.optString("accessToken");
            this.error = json.isNull("error") ? null : AuthError.makeFromJSONObject(json.optJSONObject("error"));
            if (!json.isNull("userId")) {
                str = json.optString("userId");
            }
            this.userId = str;
        }

        public static final AuthStatusResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthStatusResult result = new AuthStatusResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthVerifyLoginResult extends NativeModuleParcel {
        public AuthError error;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.error = json.isNull("error") ? null : AuthError.makeFromJSONObject(json.optJSONObject("error"));
        }

        public static final AuthVerifyLoginResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthVerifyLoginResult result = new AuthVerifyLoginResult();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AuthVerifyLoginForAccountLinkingResult extends NativeModuleParcel {
        public String accessToken;
        public AuthLoginError error;
        public String userId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.accessToken != null) {
                    parcel.put("accessToken", this.accessToken);
                }
                if (this.error != null) {
                    parcel.put("error", this.error == null ? JSONObject.NULL : this.error.convertToJSONObject());
                }
                if (this.userId != null) {
                    parcel.put("userId", this.userId);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.accessToken = json.isNull("accessToken") ? null : json.optString("accessToken");
            this.error = json.isNull("error") ? null : AuthLoginError.makeFromJSONObject(json.optJSONObject("error"));
            if (!json.isNull("userId")) {
                str = json.optString("userId");
            }
            this.userId = str;
        }

        public static final AuthVerifyLoginForAccountLinkingResult makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AuthVerifyLoginForAccountLinkingResult result = new AuthVerifyLoginForAccountLinkingResult();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
