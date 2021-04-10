package com.oculus.horizon.social.request;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.social.request.FriendRequestSource;
import javax.annotation.concurrent.Immutable;

@Immutable
public class UserMutationParams {
    public static final String PARAM_BLOCKED_USER_ID = "blocked_user_id";
    public static final String PARAM_FRIEND = "friend";
    public static final String PARAM_FRIEND_REQUESTEE = "friend_requestee";
    public static final String PARAM_FRIEND_REQUESTER = "friend_requester";
    public static final String PARAM_SOURCE = "source";
    public static final String PARAM_USER = "user";
    public static final String PARAM_USER_ID = "user_id";
    public final String parameterName;
    public String source;
    public final String userId;

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put(this.parameterName, this.userId);
        String str = this.source;
        if (!Strings.isNullOrEmpty(str)) {
            A01.put("source", str);
        }
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }

    public UserMutationParams(String str, String str2) {
        this.userId = str;
        this.parameterName = str2;
    }

    public UserMutationParams(String str, String str2, FriendRequestSource.Origin origin) {
        this.userId = str;
        this.parameterName = str2;
        this.source = origin.toString();
    }
}
