package com.oculus.panelapp.people.graphql;

import android.content.Context;
import android.util.Log;
import com.google.common.annotations.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.graphql.model.GraphQLPageInfo;
import com.oculus.horizon.api.rating.ReviewsRequest;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.panelapp.people.graphql.FBFriendListGraphQL;
import com.oculus.panelapp.people.model.FBViewerSocialParty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FBFriendListGraphQL {
    public static final int FETCH_FRIEND_COUNT = 20;
    public static final String GRAPHQL_PERSIST_ID = "3909626505717413";
    public static String TAG = LoggingUtil.tag(FBFriendListGraphQL.class);
    public String mAccessToken;
    public Context mContext;
    public GraphQLPageInfo mGraphQLPageInfo;
    public boolean mIsFetching = false;
    public OkHttpClient mOkHttpClient;
    public String[] mOrderBy;

    public interface SuccessCallback {
        void callback(FBFriendsListResult fBFriendsListResult);
    }

    public void destroy() {
        this.mContext = null;
        this.mOkHttpClient = null;
        this.mAccessToken = null;
        this.mOrderBy = null;
    }

    public /* synthetic */ void lambda$createFailureCallback$1$FBFriendListGraphQL(FacebookGraphQLUtil.FailureCallback failureCallback, Throwable th) {
        this.mIsFetching = false;
        failureCallback.callback(th);
    }

    public /* synthetic */ void lambda$createSuccessCallback$0$FBFriendListGraphQL(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, JSONObject jSONObject) {
        this.mIsFetching = false;
        try {
            JSONObject parseActorFromResult = parseActorFromResult(jSONObject);
            successCallback.callback(new FBFriendsListResult(parseViewerSocialParty(parseActorFromResult), parseFriends(parseActorFromResult, FBGraphQLJSONParseHelper.parseVrFbPresenceSharing(jSONObject.optJSONObject("viewer"))), parseFriendsCount(parseActorFromResult)));
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON: ", e);
            failureCallback.callback(e);
        }
    }

    private JSONObject buildGraphQLVariables(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("orderby", new JSONArray(this.mOrderBy));
            jSONObject.put("first", 20);
            GraphQLPageInfo graphQLPageInfo = this.mGraphQLPageInfo;
            if (graphQLPageInfo != null && z) {
                jSONObject.put("after", graphQLPageInfo.mEndCursor);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private FacebookGraphQLUtil.FailureCallback createFailureCallback(FacebookGraphQLUtil.FailureCallback failureCallback) {
        return new FacebookGraphQLUtil.FailureCallback(failureCallback) {
            /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendListGraphQL$n2bL9uzinAiiSe4xap9mrZeYZQ2 */
            public final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FBFriendListGraphQL.this.lambda$createFailureCallback$1$FBFriendListGraphQL(this.f$1, th);
            }
        };
    }

    private JSONObject parseActorFromResult(JSONObject jSONObject) throws JSONException {
        return FBGraphQLJSONParseHelper.parseField(FBGraphQLJSONParseHelper.parseField(jSONObject, "viewer"), "actor");
    }

    private List<FBSocialUser> parseFriends(JSONObject jSONObject, boolean z) throws JSONException {
        JSONObject parseField = FBGraphQLJSONParseHelper.parseField(jSONObject, "friends");
        this.mGraphQLPageInfo = FBGraphQLJSONParseHelper.parsePageInfo(parseField.optJSONObject("page_info"));
        return FBGraphQLJSONParseHelper.parseUserNodes(this.mContext, parseField.optJSONArray("nodes"), z, new SimpleClock());
    }

    private int parseFriendsCount(JSONObject jSONObject) {
        return jSONObject.optJSONObject("friends").optInt(ReviewsRequest.KEY_COUNT, 0);
    }

    private Set<String> parseNodesID(JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                hashSet.add(optJSONObject.optString("id"));
            }
        }
        return hashSet;
    }

    private FBViewerSocialParty parseViewerSocialParty(JSONObject jSONObject) {
        JSONArray optJSONArray;
        JSONArray optJSONArray2;
        FBViewerSocialParty fBViewerSocialParty = new FBViewerSocialParty();
        JSONObject optJSONObject = jSONObject.optJSONObject("vr_data");
        if (optJSONObject != null) {
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("vr_current_party");
            if (optJSONObject2 != null) {
                fBViewerSocialParty.mCurrentPartyID = optJSONObject2.optString("id");
                fBViewerSocialParty.mHasLinkSharing = optJSONObject2.optBoolean("vr_has_active_link_sharing");
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("vr_invited_users");
                if (!(optJSONObject3 == null || (optJSONArray2 = optJSONObject3.optJSONArray("nodes")) == null)) {
                    fBViewerSocialParty.mCurrentPartyInvitedUserIDs = parseNodesID(optJSONArray2);
                }
            }
            JSONObject optJSONObject4 = optJSONObject.optJSONObject("vr_invited_parties");
            if (!(optJSONObject4 == null || (optJSONArray = optJSONObject4.optJSONArray("nodes")) == null)) {
                fBViewerSocialParty.mInvitedPartyIDs = parseNodesID(optJSONArray);
            }
        }
        return fBViewerSocialParty;
    }

    @VisibleForTesting
    public FacebookGraphQLUtil.SuccessCallback createSuccessCallback(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        return new FacebookGraphQLUtil.SuccessCallback(successCallback, failureCallback) {
            /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendListGraphQL$ttG08R7gx2D7PvlH5vuYjfyV6ZA2 */
            public final /* synthetic */ FBFriendListGraphQL.SuccessCallback f$1;
            public final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                FBFriendListGraphQL.this.lambda$createSuccessCallback$0$FBFriendListGraphQL(this.f$1, this.f$2, jSONObject);
            }
        };
    }

    public void fetchFriends(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        if (!this.mIsFetching) {
            this.mIsFetching = true;
            FacebookGraphQLUtil.queryDocID(this.mOkHttpClient, GRAPHQL_PERSIST_ID, buildGraphQLVariables(false), this.mAccessToken, createSuccessCallback(successCallback, failureCallback), new FacebookGraphQLUtil.FailureCallback(failureCallback) {
                /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendListGraphQL$n2bL9uzinAiiSe4xap9mrZeYZQ2 */
                public final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBFriendListGraphQL.this.lambda$createFailureCallback$1$FBFriendListGraphQL(this.f$1, th);
                }
            });
        }
    }

    public void fetchNext(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        if (!this.mIsFetching && hasNextPage()) {
            this.mIsFetching = true;
            FacebookGraphQLUtil.queryDocID(this.mOkHttpClient, GRAPHQL_PERSIST_ID, buildGraphQLVariables(true), this.mAccessToken, createSuccessCallback(successCallback, failureCallback), new FacebookGraphQLUtil.FailureCallback(failureCallback) {
                /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendListGraphQL$n2bL9uzinAiiSe4xap9mrZeYZQ2 */
                public final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBFriendListGraphQL.this.lambda$createFailureCallback$1$FBFriendListGraphQL(this.f$1, th);
                }
            });
        }
    }

    public boolean hasNextPage() {
        GraphQLPageInfo graphQLPageInfo = this.mGraphQLPageInfo;
        if (graphQLPageInfo == null || !graphQLPageInfo.mHasNextPage) {
            return false;
        }
        return true;
    }

    public FBFriendListGraphQL(Context context, OkHttpClient okHttpClient, String[] strArr, String str) {
        this.mContext = context;
        this.mOkHttpClient = okHttpClient;
        this.mAccessToken = str;
        this.mOrderBy = strArr;
    }
}
