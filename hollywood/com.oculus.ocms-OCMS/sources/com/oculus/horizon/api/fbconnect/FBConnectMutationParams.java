package com.oculus.horizon.api.fbconnect;

import com.facebook.debug.log.BLog;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONException;
import org.json.JSONObject;

@Immutable
public class FBConnectMutationParams {
    private static final String TAG = "FBConnectMutationParams";
    private final String accessToken;
    private final String facebookId;
    @Nullable
    private final String friendPolicy;
    @Nullable
    private final String loggingExtrasJson;
    @Nullable
    private final String tosVersion;

    public FBConnectMutationParams(String str, String str2, @Nullable FBFriendPolicy fBFriendPolicy, @Nullable String str3, @Nullable String str4) {
        String str5;
        this.accessToken = str;
        this.facebookId = str2;
        if (fBFriendPolicy == null) {
            str5 = null;
        } else {
            str5 = fBFriendPolicy.toString();
        }
        this.friendPolicy = str5;
        this.tosVersion = str3;
        this.loggingExtrasJson = str4;
    }

    public String toString() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("access_token", this.accessToken);
        builder.put("facebook_id", this.facebookId);
        if (!Strings.isNullOrEmpty(this.friendPolicy)) {
            builder.put("friend_policy", this.friendPolicy);
        }
        if (!Strings.isNullOrEmpty(this.tosVersion)) {
            builder.put("tos_version", this.tosVersion);
        }
        if (!Strings.isNullOrEmpty(this.loggingExtrasJson)) {
            try {
                ImmutableList.Builder builder2 = new ImmutableList.Builder();
                JSONObject jSONObject = new JSONObject(this.loggingExtrasJson);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    builder2.add((Object) new ImmutableMap.Builder().put("key", next).put("value", jSONObject.getString(next)).build());
                }
                builder.put("extra_logging_data", builder2.build());
            } catch (JSONException e) {
                BLog.e(TAG, e, "Unable to parse logging extras Json");
            }
        }
        return GraphQLParamsHelper.encodeMutationParams(builder.build());
    }
}
