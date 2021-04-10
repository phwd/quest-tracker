package com.oculus.horizon.api.fbconnect;

import X.AnonymousClass0MD;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.modules.PanelCookiesModuleImpl;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONException;
import org.json.JSONObject;

@Immutable
public class FBConnectMutationParams {
    public static final String TAG = "FBConnectMutationParams";
    public final String accessToken;
    public final String facebookId;
    @Nullable
    public final String friendPolicy;
    @Nullable
    public final String loggingExtrasJson;
    @Nullable
    public final String tosVersion;

    public String toString() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("access_token", this.accessToken);
        builder.put("facebook_id", this.facebookId);
        String str = this.friendPolicy;
        if (!Strings.isNullOrEmpty(str)) {
            builder.put("friend_policy", str);
        }
        String str2 = this.tosVersion;
        if (!Strings.isNullOrEmpty(str2)) {
            builder.put("tos_version", str2);
        }
        String str3 = this.loggingExtrasJson;
        if (!Strings.isNullOrEmpty(str3)) {
            try {
                ImmutableList.Builder builder2 = new ImmutableList.Builder();
                JSONObject jSONObject = new JSONObject(str3);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String string = jSONObject.getString(next);
                    ImmutableMap.Builder builder3 = new ImmutableMap.Builder();
                    builder3.put(PanelCookiesModuleImpl.COOKIE_JSON_KEY, next);
                    builder3.put("value", string);
                    builder2.add((Object) builder3.build());
                }
                builder.put("extra_logging_data", builder2.build());
            } catch (JSONException e) {
                AnonymousClass0MD.A0C(TAG, e, "Unable to parse logging extras Json");
            }
        }
        return GraphQLParamsHelper.encodeMutationParams(builder.build());
    }

    public FBConnectMutationParams(String str, String str2, @Nullable FBFriendPolicy fBFriendPolicy, @Nullable String str3, @Nullable String str4) {
        String obj;
        this.accessToken = str;
        this.facebookId = str2;
        if (fBFriendPolicy == null) {
            obj = null;
        } else {
            obj = fBFriendPolicy.toString();
        }
        this.friendPolicy = obj;
        this.tosVersion = str3;
        this.loggingExtrasJson = str4;
    }
}
