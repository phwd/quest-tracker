package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerBlockGraphQL;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class MessengerBlockGraphQL {
    private static final String TAG = LoggingUtil.tag(MessengerBlockGraphQL.class);
    private static final String docID = "3671641512955622";

    public interface SuccessCallback {
        void callback(boolean z);
    }

    public static void block(OkHttpClient okHttpClient, String str, String str2, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        setBlockStatus(okHttpClient, str, str2, true, successCallback, failureCallback);
    }

    public static void unBlock(OkHttpClient okHttpClient, String str, String str2, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        setBlockStatus(okHttpClient, str, str2, false, successCallback, failureCallback);
    }

    private static void setBlockStatus(OkHttpClient okHttpClient, String str, String str2, Boolean bool, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, buildGraphQLVariables(str2, bool), str, new FacebookGraphQLUtil.SuccessCallback(failureCallback) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$MessengerBlockGraphQL$X0J2jLuhR31zeZmVYBn7Hzi6aZY */
            private final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                MessengerBlockGraphQL.lambda$setBlockStatus$70(MessengerBlockGraphQL.SuccessCallback.this, this.f$1, jSONObject);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$MessengerBlockGraphQL$4OIZXMpKCYP9lvp5nGcheDBDWsE */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                MessengerBlockGraphQL.lambda$setBlockStatus$71(FacebookGraphQLUtil.FailureCallback.this, th);
            }
        });
    }

    static /* synthetic */ void lambda$setBlockStatus$70(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, JSONObject jSONObject) {
        try {
            boolean optBoolean = FacebookGraphQLUtil.parseField(FacebookGraphQLUtil.parseField(jSONObject, "messenger_block"), "messaging_actor").optBoolean("is_message_blocked_by_viewer");
            String str = TAG;
            Log.d(str, "is target message blocked: " + String.valueOf(optBoolean));
            successCallback.callback(optBoolean);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON: ", e);
            failureCallback.callback(e);
        }
    }

    static /* synthetic */ void lambda$setBlockStatus$71(FacebookGraphQLUtil.FailureCallback failureCallback, Throwable th) {
        Log.e(TAG, "Failed to set block status: ", th);
        failureCallback.callback(th);
    }

    private static JSONObject buildGraphQLVariables(String str, Boolean bool) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("target_id", str);
            jSONObject.put("should_be_blocked", bool);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
