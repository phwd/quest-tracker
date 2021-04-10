package com.oculus.horizon.api.fbconnect;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBTokenGenerateMutationParams {
    @Nullable
    private final String cachedAccessToken;
    @Nullable
    private final String overrideOcAppId;

    public FBTokenGenerateMutationParams(@Nullable String str, @Nullable String str2) {
        this.cachedAccessToken = str;
        this.overrideOcAppId = str2;
    }

    public String toString() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        if (!Strings.isNullOrEmpty(this.cachedAccessToken)) {
            builder.put("cached_access_token", this.cachedAccessToken);
        }
        if (!Strings.isNullOrEmpty(this.overrideOcAppId)) {
            builder.put(FBConnectContent.AppScopedAccessToken.OVERRIDE_OC_APP_ID, this.overrideOcAppId);
        }
        return GraphQLParamsHelper.encodeMutationParams(builder.build());
    }
}
