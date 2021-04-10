package com.oculus.panelapp.socialandroidbackpanel.graphql;

import X.AnonymousClass006;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;
import com.oculus.provider.OculusContent;
import org.json.JSONException;
import org.json.JSONObject;

public class JoinPartyQueryHelper {
    @VisibleForTesting
    public static final String QUERY_PARTY_INVITE_WITH_ID = "query PartyInviteWithIdQuery($party_id: ID!) {\n  me {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      current_party {\n        id\n      }\n  }\n  node(node_id: $party_id) {\n    ... on Party {\n      id\n      party_type\n      party_leader {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      }\n      invited_by {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      }\n      invited_users {\n          nodes {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n          }\n      }\n      party_users {\n        nodes {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n          }\n      }\n      party_blocked_users {\n        nodes {\n             id\n             alias\n          }\n      }\n      party_blocked_invited_users {\n        nodes {\n             id\n             alias\n          }\n      }\n      party_group_launch {\nid\ndestination {\nid\ndisplay_name\nimage(size_tag: \"80x80\")\napplication {\nid\ndisplay_name\nicon_image(size: \"80x80\") {\n  uri\n}\n}\n}\n      }\n    }\n  }\n}\n";
    @VisibleForTesting
    public static final String QUERY_PARTY_INVITE_WITH_NONCE = "query PartyInviteWithNonceQuery($deeplink_target_id: ID!, $link_nonce: String!) {\n    me {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      current_party {\n        id\n      }\n    }\n  deeplink_target_node(node_id: $deeplink_target_id, nonce: $link_nonce) {\n    ... on Party {\n      id\n      party_type\n      party_leader {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      }\n      invited_by {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n      }\n      invited_users {\n          nodes {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n          }\n      }\n      party_users {\n        nodes {\nid\nalias\nprofile_photo(size:\"80x80\") {\n  uri\n}\n          }\n      }\n      party_blocked_users {\n        nodes {\n             id\n             alias\n          }\n      }\n      party_blocked_invited_users {\n        nodes {\n             id\n             alias\n          }\n      }\n      party_group_launch {\nid\ndestination {\nid\ndisplay_name\nimage(size_tag: \"80x80\")\napplication {\nid\ndisplay_name\nicon_image(size: \"80x80\") {\n  uri\n}\n}\n}\n      }\n    }\n  }\n}\n";
    public static final String TAG = LoggingUtil.tag(JoinPartyQueryHelper.class);

    public interface PartyQueryCallback {
        void onFailure(String str);

        void onSuccess(String str, User user, Party party);
    }

    public static void queryPartyInviteInfoWithId(GraphQLService graphQLService, final PartyQueryCallback partyQueryCallback, @NonNull final String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("party_id", str);
            graphQLService.queryDoc(QUERY_PARTY_INVITE_WITH_ID, jSONObject, new GraphQLService.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.graphql.JoinPartyQueryHelper.AnonymousClass1 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onFailure(String str) {
                    partyQueryCallback.onFailure(str);
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onSuccess(JSONObject jSONObject) {
                    PartyQueryCallback partyQueryCallback;
                    String str;
                    String str2;
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data").getJSONObject(FacebookShareRequest.ME);
                        JSONObject optJSONObject = jSONObject2.optJSONObject(OculusContent.FriendList.CURRENT_PARTY_COLUMN);
                        String string = jSONObject.getJSONObject("data").getJSONObject("node").getString("id");
                        String str3 = str;
                        if (string.equals(str3)) {
                            PartyQueryCallback partyQueryCallback2 = partyQueryCallback;
                            if (optJSONObject != null) {
                                str2 = optJSONObject.getString("id");
                            } else {
                                str2 = null;
                            }
                            partyQueryCallback2.onSuccess(str2, User.fromJSONObject(jSONObject2), Party.fromJSONObject(jSONObject.getJSONObject("data").getJSONObject("node")));
                            return;
                        }
                        throw new Exception(String.format("Party id in response: %s does not match request: %s", string, str3));
                    } catch (JSONException e) {
                        Log.e(JoinPartyQueryHelper.TAG, "Error parsing json response", e);
                        partyQueryCallback = partyQueryCallback;
                        str = AnonymousClass006.A07("Error parsing json response", e.toString());
                        partyQueryCallback.onFailure(str);
                    } catch (Exception e2) {
                        Log.e(JoinPartyQueryHelper.TAG, AnonymousClass006.A07("Error parsing success response: ", e2.toString()));
                        partyQueryCallback = partyQueryCallback;
                        str = "Error parsing success response";
                        partyQueryCallback.onFailure(str);
                    }
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Error building json params", e);
            partyQueryCallback.onFailure(AnonymousClass006.A07("Error building json params", e.toString()));
        }
    }

    public static void queryPartyInviteInfoWithNonce(GraphQLService graphQLService, final PartyQueryCallback partyQueryCallback, @NonNull String str, @NonNull String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deeplink_target_id", str);
            jSONObject.put("link_nonce", str2);
            graphQLService.queryDoc(QUERY_PARTY_INVITE_WITH_NONCE, jSONObject, new GraphQLService.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.graphql.JoinPartyQueryHelper.AnonymousClass2 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onFailure(String str) {
                    partyQueryCallback.onFailure(str);
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService.Callback
                public void onSuccess(JSONObject jSONObject) {
                    String str;
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data").getJSONObject(FacebookShareRequest.ME);
                        JSONObject optJSONObject = jSONObject2.optJSONObject(OculusContent.FriendList.CURRENT_PARTY_COLUMN);
                        PartyQueryCallback partyQueryCallback = partyQueryCallback;
                        if (optJSONObject != null) {
                            str = optJSONObject.getString("id");
                        } else {
                            str = null;
                        }
                        partyQueryCallback.onSuccess(str, User.fromJSONObject(jSONObject2), Party.fromJSONObject(jSONObject.getJSONObject("data").getJSONObject("deeplink_target_node")));
                    } catch (JSONException e) {
                        Log.e(JoinPartyQueryHelper.TAG, "Error parsing json response", e);
                        partyQueryCallback.onFailure(AnonymousClass006.A07("Error parsing json response", e.toString()));
                    }
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "Error building json params", e);
            partyQueryCallback.onFailure(AnonymousClass006.A07("Error building json params", e.toString()));
        }
    }
}
