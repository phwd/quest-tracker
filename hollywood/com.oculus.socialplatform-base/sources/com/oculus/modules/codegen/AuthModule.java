package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AuthModule extends AbstractBroadcastReceiverModule {
    public static final String MODULE_NAME = "AuthModule";

    public static class AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult extends NativeModuleParcel {
        public String accessToken;
        public AuthLoginError error;
        public String userId;

        public static final AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult authAuthWithOculusEmailAndPasswordForAccountLinkingResult = new AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult();
            authAuthWithOculusEmailAndPasswordForAccountLinkingResult.setFromJSONObject(jSONObject);
            return authAuthWithOculusEmailAndPasswordForAccountLinkingResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.accessToken;
                if (str != null) {
                    jSONObject.put("accessToken", str);
                }
                AuthLoginError authLoginError = this.error;
                if (authLoginError != null) {
                    if (authLoginError == null) {
                        convertToJSONObject = JSONObject.NULL;
                    } else {
                        convertToJSONObject = authLoginError.convertToJSONObject();
                    }
                    jSONObject.put("error", convertToJSONObject);
                }
                String str2 = this.userId;
                if (str2 != null) {
                    jSONObject.put("userId", str2);
                    return jSONObject;
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            AuthLoginError makeFromJSONObject;
            String str = null;
            if (jSONObject.isNull("accessToken")) {
                optString = null;
            } else {
                optString = jSONObject.optString("accessToken");
            }
            this.accessToken = optString;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthLoginError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
            if (!jSONObject.isNull("userId")) {
                str = jSONObject.optString("userId");
            }
            this.userId = str;
        }
    }

    public static class AuthCheckFbMachineApprovalResult extends NativeModuleParcel {
        public Boolean approved;
        public AuthError error;

        public static final AuthCheckFbMachineApprovalResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthCheckFbMachineApprovalResult authCheckFbMachineApprovalResult = new AuthCheckFbMachineApprovalResult();
            authCheckFbMachineApprovalResult.setFromJSONObject(jSONObject);
            return authCheckFbMachineApprovalResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("approved", this.approved);
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Boolean valueOf;
            AuthError authError = null;
            if (jSONObject.isNull("approved")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("approved"));
            }
            this.approved = valueOf;
            if (!jSONObject.isNull("error")) {
                authError = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = authError;
        }
    }

    public static class AuthError extends NativeModuleParcel {
        public double code;
        public String message;
        public String methodId;
        public String nonce;
        public String title;

        public static final AuthError makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthError authError = new AuthError();
            authError.setFromJSONObject(jSONObject);
            return authError;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", this.code);
                jSONObject.put("message", this.message);
                jSONObject.put("methodId", this.methodId);
                jSONObject.put("nonce", this.nonce);
                jSONObject.put("title", this.title);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String optString2;
            this.code = jSONObject.optDouble("code", 0.0d);
            String str = null;
            if (jSONObject.isNull("message")) {
                optString = null;
            } else {
                optString = jSONObject.optString("message");
            }
            this.message = optString;
            this.methodId = jSONObject.optString("methodId");
            if (jSONObject.isNull("nonce")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("nonce");
            }
            this.nonce = optString2;
            if (!jSONObject.isNull("title")) {
                str = jSONObject.optString("title");
            }
            this.title = str;
        }
    }

    public static class AuthFbLinkResult extends NativeModuleParcel {
        public AuthError error;

        public static final AuthFbLinkResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthFbLinkResult authFbLinkResult = new AuthFbLinkResult();
            authFbLinkResult.setFromJSONObject(jSONObject);
            return authFbLinkResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthError makeFromJSONObject;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
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

        public static final AuthFbLoginError makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthFbLoginError authFbLoginError = new AuthFbLoginError();
            authFbLoginError.setFromJSONObject(jSONObject);
            return authFbLoginError;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", this.code);
                jSONObject.put("message", this.message);
                jSONObject.put("methodId", this.methodId);
                jSONObject.put("nonce", this.nonce);
                jSONObject.put("title", this.title);
                jSONObject.put("authToken", this.authToken);
                jSONObject.put("fbUserId", this.fbUserId);
                jSONObject.put("loginFirstFactor", this.loginFirstFactor);
                jSONObject.put("machineId", this.machineId);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String optString2;
            String optString3;
            String optString4;
            String optString5;
            String optString6;
            this.code = jSONObject.optDouble("code", 0.0d);
            String str = null;
            if (jSONObject.isNull("message")) {
                optString = null;
            } else {
                optString = jSONObject.optString("message");
            }
            this.message = optString;
            this.methodId = jSONObject.optString("methodId");
            if (jSONObject.isNull("nonce")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("nonce");
            }
            this.nonce = optString2;
            if (jSONObject.isNull("title")) {
                optString3 = null;
            } else {
                optString3 = jSONObject.optString("title");
            }
            this.title = optString3;
            if (jSONObject.isNull("authToken")) {
                optString4 = null;
            } else {
                optString4 = jSONObject.optString("authToken");
            }
            this.authToken = optString4;
            if (jSONObject.isNull("fbUserId")) {
                optString5 = null;
            } else {
                optString5 = jSONObject.optString("fbUserId");
            }
            this.fbUserId = optString5;
            if (jSONObject.isNull("loginFirstFactor")) {
                optString6 = null;
            } else {
                optString6 = jSONObject.optString("loginFirstFactor");
            }
            this.loginFirstFactor = optString6;
            if (!jSONObject.isNull("machineId")) {
                str = jSONObject.optString("machineId");
            }
            this.machineId = str;
        }
    }

    public static class AuthFbLoginResult extends NativeModuleParcel {
        public AuthFbLoginError error;
        public String fbAccessToken;
        public String fbUserId;

        public static final AuthFbLoginResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthFbLoginResult authFbLoginResult = new AuthFbLoginResult();
            authFbLoginResult.setFromJSONObject(jSONObject);
            return authFbLoginResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthFbLoginError authFbLoginError = this.error;
                if (authFbLoginError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authFbLoginError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                jSONObject.put("fbAccessToken", this.fbAccessToken);
                jSONObject.put("fbUserId", this.fbUserId);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthFbLoginError makeFromJSONObject;
            String optString;
            String str = null;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthFbLoginError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
            if (jSONObject.isNull("fbAccessToken")) {
                optString = null;
            } else {
                optString = jSONObject.optString("fbAccessToken");
            }
            this.fbAccessToken = optString;
            if (!jSONObject.isNull("fbUserId")) {
                str = jSONObject.optString("fbUserId");
            }
            this.fbUserId = str;
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

        public static final AuthFetchFbInfoForAccountLinkingError makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthFetchFbInfoForAccountLinkingError authFetchFbInfoForAccountLinkingError = new AuthFetchFbInfoForAccountLinkingError();
            authFetchFbInfoForAccountLinkingError.setFromJSONObject(jSONObject);
            return authFetchFbInfoForAccountLinkingError;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", this.code);
                jSONObject.put("message", this.message);
                jSONObject.put("methodId", this.methodId);
                jSONObject.put("nonce", this.nonce);
                jSONObject.put("title", this.title);
                jSONObject.put("accessToken", this.accessToken);
                jSONObject.put("userId", this.userId);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String optString2;
            String optString3;
            String optString4;
            this.code = jSONObject.optDouble("code", 0.0d);
            String str = null;
            if (jSONObject.isNull("message")) {
                optString = null;
            } else {
                optString = jSONObject.optString("message");
            }
            this.message = optString;
            this.methodId = jSONObject.optString("methodId");
            if (jSONObject.isNull("nonce")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("nonce");
            }
            this.nonce = optString2;
            if (jSONObject.isNull("title")) {
                optString3 = null;
            } else {
                optString3 = jSONObject.optString("title");
            }
            this.title = optString3;
            if (jSONObject.isNull("accessToken")) {
                optString4 = null;
            } else {
                optString4 = jSONObject.optString("accessToken");
            }
            this.accessToken = optString4;
            if (!jSONObject.isNull("userId")) {
                str = jSONObject.optString("userId");
            }
            this.userId = str;
        }
    }

    public static class AuthFetchFbInfoForAccountLinkingResult extends NativeModuleParcel {
        public String email;
        public AuthFetchFbInfoForAccountLinkingError error;
        public String name;
        public String profilePicUri;

        public static final AuthFetchFbInfoForAccountLinkingResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthFetchFbInfoForAccountLinkingResult authFetchFbInfoForAccountLinkingResult = new AuthFetchFbInfoForAccountLinkingResult();
            authFetchFbInfoForAccountLinkingResult.setFromJSONObject(jSONObject);
            return authFetchFbInfoForAccountLinkingResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.email;
                if (str != null) {
                    jSONObject.put("email", str);
                }
                AuthFetchFbInfoForAccountLinkingError authFetchFbInfoForAccountLinkingError = this.error;
                if (authFetchFbInfoForAccountLinkingError != null) {
                    if (authFetchFbInfoForAccountLinkingError == null) {
                        convertToJSONObject = JSONObject.NULL;
                    } else {
                        convertToJSONObject = authFetchFbInfoForAccountLinkingError.convertToJSONObject();
                    }
                    jSONObject.put("error", convertToJSONObject);
                }
                String str2 = this.name;
                if (str2 != null) {
                    jSONObject.put("name", str2);
                }
                String str3 = this.profilePicUri;
                if (str3 != null) {
                    jSONObject.put("profilePicUri", str3);
                    return jSONObject;
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            AuthFetchFbInfoForAccountLinkingError makeFromJSONObject;
            String optString2;
            String str = null;
            if (jSONObject.isNull("email")) {
                optString = null;
            } else {
                optString = jSONObject.optString("email");
            }
            this.email = optString;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthFetchFbInfoForAccountLinkingError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
            if (jSONObject.isNull("name")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("name");
            }
            this.name = optString2;
            if (!jSONObject.isNull("profilePicUri")) {
                str = jSONObject.optString("profilePicUri");
            }
            this.profilePicUri = str;
        }
    }

    public static class AuthLoginError extends NativeModuleParcel {
        public double code;
        public String message;
        public String methodId;
        public String nonce;
        public String title;

        public static final AuthLoginError makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthLoginError authLoginError = new AuthLoginError();
            authLoginError.setFromJSONObject(jSONObject);
            return authLoginError;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", this.code);
                jSONObject.put("message", this.message);
                jSONObject.put("methodId", this.methodId);
                jSONObject.put("nonce", this.nonce);
                jSONObject.put("title", this.title);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String optString2;
            this.code = jSONObject.optDouble("code", 0.0d);
            String str = null;
            if (jSONObject.isNull("message")) {
                optString = null;
            } else {
                optString = jSONObject.optString("message");
            }
            this.message = optString;
            this.methodId = jSONObject.optString("methodId");
            if (jSONObject.isNull("nonce")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("nonce");
            }
            this.nonce = optString2;
            if (!jSONObject.isNull("title")) {
                str = jSONObject.optString("title");
            }
            this.title = str;
        }
    }

    public static class AuthLoginResult extends NativeModuleParcel {
        public AuthLoginError error;

        public static final AuthLoginResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthLoginResult authLoginResult = new AuthLoginResult();
            authLoginResult.setFromJSONObject(jSONObject);
            return authLoginResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthLoginError authLoginError = this.error;
                if (authLoginError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authLoginError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthLoginError makeFromJSONObject;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthLoginError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
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

        public static final AuthLoginWithFbAuthError makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthLoginWithFbAuthError authLoginWithFbAuthError = new AuthLoginWithFbAuthError();
            authLoginWithFbAuthError.setFromJSONObject(jSONObject);
            return authLoginWithFbAuthError;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertStringListToJSONArray;
            Object convertStringListToJSONArray2;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", this.code);
                jSONObject.put("message", this.message);
                jSONObject.put("methodId", this.methodId);
                jSONObject.put("nonce", this.nonce);
                jSONObject.put("title", this.title);
                List<String> list = this.allEmails;
                if (list == null) {
                    convertStringListToJSONArray = JSONObject.NULL;
                } else {
                    convertStringListToJSONArray = NativeModuleParcel.convertStringListToJSONArray(list);
                }
                jSONObject.put("allEmails", convertStringListToJSONArray);
                jSONObject.put("email", this.email);
                jSONObject.put("existingEmailConflict", this.existingEmailConflict);
                jSONObject.put("fbAccessToken", this.fbAccessToken);
                jSONObject.put("fbUserId", this.fbUserId);
                jSONObject.put("name", this.name);
                jSONObject.put("profilePicUri", this.profilePicUri);
                List<String> list2 = this.usernameSuggestions;
                if (list2 == null) {
                    convertStringListToJSONArray2 = JSONObject.NULL;
                } else {
                    convertStringListToJSONArray2 = NativeModuleParcel.convertStringListToJSONArray(list2);
                }
                jSONObject.put("usernameSuggestions", convertStringListToJSONArray2);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String optString2;
            String optString3;
            ArrayList<String> convertJSONArrayToStringList;
            String optString4;
            String optString5;
            String optString6;
            String optString7;
            String optString8;
            String optString9;
            this.code = jSONObject.optDouble("code", 0.0d);
            ArrayList<String> arrayList = null;
            if (jSONObject.isNull("message")) {
                optString = null;
            } else {
                optString = jSONObject.optString("message");
            }
            this.message = optString;
            this.methodId = jSONObject.optString("methodId");
            if (jSONObject.isNull("nonce")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("nonce");
            }
            this.nonce = optString2;
            if (jSONObject.isNull("title")) {
                optString3 = null;
            } else {
                optString3 = jSONObject.optString("title");
            }
            this.title = optString3;
            if (jSONObject.isNull("allEmails")) {
                convertJSONArrayToStringList = null;
            } else {
                convertJSONArrayToStringList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("allEmails"));
            }
            this.allEmails = convertJSONArrayToStringList;
            if (jSONObject.isNull("email")) {
                optString4 = null;
            } else {
                optString4 = jSONObject.optString("email");
            }
            this.email = optString4;
            if (jSONObject.isNull("existingEmailConflict")) {
                optString5 = null;
            } else {
                optString5 = jSONObject.optString("existingEmailConflict");
            }
            this.existingEmailConflict = optString5;
            if (jSONObject.isNull("fbAccessToken")) {
                optString6 = null;
            } else {
                optString6 = jSONObject.optString("fbAccessToken");
            }
            this.fbAccessToken = optString6;
            if (jSONObject.isNull("fbUserId")) {
                optString7 = null;
            } else {
                optString7 = jSONObject.optString("fbUserId");
            }
            this.fbUserId = optString7;
            if (jSONObject.isNull("name")) {
                optString8 = null;
            } else {
                optString8 = jSONObject.optString("name");
            }
            this.name = optString8;
            if (jSONObject.isNull("profilePicUri")) {
                optString9 = null;
            } else {
                optString9 = jSONObject.optString("profilePicUri");
            }
            this.profilePicUri = optString9;
            if (!jSONObject.isNull("usernameSuggestions")) {
                arrayList = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("usernameSuggestions"));
            }
            this.usernameSuggestions = arrayList;
        }
    }

    public static class AuthLoginWithFbAuthResult extends NativeModuleParcel {
        public AuthLoginWithFbAuthError error;

        public static final AuthLoginWithFbAuthResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthLoginWithFbAuthResult authLoginWithFbAuthResult = new AuthLoginWithFbAuthResult();
            authLoginWithFbAuthResult.setFromJSONObject(jSONObject);
            return authLoginWithFbAuthResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthLoginWithFbAuthError authLoginWithFbAuthError = this.error;
                if (authLoginWithFbAuthError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authLoginWithFbAuthError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthLoginWithFbAuthError makeFromJSONObject;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthLoginWithFbAuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
        }
    }

    public static class AuthResult extends NativeModuleParcel {
        public AuthError error;

        public static final AuthResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthResult authResult = new AuthResult();
            authResult.setFromJSONObject(jSONObject);
            return authResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthError makeFromJSONObject;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
        }
    }

    public static class AuthSendTwoFactorCodeResult extends NativeModuleParcel {
        public AuthError error;

        public static final AuthSendTwoFactorCodeResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthSendTwoFactorCodeResult authSendTwoFactorCodeResult = new AuthSendTwoFactorCodeResult();
            authSendTwoFactorCodeResult.setFromJSONObject(jSONObject);
            return authSendTwoFactorCodeResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthError makeFromJSONObject;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
        }
    }

    public static class AuthStatusResult extends NativeModuleParcel {
        public String accessToken;
        public AuthError error;
        public String userId;

        public static final AuthStatusResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthStatusResult authStatusResult = new AuthStatusResult();
            authStatusResult.setFromJSONObject(jSONObject);
            return authStatusResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("accessToken", this.accessToken);
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                jSONObject.put("userId", this.userId);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            AuthError makeFromJSONObject;
            String str = null;
            if (jSONObject.isNull("accessToken")) {
                optString = null;
            } else {
                optString = jSONObject.optString("accessToken");
            }
            this.accessToken = optString;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
            if (!jSONObject.isNull("userId")) {
                str = jSONObject.optString("userId");
            }
            this.userId = str;
        }
    }

    public static class AuthVerifyLoginForAccountLinkingResult extends NativeModuleParcel {
        public String accessToken;
        public AuthLoginError error;
        public String userId;

        public static final AuthVerifyLoginForAccountLinkingResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthVerifyLoginForAccountLinkingResult authVerifyLoginForAccountLinkingResult = new AuthVerifyLoginForAccountLinkingResult();
            authVerifyLoginForAccountLinkingResult.setFromJSONObject(jSONObject);
            return authVerifyLoginForAccountLinkingResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.accessToken;
                if (str != null) {
                    jSONObject.put("accessToken", str);
                }
                AuthLoginError authLoginError = this.error;
                if (authLoginError != null) {
                    if (authLoginError == null) {
                        convertToJSONObject = JSONObject.NULL;
                    } else {
                        convertToJSONObject = authLoginError.convertToJSONObject();
                    }
                    jSONObject.put("error", convertToJSONObject);
                }
                String str2 = this.userId;
                if (str2 != null) {
                    jSONObject.put("userId", str2);
                    return jSONObject;
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            AuthLoginError makeFromJSONObject;
            String str = null;
            if (jSONObject.isNull("accessToken")) {
                optString = null;
            } else {
                optString = jSONObject.optString("accessToken");
            }
            this.accessToken = optString;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthLoginError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
            if (!jSONObject.isNull("userId")) {
                str = jSONObject.optString("userId");
            }
            this.userId = str;
        }
    }

    public static class AuthVerifyLoginResult extends NativeModuleParcel {
        public AuthError error;

        public static final AuthVerifyLoginResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AuthVerifyLoginResult authVerifyLoginResult = new AuthVerifyLoginResult();
            authVerifyLoginResult.setFromJSONObject(jSONObject);
            return authVerifyLoginResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            AuthError makeFromJSONObject;
            if (jSONObject.isNull("error")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = makeFromJSONObject;
        }
    }

    public static class CredentialsResult extends NativeModuleParcel {
        public String accessToken;
        public AuthError error;

        public static final CredentialsResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            CredentialsResult credentialsResult = new CredentialsResult();
            credentialsResult.setFromJSONObject(jSONObject);
            return credentialsResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("accessToken", this.accessToken);
                AuthError authError = this.error;
                if (authError == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = authError.convertToJSONObject();
                }
                jSONObject.put("error", convertToJSONObject);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            AuthError authError = null;
            if (jSONObject.isNull("accessToken")) {
                optString = null;
            } else {
                optString = jSONObject.optString("accessToken");
            }
            this.accessToken = optString;
            if (!jSONObject.isNull("error")) {
                authError = AuthError.makeFromJSONObject(jSONObject.optJSONObject("error"));
            }
            this.error = authError;
        }
    }

    public abstract void authWithOculusEmailAndPasswordForAccountLinkingImpl(String str, String str2, Resolver<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult> resolver);

    public abstract void checkFbMachineApprovalImpl(String str, String str2, Resolver<AuthCheckFbMachineApprovalResult> resolver);

    public abstract void completeFbLoginAfterMachineApprovalImpl(String str, String str2, String str3, Resolver<AuthFbLoginResult> resolver);

    public abstract void completeFbLoginWithTwoFactorCodeImpl(String str, String str2, String str3, String str4, String str5, Resolver<AuthFbLoginResult> resolver);

    public abstract void ensureDeviceOwnershipImpl(Resolver<AuthResult> resolver);

    public abstract void fetchDeviceScopedCredentialsImpl(Resolver<CredentialsResult> resolver);

    public abstract void fetchFbInfoForAccountLinkingImpl(String str, Resolver<AuthFetchFbInfoForAccountLinkingResult> resolver);

    public abstract void linkFbAccountImpl(String str, String str2, Resolver<AuthFbLinkResult> resolver);

    public abstract void loginToFbImpl(String str, String str2, Resolver<AuthFbLoginResult> resolver);

    public abstract void loginWithEmailAndPasswordImpl(String str, String str2, Resolver<AuthLoginResult> resolver);

    public abstract void loginWithFbAuthImpl(String str, Resolver<AuthLoginWithFbAuthResult> resolver);

    public abstract double marshallJSConstantErrorCodeAccountConflict();

    public abstract double marshallJSConstantErrorCodeAnomalousLogin();

    public abstract double marshallJSConstantErrorCodeCannotMergeAccountsBecauseAlreadyLinked();

    public abstract double marshallJSConstantErrorCodeDeviceAlreadyClaimed();

    public abstract double marshallJSConstantErrorCodeHttpError();

    public abstract double marshallJSConstantErrorCodeInvalidCreds();

    public abstract double marshallJSConstantErrorCodeLoginApprovalsInvalidCode();

    public abstract double marshallJSConstantErrorCodeNetworkError();

    public abstract double marshallJSConstantErrorCodeNoDeviceIdentity();

    public abstract double marshallJSConstantErrorCodeNoLinkedAccount();

    public abstract double marshallJSConstantErrorCodeRateLimited();

    public abstract double marshallJSConstantErrorCodeTwoFactorLoginRequired();

    public abstract double marshallJSConstantErrorCodeUserAlreadyOnDevice();

    public abstract void reloginWithEmailAndPasswordImpl(String str, String str2, Resolver<AuthLoginResult> resolver);

    public abstract void reloginWithFbAuthImpl(String str, Resolver<AuthLoginWithFbAuthResult> resolver);

    public abstract void sendTwoFactorCodeImpl(String str, String str2, Resolver<AuthSendTwoFactorCodeResult> resolver);

    public void shutdownModuleImpl() {
    }

    public abstract void statusImpl(Resolver<AuthStatusResult> resolver);

    public abstract void verifyLoginForAccountLinkingImpl(String str, String str2, String str3, String str4, Resolver<AuthVerifyLoginForAccountLinkingResult> resolver);

    public abstract void verifyLoginImpl(String str, String str2, String str3, String str4, boolean z, Resolver<AuthVerifyLoginResult> resolver);

    public abstract void verifyReloginImpl(String str, String str2, String str3, String str4, boolean z, Resolver<AuthVerifyLoginResult> resolver);

    public final void emitOnLogin(JSONObject jSONObject) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "AuthModule_onLogin", String.valueOf(jSONObject));
    }

    public final String marshallJSConstants() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ERROR_CODE_ACCOUNT_CONFLICT", marshallJSConstantErrorCodeAccountConflict());
            jSONObject.put("ERROR_CODE_ANOMALOUS_LOGIN", marshallJSConstantErrorCodeAnomalousLogin());
            jSONObject.put("ERROR_CODE_CANNOT_MERGE_ACCOUNTS_BECAUSE_ALREADY_LINKED", marshallJSConstantErrorCodeCannotMergeAccountsBecauseAlreadyLinked());
            jSONObject.put("ERROR_CODE_DEVICE_ALREADY_CLAIMED", marshallJSConstantErrorCodeDeviceAlreadyClaimed());
            jSONObject.put("ERROR_CODE_HTTP_ERROR", marshallJSConstantErrorCodeHttpError());
            jSONObject.put("ERROR_CODE_INVALID_CREDS", marshallJSConstantErrorCodeInvalidCreds());
            jSONObject.put("ERROR_CODE_LOGIN_APPROVALS_INVALID_CODE", marshallJSConstantErrorCodeLoginApprovalsInvalidCode());
            jSONObject.put("ERROR_CODE_NETWORK_ERROR", marshallJSConstantErrorCodeNetworkError());
            jSONObject.put("ERROR_CODE_NO_DEVICE_IDENTITY", marshallJSConstantErrorCodeNoDeviceIdentity());
            jSONObject.put("ERROR_CODE_NO_LINKED_ACCOUNT", marshallJSConstantErrorCodeNoLinkedAccount());
            jSONObject.put("ERROR_CODE_RATE_LIMITED", marshallJSConstantErrorCodeRateLimited());
            jSONObject.put("ERROR_CODE_TWO_FACTOR_LOGIN_REQUIRED", marshallJSConstantErrorCodeTwoFactorLoginRequired());
            jSONObject.put("ERROR_CODE_USER_ALREADY_ON_DEVICE", marshallJSConstantErrorCodeUserAlreadyOnDevice());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> describe = AbstractBroadcastReceiverModule.describe();
        describe.add(new Pair<>("authWithOculusEmailAndPasswordForAccountLinking", "+ssii"));
        describe.add(new Pair<>("checkFbMachineApproval", "+ssii"));
        describe.add(new Pair<>("completeFbLoginAfterMachineApproval", "+sssii"));
        describe.add(new Pair<>("completeFbLoginWithTwoFactorCode", "+sssssii"));
        describe.add(new Pair<>("ensureDeviceOwnership", "+ii"));
        describe.add(new Pair<>("fetchDeviceScopedCredentials", "+ii"));
        describe.add(new Pair<>("fetchFbInfoForAccountLinking", "+sii"));
        describe.add(new Pair<>("linkFbAccount", "+ssii"));
        describe.add(new Pair<>("loginToFb", "+ssii"));
        describe.add(new Pair<>("loginWithEmailAndPassword", "+ssii"));
        describe.add(new Pair<>("loginWithFbAuth", "+sii"));
        describe.add(new Pair<>("reloginWithEmailAndPassword", "+ssii"));
        describe.add(new Pair<>("reloginWithFbAuth", "+sii"));
        describe.add(new Pair<>("sendTwoFactorCode", "+ssii"));
        describe.add(new Pair<>("status", "+ii"));
        describe.add(new Pair<>("verifyLogin", "+ssssbii"));
        describe.add(new Pair<>("verifyLoginForAccountLinking", "+ssssii"));
        describe.add(new Pair<>("verifyRelogin", "+ssssbii"));
        return describe;
    }

    public final void authWithOculusEmailAndPasswordForAccountLinking(String str, String str2, int i, int i2) {
        authWithOculusEmailAndPasswordForAccountLinkingImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void checkFbMachineApproval(String str, String str2, int i, int i2) {
        checkFbMachineApprovalImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void completeFbLoginAfterMachineApproval(String str, String str2, String str3, int i, int i2) {
        completeFbLoginAfterMachineApprovalImpl(str, str2, str3, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void completeFbLoginWithTwoFactorCode(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        completeFbLoginWithTwoFactorCodeImpl(str, str2, str3, str4, str5, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void ensureDeviceOwnership(int i, int i2) {
        ensureDeviceOwnershipImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void fetchDeviceScopedCredentials(int i, int i2) {
        fetchDeviceScopedCredentialsImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void fetchFbInfoForAccountLinking(String str, int i, int i2) {
        fetchFbInfoForAccountLinkingImpl(str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void linkFbAccount(String str, String str2, int i, int i2) {
        linkFbAccountImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void loginToFb(String str, String str2, int i, int i2) {
        loginToFbImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void loginWithEmailAndPassword(String str, String str2, int i, int i2) {
        loginWithEmailAndPasswordImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void loginWithFbAuth(String str, int i, int i2) {
        loginWithFbAuthImpl(str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void reloginWithEmailAndPassword(String str, String str2, int i, int i2) {
        reloginWithEmailAndPasswordImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void reloginWithFbAuth(String str, int i, int i2) {
        reloginWithFbAuthImpl(str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void sendTwoFactorCode(String str, String str2, int i, int i2) {
        sendTwoFactorCodeImpl(str, str2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void status(int i, int i2) {
        statusImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void verifyLogin(String str, String str2, String str3, String str4, boolean z, int i, int i2) {
        verifyLoginImpl(str, str2, str3, str4, z, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void verifyLoginForAccountLinking(String str, String str2, String str3, String str4, int i, int i2) {
        verifyLoginForAccountLinkingImpl(str, str2, str3, str4, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void verifyRelogin(String str, String str2, String str3, String str4, boolean z, int i, int i2) {
        verifyReloginImpl(str, str2, str3, str4, z, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public AuthModule(Context context) {
        super(context);
    }
}
