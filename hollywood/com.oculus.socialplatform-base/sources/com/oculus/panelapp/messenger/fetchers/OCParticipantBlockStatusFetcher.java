package com.oculus.panelapp.messenger.fetchers;

import X.AnonymousClass006;
import android.util.Log;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.messengervr.oc.OcThreadParticipantsBlockStatusQueryHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OCParticipantBlockStatusFetcher implements OcThreadParticipantsBlockStatusQueryHandler {
    public static final String TAG = "OCParticipantBlockStatusFetcher";
    public final String mAccessToken;
    public final OkHttpClient mOkHttpClient;

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    private JSONObject buildGraphQLVariables(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(TAG, "error building GraphQL variables");
            return null;
        }
    }

    @Override // com.oculus.messengervr.oc.OcThreadParticipantsBlockStatusQueryHandler
    public void queryParticipantBlockStatuses(final String str, final Consumer<Map<String, Boolean>> consumer, final Consumer<Throwable> consumer2) {
        GraphQLUtil.queryDoc(this.mOkHttpClient, "query($id:ID!) {\n  message_thread(id: $id) {\n    participants_as_user_or_blocked_users {\n      is_blocked_by_viewer\n      possibly_blocked_real_user_id\n    }\n  }\n}", buildGraphQLVariables(str), this.mAccessToken, new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.messenger.fetchers.OCParticipantBlockStatusFetcher.AnonymousClass1 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                Log.e(OCParticipantBlockStatusFetcher.TAG, AnonymousClass006.A07("querying participant block statuses failed: ", str));
                consumer2.accept(new IOException(str));
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                JSONObject optJSONObject;
                try {
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
                    JSONArray jSONArray = null;
                    if (!(optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("message_thread")) == null)) {
                        jSONArray = optJSONObject.optJSONArray("participants_as_user_or_blocked_users");
                    }
                    HashMap hashMap = new HashMap();
                    if (jSONArray != null) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            hashMap.put(jSONObject2.getString("possibly_blocked_real_user_id"), Boolean.valueOf(jSONObject2.getBoolean("is_blocked_by_viewer")));
                        }
                    }
                    consumer.accept(hashMap);
                } catch (JSONException e) {
                    Log.e(OCParticipantBlockStatusFetcher.TAG, "failed to parse participant block statuses: ", e);
                    consumer2.accept(e);
                }
            }
        });
    }

    public OCParticipantBlockStatusFetcher(String str, OkHttpClient okHttpClient) {
        this.mAccessToken = str;
        this.mOkHttpClient = okHttpClient;
    }
}
