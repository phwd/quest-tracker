package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FetchNameGraphQL;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchNameGraphQL {
    private static final String TAG = LoggingUtil.tag(FetchNameGraphQL.class);
    private static final String docID = "4377776578951778";

    public interface FailureCallback {
        void callback();
    }

    public interface SuccessCallback {
        void callback(String str);
    }

    public static void fetch(OkHttpClient okHttpClient, String str, String str2, SuccessCallback successCallback, FailureCallback failureCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, buildGraphQLVariables(str2), str, new FacebookGraphQLUtil.SuccessCallback(successCallback) {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FetchNameGraphQL$INJhSp1SThm2nRi0r7ve4a1bk3s */
            private final /* synthetic */ FetchNameGraphQL.SuccessCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                FetchNameGraphQL.lambda$fetch$68(FetchNameGraphQL.FailureCallback.this, this.f$1, jSONObject);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FetchNameGraphQL$mguNeIYY3jRRJojxYgG0VvWrQHo */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FetchNameGraphQL.lambda$fetch$69(FetchNameGraphQL.FailureCallback.this, th);
            }
        });
    }

    static /* synthetic */ void lambda$fetch$68(FailureCallback failureCallback, SuccessCallback successCallback, JSONObject jSONObject) {
        try {
            JSONObject parseField = FacebookGraphQLUtil.parseField(jSONObject, "user");
            if (parseField == null) {
                failureCallback.callback();
            } else {
                successCallback.callback(parseField.getString("name"));
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON: ", e);
            failureCallback.callback();
        }
    }

    static /* synthetic */ void lambda$fetch$69(FailureCallback failureCallback, Throwable th) {
        Log.e(TAG, "Failed to parse response when fetching FB user name");
        failureCallback.callback();
    }

    private static JSONObject buildGraphQLVariables(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
