package com.oculus.horizon.social.request;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class ApplicationParams {
    public static final String PARAM_AFTER = "after";
    public static final String PARAM_APPLICATION_ID = "application_id";
    public static final String PARAM_FIRST = "first";
    @Nullable
    public final String mAfter;
    public final String mAppId;
    @Nullable
    public final Integer mFirst;

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("application_id", this.mAppId);
        String str = this.mAfter;
        if (str != null) {
            A04.put("after", str);
        }
        Integer num = this.mFirst;
        if (num != null) {
            A04.put("first", num);
        }
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A04.build());
    }

    public ApplicationParams(String str) {
        this.mAppId = str;
        this.mAfter = null;
        this.mFirst = null;
    }

    public ApplicationParams(String str, @Nullable String str2, @Nullable Integer num) {
        this.mAppId = str;
        this.mAfter = str2;
        this.mFirst = num;
    }
}
