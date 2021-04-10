package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbUnblockGraphQL;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FbUnblockGraphQL {
    private static final String TAG = LoggingUtil.tag(FbUnblockGraphQL.class);
    private static final String docID = "5087050321337464";

    @FunctionalInterface
    public interface SuccessCallback {
        void run();
    }

    public static void unblock(OkHttpClient okHttpClient, String str, String str2, String str3, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, buildGraphQLVariables(str2, str3), str, new FacebookGraphQLUtil.SuccessCallback(failureCallback) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FbUnblockGraphQL$gqV8F34SY9_bLyiicCGopTFyJTk */
            private final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                FbUnblockGraphQL.lambda$unblock$66(FbUnblockGraphQL.SuccessCallback.this, this.f$1, jSONObject);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FbUnblockGraphQL$_pX5f2MllE5biT5fnDnoySw11s */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FbUnblockGraphQL.lambda$unblock$67(FacebookGraphQLUtil.FailureCallback.this, th);
            }
        });
    }

    static /* synthetic */ void lambda$unblock$66(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, JSONObject jSONObject) {
        try {
            FacebookGraphQLUtil.parseField(jSONObject, "fb_unblock");
            successCallback.run();
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON for Facebook unblock: ", e);
            failureCallback.callback(e);
        }
    }

    static /* synthetic */ void lambda$unblock$67(FacebookGraphQLUtil.FailureCallback failureCallback, Throwable th) {
        Log.e(TAG, "Failed to Facebook unblock: ", th);
        failureCallback.callback(th);
    }

    private static JSONObject buildGraphQLVariables(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("actor_id", str);
            jSONObject.put("block_source", "MESSENGER");
            jSONObject.put("target_id", str2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
