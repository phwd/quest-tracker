package com.oculus.panelapp.androiddialog.dialogs.integrity.graphql;

import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerThreadParticipant;
import com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.MessengerThreadParticipantsGraphQL;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessengerThreadParticipantsGraphQL {
    private static final String TAG = LoggingUtil.tag(MessengerThreadParticipantsGraphQL.class);
    private static final String docID = "3466407500148030";

    public interface SuccessCallback {
        void run(List<MessengerThreadParticipant> list);
    }

    public static void fetch(OkHttpClient okHttpClient, String str, String str2, SuccessCallback successCallback) {
        FacebookGraphQLUtil.queryDocID(okHttpClient, docID, buildGraphQLVariables(str2), str, new FacebookGraphQLUtil.SuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.integrity.graphql.$$Lambda$MessengerThreadParticipantsGraphQL$StQDR3xhQQTJ03T4Q5tj4tX_uaQ */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                MessengerThreadParticipantsGraphQL.lambda$fetch$72(MessengerThreadParticipantsGraphQL.SuccessCallback.this, jSONObject);
            }
        }, $$Lambda$MessengerThreadParticipantsGraphQL$e3F1zSwTYbUYsiNEvSMGaIbcQns.INSTANCE);
    }

    static /* synthetic */ void lambda$fetch$72(SuccessCallback successCallback, JSONObject jSONObject) {
        try {
            JSONObject parseField = FacebookGraphQLUtil.parseField(FacebookGraphQLUtil.parseField(jSONObject, "message_thread"), "other_participants");
            if (parseField == null) {
                Log.d(TAG, "other_participants field is null when parsing thread participants");
                return;
            }
            JSONArray optJSONArray = parseField.optJSONArray("nodes");
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.getJSONObject(i).optJSONObject("messaging_actor");
                    if (optJSONObject == null) {
                        Log.d(TAG, "messaging_actor field is null when parsing a participant node");
                    } else {
                        String string = optJSONObject.getString("id");
                        String string2 = optJSONObject.getString("name");
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject(FBConnectContent.Account.PROFILE_PICTURE);
                        String str = null;
                        if (optJSONObject2 != null) {
                            str = optJSONObject2.getString(AssistantComponentContract.Components.RemoteImageViewComponent.URI);
                        }
                        arrayList.add(new MessengerThreadParticipant(string, str, string2));
                    }
                }
            }
            successCallback.run(arrayList);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON when fetching messenger thread participants: ", e);
        }
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
