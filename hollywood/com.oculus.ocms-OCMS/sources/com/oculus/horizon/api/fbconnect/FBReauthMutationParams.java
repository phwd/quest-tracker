package com.oculus.horizon.api.fbconnect;

import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.Immutable;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;

@Immutable
public class FBReauthMutationParams {
    private final String mAccessToken;
    private final String mProxyAccessToken;

    public FBReauthMutationParams(String str, @Nullable String str2) {
        this.mAccessToken = str;
        this.mProxyAccessToken = str2 == null ? "" : str2;
    }

    public String toString() {
        return GraphQLParamsHelper.encodeMutationParams(ImmutableMap.of("access_token", this.mAccessToken, "proxy_access_token", this.mProxyAccessToken));
    }
}
