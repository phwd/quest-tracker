package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.BlockListsGraphQL;
import java.util.HashSet;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BlockListsGraphQL {
    private static final String TAG = LoggingUtil.tag(BlockListsGraphQL.class);
    private static final String docID = "3683345938398715";

    public interface SuccessCallback {
        void callback(HashSet<String> hashSet, HashSet<String> hashSet2);
    }

    public static void fetch(OkHttpClient okHttpClient, String str, SuccessCallback successCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, new JSONObject(), str, new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$BlockListsGraphQL$vArdxNgadjFiSPh8JyTShmt2APE */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                BlockListsGraphQL.lambda$fetch$60(BlockListsGraphQL.SuccessCallback.this, jSONObject);
            }
        }, $$Lambda$BlockListsGraphQL$kBcpasHlz5rIruB39nuETkfBo54.INSTANCE);
    }

    static /* synthetic */ void lambda$fetch$60(SuccessCallback successCallback, JSONObject jSONObject) {
        try {
            JSONObject parseField = FacebookGraphQLUtil.parseField(jSONObject, "viewer");
            JSONArray optJSONArray = parseField.optJSONArray("blockee_ids");
            JSONArray optJSONArray2 = parseField.optJSONArray("message_blockee_ids");
            HashSet<String> hashSet = new HashSet<>();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    hashSet.add(optJSONArray.getString(i));
                }
            }
            HashSet<String> hashSet2 = new HashSet<>();
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    hashSet2.add(optJSONArray2.getString(i2));
                }
            }
            successCallback.callback(hashSet, hashSet2);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON: ", e);
        }
    }
}
