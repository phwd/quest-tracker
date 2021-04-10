package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockGraphQL;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FbBlockGraphQL {
    private static final String TAG = LoggingUtil.tag(FbBlockGraphQL.class);
    private static final String docID = "3985678348118288";

    @FunctionalInterface
    public interface SuccessCallback {
        void run();
    }

    public static void block(OkHttpClient okHttpClient, String str, String str2, String str3, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, buildGraphQLVariables(str2, str3), str, new FacebookGraphQLUtil.SuccessCallback(failureCallback) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FbBlockGraphQL$NeM5QyAPXJsuDsv9s_qtNy9hTz4 */
            private final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                FbBlockGraphQL.lambda$block$62(FbBlockGraphQL.SuccessCallback.this, this.f$1, jSONObject);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FbBlockGraphQL$Qunvf8YvwvJVtCeFihys60VY3Q0 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FbBlockGraphQL.lambda$block$63(FacebookGraphQLUtil.FailureCallback.this, th);
            }
        });
    }

    static /* synthetic */ void lambda$block$62(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, JSONObject jSONObject) {
        try {
            FacebookGraphQLUtil.parseField(jSONObject, "fb_block");
            successCallback.run();
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON for Facebook block: ", e);
            failureCallback.callback(e);
        }
    }

    static /* synthetic */ void lambda$block$63(FacebookGraphQLUtil.FailureCallback failureCallback, Throwable th) {
        Log.e(TAG, "Failed to Facebook block: ", th);
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
