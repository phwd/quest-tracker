package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class AchievementsParams {
    public static final String PARAM_APPLICATION_ID = "application_id";
    public static final String PARAM_USER_ID = "user_id";
    public final String mApplicationId;
    public final String mUserId;

    public AchievementsParams(String str, String str2) {
        this.mUserId = str;
        this.mApplicationId = str2;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("user_id", this.mUserId);
        A04.put("application_id", this.mApplicationId);
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A04.build());
    }
}
