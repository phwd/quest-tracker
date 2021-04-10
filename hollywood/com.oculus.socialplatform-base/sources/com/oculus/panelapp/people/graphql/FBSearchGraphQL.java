package com.oculus.panelapp.people.graphql;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.panelapp.people.graphql.FBSearchGraphQL;
import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FBSearchGraphQL {
    public static final int FETCH_FIRST_COUNT = 100;
    public static final String GRAPHQL_PERSIST_ID = "3643106299088490";
    public static String TAG = LoggingUtil.tag(FBSearchGraphQL.class);
    public String mAccessToken;
    public Context mContext;
    public OkHttpClient mOkHttpClient;

    public interface SuccessCallback {
        void callback(List<FBSocialUser> list);
    }

    public void destroy() {
        this.mContext = null;
        this.mOkHttpClient = null;
        this.mAccessToken = null;
    }

    private JSONObject buildGraphQLVariables(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("query", str);
            jSONObject2.put("limit", 100);
            jSONObject.put(GraphQLQueryConstants.GRAPHQL_QUERY_PARAMS_KEY, jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private List<FBSocialUser> parseSearchResultsFromResult(JSONObject jSONObject) throws JSONException {
        JSONObject parseField = FBGraphQLJSONParseHelper.parseField(FBGraphQLJSONParseHelper.parseField(jSONObject, "users_search"), "results");
        return FBGraphQLJSONParseHelper.parseUserNodes(this.mContext, parseField.optJSONArray("nodes"), FBGraphQLJSONParseHelper.parseVrFbPresenceSharing(jSONObject.optJSONObject("viewer")), new SimpleClock());
    }

    public void searchUsers(String str, SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback) {
        FacebookGraphQLUtil.queryDocID(this.mOkHttpClient, GRAPHQL_PERSIST_ID, buildGraphQLVariables(str), this.mAccessToken, new FacebookGraphQLUtil.SuccessCallback(successCallback, failureCallback) {
            /* class com.oculus.panelapp.people.graphql.$$Lambda$FBSearchGraphQL$tODJlLfhTOL8mTNkvZZIIFGJ2CU2 */
            public final /* synthetic */ FBSearchGraphQL.SuccessCallback f$1;
            public final /* synthetic */ FacebookGraphQLUtil.FailureCallback f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.SuccessCallback
            public final void callback(JSONObject jSONObject) {
                FBSearchGraphQL.this.lambda$searchUsers$0$FBSearchGraphQL(this.f$1, this.f$2, jSONObject);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.graphql.$$Lambda$FBSearchGraphQL$Z2y7M3g484zvxu4fmR9Q5JShUBI2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FacebookGraphQLUtil.FailureCallback.this.callback(th);
            }
        });
    }

    public FBSearchGraphQL(Context context, OkHttpClient okHttpClient, String str) {
        this.mContext = context;
        this.mOkHttpClient = okHttpClient;
        this.mAccessToken = str;
    }

    public /* synthetic */ void lambda$searchUsers$0$FBSearchGraphQL(SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, JSONObject jSONObject) {
        try {
            successCallback.callback(parseSearchResultsFromResult(jSONObject));
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON ");
            failureCallback.callback(e);
        }
    }
}
