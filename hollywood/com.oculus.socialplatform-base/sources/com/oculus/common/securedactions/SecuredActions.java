package com.oculus.common.securedactions;

import X.AbstractC06371Zh;
import X.AbstractC06511aN;
import X.AbstractC10551og;
import X.AbstractC13251zE;
import android.util.Log;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

public class SecuredActions {
    public static final String GRAPH_ENDPOINT = "https://graph.facebook.com";
    public static final int INCORRECT_PASSWORD_ERR_SUBCODE = 1348092;
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_CHALLENGE_PARAMS = "challenge_params";
    public static final String KEY_CHALLENGE_TYPE = "challenge_type";
    public static final String KEY_PASSWORD = "password";
    public static final String MIME_TYPE_JSON = "application/json";
    public static final String PASSWORD_CHALLENGE_TYPE = "PASSWORD";
    public static final String PATH_SECURED_ACTION_VALIDATE_CHALLENGE = "secured_action/validate_challenge";
    public static final String TAG = "SecuredActions";
    public static final int TOO_MANY_ATTEMPTS_ERR_SUBCODE = 1647001;

    public static final class Result {
        public Integer mErrorSubcode;
        public String mErrorUserMsg;
        public String mErrorUserTitle;
        public boolean mIsSuccessful;

        public static Result forSuccess() {
            return new Result(true, "", "", null);
        }

        public Integer getErrorSubcode() {
            return this.mErrorSubcode;
        }

        public String getErrorUserMsg() {
            return this.mErrorUserMsg;
        }

        public String getErrorUserTitle() {
            return this.mErrorUserTitle;
        }

        public boolean isSuccessful() {
            return this.mIsSuccessful;
        }

        public Result(boolean z, String str, String str2, Integer num) {
            this.mIsSuccessful = z;
            this.mErrorUserTitle = str;
            this.mErrorUserMsg = str2;
            this.mErrorSubcode = num;
        }

        public static Result forError() {
            return new Result(false, "", "", null);
        }

        public static Result forError(String str, String str2, Integer num) {
            return new Result(false, str, str2, num);
        }
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static AbstractC13251zE<Result> createPasswordSecuredActionSingle(OkHttpClient okHttpClient, String str, String str2) {
        return AbstractC13251zE.A00(new AbstractC06371Zh(str, str2, okHttpClient) {
            /* class com.oculus.common.securedactions.$$Lambda$SecuredActions$uylZ5Jg_frBW6fj48qyAG6p1Eyo2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ OkHttpClient f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r4) {
                SecuredActions.lambda$createPasswordSecuredActionSingle$0(this.f$0, this.f$1, this.f$2, r4);
            }
        });
    }

    public static /* synthetic */ void lambda$createPasswordSecuredActionSingle$0(String str, String str2, OkHttpClient okHttpClient, final AbstractC10551og r6) throws Exception {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("access_token", str);
            jSONObject.put(KEY_CHALLENGE_TYPE, PASSWORD_CHALLENGE_TYPE);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("password", str2);
            jSONObject.put(KEY_CHALLENGE_PARAMS, jSONObject2);
            HttpUrl.Builder newBuilder = HttpUrl.parse("https://graph.facebook.com").newBuilder();
            newBuilder.addPathSegment(PATH_SECURED_ACTION_VALIDATE_CHALLENGE);
            RequestBody create = RequestBody.create(MediaType.parse("application/json"), jSONObject.toString());
            Request.Builder builder = new Request.Builder();
            builder.url(newBuilder.build());
            builder.post(create);
            Call newCall = okHttpClient.newCall(builder.build());
            r6.A9i(new AbstractC06511aN() {
                /* class com.oculus.common.securedactions.$$Lambda$e0FgaLC6lAj6Kp7K5wo9aiITNUs2 */

                @Override // X.AbstractC06511aN
                public final void cancel() {
                    Call.this.cancel();
                }
            });
            newCall.enqueue(new Callback() {
                /* class com.oculus.common.securedactions.SecuredActions.AnonymousClass1 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Log.e(SecuredActions.TAG, "Secured action request failed.");
                    AbstractC10551og.this.onSuccess(Result.forError());
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    String str;
                    Integer num;
                    ResponseBody responseBody = response.body;
                    if (responseBody == null) {
                        try {
                            Log.e(SecuredActions.TAG, "Failed to get response body.");
                            AbstractC10551og.this.onSuccess(Result.forError());
                            return;
                        } catch (Throwable unused) {
                        }
                    } else {
                        if (response.isSuccessful()) {
                            AbstractC10551og.this.onSuccess(Result.forSuccess());
                        } else {
                            Log.e(SecuredActions.TAG, "Secured action responded with unsuccessful status.");
                            JSONObject optJSONObject = new JSONObject(responseBody.string()).optJSONObject("error");
                            String str2 = "";
                            if (optJSONObject != null) {
                                str = optJSONObject.optString(OculusAuthorizationInterceptor.EXTRA_ERROR_USER_TITLE);
                                str2 = optJSONObject.optString("error_user_msg");
                                num = Integer.valueOf(optJSONObject.optInt("error_subcode"));
                            } else {
                                str = str2;
                                num = null;
                            }
                            AbstractC10551og.this.onSuccess(Result.forError(str, str2, num));
                        }
                        try {
                            responseBody.close();
                            return;
                        } catch (IOException | JSONException unused2) {
                            Log.e(SecuredActions.TAG, "Secured action failed parsing content.");
                            AbstractC10551og.this.onSuccess(Result.forError());
                            return;
                        }
                    }
                    throw th;
                }
            });
        } catch (JSONException unused) {
            r6.onError(new RuntimeException("JSONException when constructing request body."));
        }
    }
}
