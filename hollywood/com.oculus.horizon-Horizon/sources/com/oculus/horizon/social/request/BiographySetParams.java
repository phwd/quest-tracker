package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.concurrent.Immutable;

@Immutable
public class BiographySetParams {
    public String mBiography;
    public String mUserID;

    public BiographySetParams(String str, String str2) {
        this.mUserID = str;
        this.mBiography = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("user_id", this.mUserID);
        A01.put(OculusContent.FriendList.BIOGRAPHY_STRING, this.mBiography);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
