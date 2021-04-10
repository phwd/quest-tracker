package com.oculus.panelapp.people.graphql;

import android.content.Context;
import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.panelapp.people.graphql.FBFriendRequestsGraphQL;
import com.oculus.panelapp.people.views.actions.constants.FriendingChannel;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FBFriendRequestsGraphQL {
    public static final FriendingChannel FRIENDING_CHANNEL = FriendingChannel.OCULUS_MESSENGER_VR;
    public static final String GRAPHQL_PERSIST_ID = "3438447439616611";
    public static String TAG = LoggingUtil.tag(FBFriendRequestsGraphQL.class);
    @Nullable
    public String mAccessToken;
    @Nullable
    public Context mContext;
    @Nullable
    public OkHttpClient mOkHttpClient;

    public interface SuccessCallback {
        void callback(List<FBSocialUser> list);
    }

    public void destroy() {
        this.mContext = null;
        this.mOkHttpClient = null;
        this.mAccessToken = null;
    }

    private JSONObject buildGraphQLVariables(@Nullable Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("friending_channel", FRIENDING_CHANNEL);
            if (num != null) {
                jSONObject.put("first", num);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private List<FBSocialUser> parseFriendRequestsResults(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("viewer");
        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("friending_possibilities")) == null) {
            return ImmutableList.of();
        }
        return FBGraphQLJSONParseHelper.parseUserNodes(this.mContext, optJSONObject.optJSONArray("nodes"), FBGraphQLJSONParseHelper.parseVrFbPresenceSharing(optJSONObject2), new SimpleClock());
    }

    public void getFriendRequests(@Nullable Integer num, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        if (this.mOkHttpClient.certificatePinner.equals(CertificatePinner.DEFAULT)) {
            Log.e(TAG, "OkHttpClient does not have certificate pinned");
        } else {
            FacebookGraphQLUtil.queryDocID(this.mOkHttpClient, GRAPHQL_PERSIST_ID, buildGraphQLVariables(num), this.mAccessToken, new FacebookGraphQLUtil.SuccessCallback(successCallback, failureCallback) {
                /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendRequestsGraphQL$OpyYatirbUJdffie1tVuD86u1Bo2 */
                public final /* synthetic */ FBFriendRequestsGraphQL.SuccessCallback f$1;
                public final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
                public final void callback(JSONObject jSONObject) {
                    FBFriendRequestsGraphQL.this.lambda$getFriendRequests$0$FBFriendRequestsGraphQL(this.f$1, this.f$2, jSONObject);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendRequestsGraphQL$ahmGXIHY5ggUlNoj7u7sSNjG2v82 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FacebookGraphQLUtil.FailureCallback.this.callback(th);
                }
            });
        }
    }

    public FBFriendRequestsGraphQL(Context context, OkHttpClient okHttpClient, String str) {
        this.mContext = context;
        this.mOkHttpClient = okHttpClient;
        this.mAccessToken = str;
    }

    public /* synthetic */ void lambda$getFriendRequests$0$FBFriendRequestsGraphQL(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, JSONObject jSONObject) {
        try {
            successCallback.callback(parseFriendRequestsResults(jSONObject));
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON ");
            failureCallback.callback(e);
        }
    }
}
