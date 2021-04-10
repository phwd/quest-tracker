package com.oculus.auth.api;

import android.os.Bundle;
import com.facebook.FacebookRequestError;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import com.google.gson.annotations.SerializedName;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class FBLoginResponse {
    @SerializedName("access_token")
    @Nullable
    public final String accessToken;
    @SerializedName("error_code")
    @Nullable
    public final Integer errorCode;
    @SerializedName(ServiceContract.EXTRA_ERROR_USER_MESSAGE)
    @Nullable
    public final String errorData;
    @SerializedName(FacebookRequestError.ERROR_MSG_KEY)
    @Nullable
    public final String errorMessage;
    @Nullable
    public final String identifier;
    @SerializedName("machine_id")
    @Nullable
    public final String machineId;
    @Nullable
    public final String secret;
    @SerializedName("session_key")
    @Nullable
    public final String sessionKey;
    @Nullable
    public final String uid;

    @Nullable
    private JSONObject errorDataToJson() {
        String str = this.errorData;
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public Bundle getResultBundle(Function<Integer, Integer> function) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        JSONObject jSONObject;
        Bundle bundle = new Bundle();
        Integer num = this.errorCode;
        if (num != null) {
            Integer apply = function.apply(num);
            Object[] objArr = new Object[0];
            if (apply != null) {
                bundle.putInt("error_code", apply.intValue());
            } else {
                throw new VerifyException(Strings.lenientFormat("expected a non-null reference", objArr));
            }
        }
        String str9 = this.errorMessage;
        if (str9 != null) {
            bundle.putString("error_message", str9);
        }
        String str10 = this.sessionKey;
        if (str10 != null) {
            bundle.putString(ServiceContract.EXTRA_SESSION_KEY, str10);
        }
        String str11 = this.accessToken;
        if (str11 != null) {
            bundle.putString("access_token", str11);
        }
        JSONObject errorDataToJson = errorDataToJson();
        if (errorDataToJson != null) {
            try {
                str = errorDataToJson.getString("uid");
            } catch (JSONException unused) {
                str = null;
            }
            if (str != null) {
                bundle.putString("uid", str);
            }
            try {
                str2 = errorDataToJson.getString("machine_id");
            } catch (JSONException unused2) {
                str2 = null;
            }
            if (str2 != null) {
                bundle.putString("machine_id", str2);
            }
            try {
                str3 = errorDataToJson.getString(ServiceContract.EXTRA_LOGIN_FIRST_FACTOR);
            } catch (JSONException unused3) {
                str3 = null;
            }
            if (str3 != null) {
                bundle.putString(ServiceContract.EXTRA_LOGIN_FIRST_FACTOR, str3);
            }
            try {
                str4 = errorDataToJson.getString(ServiceContract.EXTRA_AUTH_TOKEN);
            } catch (JSONException unused4) {
                str4 = null;
            }
            if (str4 != null) {
                bundle.putString(ServiceContract.EXTRA_AUTH_TOKEN, str4);
            }
            try {
                str5 = errorDataToJson.getString("error_title");
            } catch (JSONException unused5) {
                str5 = null;
            }
            if (str5 != null) {
                bundle.putString("error_title", str5);
            }
            try {
                str6 = errorDataToJson.getString(ServiceContract.EXTRA_ERROR_USER_MESSAGE);
            } catch (JSONException unused6) {
                str6 = null;
            }
            if (str6 != null) {
                bundle.putString(ServiceContract.EXTRA_ERROR_USER_MESSAGE, str6);
            }
            try {
                str7 = errorDataToJson.getString(ServiceContract.EXTRA_SUPPORT_URI);
            } catch (JSONException unused7) {
                str7 = null;
            }
            if (str7 != null) {
                bundle.putString(ServiceContract.EXTRA_SUPPORT_URI, str7);
            }
            try {
                str8 = errorDataToJson.getString("error_subcode");
            } catch (JSONException unused8) {
                str8 = null;
            }
            if (str8 != null) {
                try {
                    bundle.putInt("error_subcode", Integer.parseInt(str8));
                } catch (NumberFormatException unused9) {
                }
            }
            try {
                jSONObject = errorDataToJson.getJSONObject(ServiceContract.EXTRA_ENCRYPTION_KEY_PKG);
            } catch (JSONException unused10) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                try {
                    bundle.putInt("key_id", jSONObject.getInt("key_id"));
                    bundle.putString("public_key", jSONObject.getString("public_key"));
                    bundle.putLong(ServiceContract.EXTRA_ENCRYPTION_KEY_PKG_SECONDS_TO_LIVE, jSONObject.getLong(ServiceContract.EXTRA_ENCRYPTION_KEY_PKG_SECONDS_TO_LIVE));
                    return bundle;
                } catch (JSONException unused11) {
                }
            }
        } else {
            String str12 = this.uid;
            if (str12 != null) {
                bundle.putString("uid", str12);
            }
            String str13 = this.machineId;
            if (str13 != null) {
                bundle.putString("machine_id", str13);
            }
            String str14 = this.identifier;
            if (str14 != null) {
                bundle.putString(ServiceContract.EXTRA_IDENTIFIER, str14);
            }
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("sessionKey = ");
        sb.append(this.sessionKey);
        sb.append(", uid = ");
        sb.append(this.uid);
        sb.append(", secret = ");
        sb.append(this.secret);
        sb.append(", accessToken = ");
        sb.append(this.accessToken);
        sb.append(", machineId = ");
        sb.append(this.machineId);
        sb.append(", identifier = ");
        sb.append(this.identifier);
        sb.append(", errorCode = ");
        sb.append(this.errorCode);
        sb.append(", errorMessage = ");
        sb.append(this.errorMessage);
        sb.append(", errorData = ");
        sb.append(this.errorData);
        return sb.toString();
    }

    @Nullable
    public static JSONObject getJsonObject(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    public static String getString(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }
}
