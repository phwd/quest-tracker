package com.oculus.panelapp.people.graphql;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC13251zE;
import X.AnonymousClass1y2;
import android.annotation.SuppressLint;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestCancelRef;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestHowFound;
import com.oculus.panelapp.people.views.actions.fb.constants.FriendRequestResponseRef;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FBFriendingMutator {
    public static final String GRAPHQL_ACCEPT_FRIEND_REQ_PERSIST_ID = "3599521126775706";
    public static final String GRAPHQL_ADD_FRIEND_PERSIST_ID = "4690367994370470";
    public static final String GRAPHQL_CANCEL_FRIEND_REQ_PERSIST_ID = "3665637860162180";
    public static final String GRAPHQL_DENY_FRIEND_REQ_PERSIST_ID = "3716893108341557";
    public static String TAG = LoggingUtil.tag(FBFriendingMutator.class);
    @Nullable
    public OkHttpClient mClient;
    @Nullable
    public String mFbAccessToken;
    public AbstractC12271xB mTokenDisposable;

    private JSONObject buildAcceptOrDenyFriendReqGraphQLVariables(String str, String str2, FriendRequestResponseRef friendRequestResponseRef) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject2.put("actor_id", str);
            jSONObject2.put("friend_requester_id", str2);
            jSONObject2.put("source", friendRequestResponseRef);
            jSONObject.put("input", jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private JSONObject buildAddFriendGraphQLVariables(String str, String str2, FriendRequestHowFound friendRequestHowFound) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str2);
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject2.put("actor_id", str);
            jSONObject2.put("friend_requestee_ids", jSONArray);
            jSONObject2.put("source", friendRequestHowFound);
            jSONObject.put("input", jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private JSONObject buildCancelFriendReqGraphQLVariables(String str, String str2, FriendRequestCancelRef friendRequestCancelRef) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("client_mutation_id", UUID.randomUUID().toString());
            jSONObject2.put("actor_id", str);
            jSONObject2.put("cancelled_friend_requestee_id", str2);
            jSONObject2.put("source", friendRequestCancelRef);
            jSONObject.put("input", jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    @SuppressLint({"CheckResult"})
    public void acceptFriendRequest(String str, String str2, FacebookGraphQLUtil.SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, FriendRequestResponseRef friendRequestResponseRef) {
        OkHttpClient okHttpClient;
        if (this.mFbAccessToken != null && (okHttpClient = this.mClient) != null) {
            FacebookGraphQLUtil.queryDocID(okHttpClient, GRAPHQL_ACCEPT_FRIEND_REQ_PERSIST_ID, buildAcceptOrDenyFriendReqGraphQLVariables(str, str2, friendRequestResponseRef), this.mFbAccessToken, successCallback, failureCallback);
        }
    }

    @SuppressLint({"CheckResult"})
    public void addFriend(String str, String str2, FacebookGraphQLUtil.SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, FriendRequestHowFound friendRequestHowFound) {
        OkHttpClient okHttpClient;
        if (this.mFbAccessToken != null && (okHttpClient = this.mClient) != null) {
            FacebookGraphQLUtil.queryDocID(okHttpClient, GRAPHQL_ADD_FRIEND_PERSIST_ID, buildAddFriendGraphQLVariables(str, str2, friendRequestHowFound), this.mFbAccessToken, successCallback, failureCallback);
        }
    }

    @SuppressLint({"CheckResult"})
    public void cancelFriendRequest(String str, String str2, FacebookGraphQLUtil.SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, FriendRequestCancelRef friendRequestCancelRef) {
        OkHttpClient okHttpClient;
        if (this.mFbAccessToken != null && (okHttpClient = this.mClient) != null) {
            FacebookGraphQLUtil.queryDocID(okHttpClient, GRAPHQL_CANCEL_FRIEND_REQ_PERSIST_ID, buildCancelFriendReqGraphQLVariables(str, str2, friendRequestCancelRef), this.mFbAccessToken, successCallback, failureCallback);
        }
    }

    @SuppressLint({"CheckResult"})
    public void denyFriendRequest(String str, String str2, FacebookGraphQLUtil.SuccessCallback successCallback, FacebookGraphQLUtil.FailureCallback failureCallback, FriendRequestResponseRef friendRequestResponseRef) {
        OkHttpClient okHttpClient;
        if (this.mFbAccessToken != null && (okHttpClient = this.mClient) != null) {
            FacebookGraphQLUtil.queryDocID(okHttpClient, GRAPHQL_DENY_FRIEND_REQ_PERSIST_ID, buildAcceptOrDenyFriendReqGraphQLVariables(str, str2, friendRequestResponseRef), this.mFbAccessToken, successCallback, failureCallback);
        }
    }

    public void destroy() {
        this.mTokenDisposable.dispose();
        this.mClient = null;
    }

    public FBFriendingMutator(OkHttpClient okHttpClient, AbstractC13251zE<String> r5) {
        boolean equals = okHttpClient.certificatePinner.equals(CertificatePinner.DEFAULT);
        if (equals) {
            Log.e(TAG, "okHttpClient does not have a certificate pinned");
        }
        this.mClient = equals ? null : okHttpClient;
        this.mFbAccessToken = null;
        this.mTokenDisposable = r5.A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.graphql.$$Lambda$FBFriendingMutator$pfij3GZevlsiewONzPBqy73YTo2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBFriendingMutator.this.mFbAccessToken = (String) obj;
            }
        }, $$Lambda$FBFriendingMutator$6iS27FWu7c6G2bALCtA9V6_mo7E2.INSTANCE);
    }

    public /* synthetic */ void lambda$new$0$FBFriendingMutator(String str) throws Exception {
        this.mFbAccessToken = str;
    }
}
