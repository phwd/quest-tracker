package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ChatMutationParams {
    public static final String PARAM_BODY = "body";
    public static final String PARAM_THREAD_ID = "thread_id";
    public final String body;
    public final String threadId;

    public ChatMutationParams(String str, String str2) {
        this.threadId = str;
        this.body = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("thread_id", this.threadId);
        A01.put("body", this.body);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
