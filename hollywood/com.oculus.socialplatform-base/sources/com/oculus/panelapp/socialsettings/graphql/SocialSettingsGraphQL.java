package com.oculus.panelapp.socialsettings.graphql;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.oc.GraphQLUtil;
import java.util.UUID;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SocialSettingsGraphQL {
    public static final String ANYONE = "ANYONE";
    public static final String ARGUMENT_MUTATION_INPUT = "input";
    public static final String BASE_STATE = "base_state";
    public static final String MESSENGER_SOCIAL_SETTINGS_MUTATION = "Mutation OCChangePrivacyMutation : OCChangePrivacyMutationResponse {privacy_concept_set_privacy(<input>) {client_mutation_id,}}";
    public static final String MESSENGER_SOCIAL_SETTINGS_QUERY = "me() { privacy_settings_list.key(FB_PRESENCE_SHARING) { nodes { key, selected_value } } }";
    public static final String PARAM_CLIENT_MUTATION_ID = "client_mutation_id";
    public static final String PRIVACY = "privacy";
    public static final String PRIVACY_CONCEPT = "privacy_concept";
    public static final String SELF = "SELF";
    public static final String SOURCE = "source";
    public static final String TAG = LoggingUtil.tag(SocialSettingsGraphQL.class);
    public String mAccessToken;
    public OkHttpClient mOkHttpClient;

    public interface SocialSettingsCallback {
        void onFailure();

        void onSuccess(boolean z);
    }

    public static JSONObject getParamsForQuery(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject2.put("source", "HOME_SETTINGS");
            jSONObject2.put(PRIVACY_CONCEPT, "FB_PRESENCE_SHARING");
            JSONObject jSONObject3 = new JSONObject();
            if (z) {
                jSONObject3.put(BASE_STATE, ANYONE);
            } else {
                jSONObject3.put(BASE_STATE, SELF);
            }
            jSONObject2.put(PRIVACY, jSONObject3);
            jSONObject.put("input", jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public void getMessengerActiveStatus(final SocialSettingsCallback socialSettingsCallback) {
        GraphQLUtil.query(this.mOkHttpClient, MESSENGER_SOCIAL_SETTINGS_QUERY, this.mAccessToken, new JSONObject(), new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL.AnonymousClass1 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
                socialSettingsCallback.onFailure();
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                jSONObject.toString();
                try {
                    JSONObject optJSONObject = jSONObject.optJSONObject(jSONObject.keys().next());
                    if (optJSONObject == null) {
                        Log.e(SocialSettingsGraphQL.TAG, "onSuccess: missing obj tag");
                        return;
                    }
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("privacy_settings_list");
                    if (optJSONObject2 == null) {
                        Log.e(SocialSettingsGraphQL.TAG, "onSuccess: missing privacyList tag");
                        return;
                    }
                    JSONArray optJSONArray = optJSONObject2.optJSONArray("nodes");
                    if (optJSONArray == null || optJSONArray.length() < 1) {
                        Log.e(SocialSettingsGraphQL.TAG, "onSuccess: missing nodes tag");
                        return;
                    }
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(0);
                    if (jSONObject2 == null) {
                        Log.e(SocialSettingsGraphQL.TAG, "onSuccess: missing setting tag");
                        return;
                    }
                    String string = jSONObject2.getString("selected_value");
                    if (string == null) {
                        Log.e(SocialSettingsGraphQL.TAG, "onSuccess: missing selected_value tag");
                    } else {
                        socialSettingsCallback.onSuccess(SocialSettingsGraphQL.ANYONE.equals(string));
                    }
                } catch (JSONException e) {
                    Log.e(SocialSettingsGraphQL.TAG, "onSuccess: Failed to retrieve Messenger Active Status", e);
                }
            }
        });
    }

    public void setMessengerActiveStatus(boolean z) {
        GraphQLUtil.post(this.mOkHttpClient, MESSENGER_SOCIAL_SETTINGS_MUTATION, this.mAccessToken, getParamsForQuery(z), new GraphQLUtil.Result() {
            /* class com.oculus.panelapp.socialsettings.graphql.SocialSettingsGraphQL.AnonymousClass2 */

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onFailure(String str) {
            }

            @Override // com.oculus.graphql.oc.GraphQLUtil.Result
            public void onSuccess(JSONObject jSONObject) {
                jSONObject.toString();
            }
        });
    }

    public SocialSettingsGraphQL(String str, OkHttpClient okHttpClient) {
        this.mAccessToken = str;
        this.mOkHttpClient = okHttpClient;
    }
}
