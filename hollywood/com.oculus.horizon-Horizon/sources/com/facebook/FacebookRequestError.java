package com.facebook;

import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookRequestError {
    public static final String BODY_KEY = "body";
    public static final String CODE_KEY = "code";
    public static final String ERROR_CODE_FIELD_KEY = "code";
    public static final String ERROR_CODE_KEY = "error_code";
    public static final String ERROR_IS_TRANSIENT_KEY = "is_transient";
    public static final String ERROR_KEY = "error";
    public static final String ERROR_MESSAGE_FIELD_KEY = "message";
    public static final String ERROR_MSG_KEY = "error_msg";
    public static final String ERROR_REASON_KEY = "error_reason";
    public static final String ERROR_SUB_CODE_KEY = "error_subcode";
    public static final String ERROR_TYPE_FIELD_KEY = "type";
    public static final String ERROR_USER_MSG_KEY = "error_user_msg";
    public static final String ERROR_USER_TITLE_KEY = "error_user_title";
    public static final Range HTTP_RANGE_SUCCESS = new Range(200, 299);
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    public final Object batchRequestResult;
    public final Category category;
    public final HttpURLConnection connection;
    public final int errorCode;
    public final String errorMessage;
    public final String errorRecoveryMessage;
    public final String errorType;
    public final String errorUserMessage;
    public final String errorUserTitle;
    public final FacebookException exception;
    public final JSONObject requestResult;
    public final JSONObject requestResultBody;
    public final int requestStatusCode;
    public final int subErrorCode;

    public enum Category {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    public static class Range {
        public final int end;
        public final int start;

        public boolean contains(int i) {
            if (this.start > i || i > this.end) {
                return false;
            }
            return true;
        }

        public Range(int i, int i2) {
            this.start = i;
            this.end = i2;
        }
    }

    public static FacebookRequestError checkResponseAndCreateError(JSONObject jSONObject, Object obj, HttpURLConnection httpURLConnection) {
        JSONObject jSONObject2;
        String optString;
        String optString2;
        int optInt;
        int optInt2;
        String str;
        String str2;
        boolean z;
        try {
            if (jSONObject.has("code")) {
                int i = jSONObject.getInt("code");
                Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, "body", GraphResponse.NON_JSON_RESPONSE_PROPERTY);
                if (stringPropertyAsJSON != null && (stringPropertyAsJSON instanceof JSONObject)) {
                    JSONObject jSONObject3 = (JSONObject) stringPropertyAsJSON;
                    if (jSONObject3.has("error")) {
                        JSONObject jSONObject4 = (JSONObject) Utility.getStringPropertyAsJSON(jSONObject3, "error", null);
                        optString = jSONObject4.optString("type", null);
                        optString2 = jSONObject4.optString("message", null);
                        optInt = jSONObject4.optInt("code", -1);
                        optInt2 = jSONObject4.optInt("error_subcode", -1);
                        str = jSONObject4.optString(ERROR_USER_MSG_KEY, null);
                        str2 = jSONObject4.optString("error_user_title", null);
                        z = jSONObject4.optBoolean(ERROR_IS_TRANSIENT_KEY, false);
                    } else if (jSONObject3.has("error_code") || jSONObject3.has(ERROR_MSG_KEY) || jSONObject3.has("error_reason")) {
                        optString = jSONObject3.optString("error_reason", null);
                        optString2 = jSONObject3.optString(ERROR_MSG_KEY, null);
                        optInt = jSONObject3.optInt("error_code", -1);
                        optInt2 = jSONObject3.optInt("error_subcode", -1);
                        str = null;
                        str2 = null;
                        z = false;
                    }
                    return new FacebookRequestError(i, optInt, optInt2, optString, optString2, str2, str, z, jSONObject3, jSONObject, obj, httpURLConnection, null);
                }
                if (!HTTP_RANGE_SUCCESS.contains(i)) {
                    if (jSONObject.has("body")) {
                        jSONObject2 = (JSONObject) Utility.getStringPropertyAsJSON(jSONObject, "body", GraphResponse.NON_JSON_RESPONSE_PROPERTY);
                    } else {
                        jSONObject2 = null;
                    }
                    return new FacebookRequestError(i, -1, -1, null, null, null, null, false, jSONObject2, jSONObject, obj, httpURLConnection, null);
                }
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    public static synchronized FacebookRequestErrorClassification getErrorClassification() {
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        synchronized (FacebookRequestError.class) {
            Validate.sdkInitialized();
            Utility.FetchedAppSettings appSettingsWithoutQuery = Utility.getAppSettingsWithoutQuery(FacebookSdk.applicationId);
            if (appSettingsWithoutQuery == null) {
                facebookRequestErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
            } else {
                facebookRequestErrorClassification = appSettingsWithoutQuery.errorClassification;
            }
        }
        return facebookRequestErrorClassification;
    }

    public String getErrorMessage() {
        String str = this.errorMessage;
        if (str == null) {
            return this.exception.getLocalizedMessage();
        }
        return str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{HttpStatus: ");
        sb.append(this.requestStatusCode);
        sb.append(", errorCode: ");
        sb.append(this.errorCode);
        sb.append(", errorType: ");
        sb.append(this.errorType);
        sb.append(", errorMessage: ");
        sb.append(getErrorMessage());
        sb.append("}");
        return sb.toString();
    }

    public Object getBatchRequestResult() {
        return this.batchRequestResult;
    }

    public Category getCategory() {
        return this.category;
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorRecoveryMessage() {
        return this.errorRecoveryMessage;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public String getErrorUserMessage() {
        return this.errorUserMessage;
    }

    public String getErrorUserTitle() {
        return this.errorUserTitle;
    }

    public FacebookException getException() {
        return this.exception;
    }

    public JSONObject getRequestResult() {
        return this.requestResult;
    }

    public JSONObject getRequestResultBody() {
        return this.requestResultBody;
    }

    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public int getSubErrorCode() {
        return this.subErrorCode;
    }

    public FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        boolean z2;
        Category classify;
        this.requestStatusCode = i;
        this.errorCode = i2;
        this.subErrorCode = i3;
        this.errorType = str;
        this.errorMessage = str2;
        this.requestResultBody = jSONObject;
        this.requestResult = jSONObject2;
        this.batchRequestResult = obj;
        this.connection = httpURLConnection;
        this.errorUserTitle = str3;
        this.errorUserMessage = str4;
        if (facebookException != null) {
            this.exception = facebookException;
            z2 = true;
        } else {
            this.exception = new FacebookServiceException(this, str2);
            z2 = false;
        }
        FacebookRequestErrorClassification errorClassification = getErrorClassification();
        if (z2) {
            classify = Category.OTHER;
        } else {
            classify = errorClassification.classify(i2, i3, z);
        }
        this.category = classify;
        this.errorRecoveryMessage = errorClassification.getRecoveryMessage(classify);
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, null, null, false, null, null, null, null, null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FacebookRequestError(HttpURLConnection httpURLConnection, Exception exc) {
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, exc instanceof FacebookException ? (FacebookException) exc : new FacebookException(exc));
    }
}
