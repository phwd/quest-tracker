package com.oculus.horizon.social.request;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class FriendListParams {
    public static final String PARAM_FIRST = "first";
    public static final String PARAM_ORDER_BY = "orderby";
    @Nullable
    public final Integer mFirst;
    public final String[] mOrderby;

    public FriendListParams(String[] strArr, @Nullable Integer num) {
        this.mOrderby = strArr;
        this.mFirst = num;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("orderby", this.mOrderby);
        Integer num = this.mFirst;
        if (num != null) {
            A04.put("first", num);
        }
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A04.build());
    }
}
