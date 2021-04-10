package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class BiographySetParams {
    public String mBiography;
    public String mUserID;

    public BiographySetParams(String str, String str2) {
        this.mUserID = str;
        this.mBiography = str2;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("user_id", this.mUserID);
        A04.put("biography", this.mBiography);
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
