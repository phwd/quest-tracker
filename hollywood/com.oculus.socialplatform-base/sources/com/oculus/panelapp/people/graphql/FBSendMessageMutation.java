package com.oculus.panelapp.people.graphql;

import android.annotation.SuppressLint;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import java.util.UUID;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FBSendMessageMutation {
    public static final String GRAPHQL_SEND_MESSAGE_PERSIST_ID = "5124986577526962";
    public static String TAG = LoggingUtil.tag(FBSendMessageMutation.class);

    public static JSONObject buildSendMessageGraphQLVariables(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject.put("recipient_id", str);
            jSONObject.put("text", str2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    @SuppressLint({"CheckResult"})
    public static void sendMessage(String str, String str2, FacebookGraphQLUtil.SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, String str3, OkHttpClient okHttpClient) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, GRAPHQL_SEND_MESSAGE_PERSIST_ID, buildSendMessageGraphQLVariables(str, str2), str3, successCallback, failureCallback);
    }
}
