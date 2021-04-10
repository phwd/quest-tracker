package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GameInviteParams {
    public static final String PARAM_SENT_SECONDS_AGO = "sent_seconds_ago";

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put(PARAM_SENT_SECONDS_AGO, Long.valueOf(TimeUnit.MINUTES.toSeconds(10)));
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A04.build());
    }
}
