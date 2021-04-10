package com.oculus.horizon.api.fbconnect;

import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.Immutable;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;

@Immutable
public class FBReauthMutationParams {
    public final String mAccessToken;
    public final String mProxyAccessToken;

    public String toString() {
        return GraphQLParamsHelper.encodeMutationParams(ImmutableMap.A06("access_token", this.mAccessToken, "proxy_access_token", this.mProxyAccessToken));
    }

    public FBReauthMutationParams(String str, @Nullable String str2) {
        this.mAccessToken = str;
        this.mProxyAccessToken = str2 == null ? "" : str2;
    }
}
