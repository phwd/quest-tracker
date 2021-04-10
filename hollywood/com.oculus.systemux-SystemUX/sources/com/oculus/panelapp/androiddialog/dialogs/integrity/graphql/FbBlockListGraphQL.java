package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.FbBlockListGraphQL;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FbBlockListGraphQL {
    private static final String TAG = LoggingUtil.tag(FbBlockListGraphQL.class);
    private static final String docID = "3653518324724454";

    public interface SuccessCallback {
        void callback(HashMap<String, String> hashMap);
    }

    public static void fetch(OkHttpClient okHttpClient, String str, SuccessCallback successCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, new JSONObject(), str, new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$FbBlockListGraphQL$vHovRidFiqL4HLDXiDIa8i_aHc */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                FbBlockListGraphQL.lambda$fetch$64(FbBlockListGraphQL.SuccessCallback.this, jSONObject);
            }
        }, $$Lambda$FbBlockListGraphQL$ljj3ldogWgIXA_8U_katDv_0TkA.INSTANCE);
    }

    static /* synthetic */ void lambda$fetch$64(SuccessCallback successCallback, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = FacebookGraphQLUtil.parseField(jSONObject, "viewer").getJSONObject("blockees").optJSONArray("nodes");
            HashMap<String, String> hashMap = new HashMap<>();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        hashMap.put(optJSONObject.getString("id"), optJSONObject.getString("name"));
                    }
                }
            }
            successCallback.callback(hashMap);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON: ", e);
        }
    }
}
