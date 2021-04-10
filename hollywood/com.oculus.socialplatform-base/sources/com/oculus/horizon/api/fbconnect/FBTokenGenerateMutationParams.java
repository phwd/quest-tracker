package com.oculus.horizon.api.fbconnect;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBTokenGenerateMutationParams {
    @Nullable
    public final String cachedAccessToken;
    @Nullable
    public final String overrideOcAppId;

    public String toString() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        String str = this.cachedAccessToken;
        if (!Strings.isNullOrEmpty(str)) {
            builder.put("cached_access_token", str);
        }
        String str2 = this.overrideOcAppId;
        if (!Strings.isNullOrEmpty(str2)) {
            builder.put("override_oc_app_id", str2);
        }
        return GraphQLParamsHelper.encodeMutationParams(builder.build());
    }

    public FBTokenGenerateMutationParams(@Nullable String str, @Nullable String str2) {
        this.cachedAccessToken = str;
        this.overrideOcAppId = str2;
    }
}
