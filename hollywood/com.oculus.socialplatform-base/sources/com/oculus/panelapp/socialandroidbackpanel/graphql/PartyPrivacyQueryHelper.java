package com.oculus.panelapp.socialandroidbackpanel.graphql;

import X.AnonymousClass006;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;
import org.json.JSONException;
import org.json.JSONObject;

public class PartyPrivacyQueryHelper {
    @VisibleForTesting
    public static final String FETCH_PARTY_PRIVACY_INFO = "query PartyPrivacyInfoQuery($party_id: ID!) {  node(node_id: $party_id) {    ... on Party {      id      party_type      has_active_link_sharing      url_for_deeplink_target_web_page    }  }}";
    @VisibleForTesting
    public static final String PARTY_ACTIVATE_LINK_SHARING = "mutation ($party_id: ID!) {  activate_link_sharing_party(data: {    client_mutation_id: 0,    party_id: $party_id  }) {    client_mutation_id    party {      id      has_active_link_sharing    }  }}";
    @VisibleForTesting
    public static final String PARTY_DEACTIVATE_LINK_SHARING = "mutation ($party_id: ID!) {  deactivate_link_sharing_party(data: {    client_mutation_id: 0,    party_id: $party_id  }) {    client_mutation_id    party {      id      has_active_link_sharing    }  }}";
    public static final String PARTY_HAS_ACTIVE_LINK_SHARING_PARAM = "has_active_link_sharing";
    public static final String PARTY_ID_PARAM = "party_id";
    @VisibleForTesting
    public static final String PARTY_SET_TYPE = "mutation ($party_id: ID!, $party_type: PartyType!) {  party_set_type(data: {    client_mutation_id: 0,    party_id: $party_id,    party_type: $party_type  }) {    client_mutation_id    party {      id      party_type    }  }}";
    public static final String PARTY_TYPE_PARAM = "party_type";
    public static final String PARTY_URL_PARAM = "url_for_deeplink_target_web_page";
    public static final String TAG = LoggingUtil.tag(PartyPrivacyQueryHelper.class);

    public interface PartyPrivacyQueryCallback {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static void fetchPartyPrivacyInfo(GraphQLService graphQLService, final PartyPrivacyQueryCallback partyPrivacyQueryCallback, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("party_id", str);
            graphQLService.queryDoc(FETCH_PARTY_PRIVACY_INFO, jSONObject, new GraphQLService.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.AnonymousClass1 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onFailure(String str) {
                    partyPrivacyQueryCallback.onFailure(str);
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onSuccess(JSONObject jSONObject) {
                    jSONObject.toString();
                    try {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data").optJSONObject("node");
                        if (!optJSONObject.has("party_type") || PartyPrivacyType.fromString(optJSONObject.getString("party_type")) != PartyPrivacyType.UNKNOWN) {
                            partyPrivacyQueryCallback.onSuccess(optJSONObject);
                            return;
                        }
                        throw new Exception("Invalid party type");
                    } catch (Exception e) {
                        partyPrivacyQueryCallback.onFailure(AnonymousClass006.A07("Error parsing success response: ", e.toString()));
                    }
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Error building json params", e);
            partyPrivacyQueryCallback.onFailure(AnonymousClass006.A07("Error building json params", e.toString()));
        }
    }

    public static void setPartyHasActiveLinkSharing(GraphQLService graphQLService, final PartyPrivacyQueryCallback partyPrivacyQueryCallback, @NonNull String str, @NonNull final boolean z) {
        String str2;
        final String str3;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("party_id", str);
            jSONObject.put(PARTY_HAS_ACTIVE_LINK_SHARING_PARAM, z);
            if (z) {
                str2 = PARTY_ACTIVATE_LINK_SHARING;
                str3 = "activate_link_sharing_party";
            } else {
                str2 = PARTY_DEACTIVATE_LINK_SHARING;
                str3 = "deactivate_link_sharing_party";
            }
            graphQLService.queryDoc(str2, jSONObject, new GraphQLService.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.AnonymousClass2 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onFailure(String str) {
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onSuccess(JSONObject jSONObject) {
                    jSONObject.toString();
                    try {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data").optJSONObject(str3).optJSONObject("party");
                        if (!optJSONObject.has(PartyPrivacyQueryHelper.PARTY_HAS_ACTIVE_LINK_SHARING_PARAM)) {
                            partyPrivacyQueryCallback.onFailure("Error setting party link sharing status");
                        } else if (optJSONObject.getBoolean(PartyPrivacyQueryHelper.PARTY_HAS_ACTIVE_LINK_SHARING_PARAM) == z) {
                            partyPrivacyQueryCallback.onSuccess(optJSONObject);
                        } else {
                            partyPrivacyQueryCallback.onFailure("Error party link sharing response mismatch");
                        }
                    } catch (Exception e) {
                        partyPrivacyQueryCallback.onFailure(AnonymousClass006.A07("Error parsing success response: ", e.toString()));
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error building json params", e);
            partyPrivacyQueryCallback.onFailure(AnonymousClass006.A07("Error building json params", e.toString()));
        }
    }

    public static void setPartyPrivacyType(GraphQLService graphQLService, final PartyPrivacyQueryCallback partyPrivacyQueryCallback, @NonNull String str, @NonNull final PartyPrivacyType partyPrivacyType) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("party_id", str);
            jSONObject.put("party_type", partyPrivacyType.toString());
            graphQLService.queryDoc(PARTY_SET_TYPE, jSONObject, new GraphQLService.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.AnonymousClass3 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onFailure(String str) {
                    partyPrivacyQueryCallback.onFailure(str);
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onSuccess(JSONObject jSONObject) {
                    jSONObject.toString();
                    try {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data").optJSONObject("party_set_type").optJSONObject("party");
                        PartyPrivacyType fromString = PartyPrivacyType.fromString(optJSONObject.optString("party_type"));
                        if (PartyPrivacyType.UNKNOWN == fromString) {
                            partyPrivacyQueryCallback.onFailure("Error setting party type");
                        } else if (partyPrivacyType != fromString) {
                            partyPrivacyQueryCallback.onFailure("Error party type response mismatch");
                        } else {
                            partyPrivacyQueryCallback.onSuccess(optJSONObject);
                        }
                    } catch (Exception e) {
                        partyPrivacyQueryCallback.onFailure(AnonymousClass006.A07("Error parsing success response: ", e.toString()));
                    }
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Error building json params", e);
            partyPrivacyQueryCallback.onFailure(AnonymousClass006.A07("Error building json params", e.toString()));
        }
    }
}
