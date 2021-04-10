package com.oculus.horizon.social.request;

import X.AnonymousClass0th;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.api.rating.ReviewsRequest;
import javax.annotation.concurrent.Immutable;

@Immutable
public class UserSearchParams {
    public static final int USER_SEARCH_COUNT = 30;
    public final String cursor;
    public final String searchString;
    public final SearchType searchType;

    public enum SearchType {
        ALL,
        ID,
        REAL_NAME
    }

    public UserSearchParams(String str, SearchType searchType2, String str2) {
        this.searchString = str;
        this.searchType = searchType2;
        this.cursor = str2;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("query_string", this.searchString);
        A04.put("search_mode", this.searchType.toString());
        ImmutableMap build = A04.build();
        String str = this.cursor;
        if (str == null) {
            str = "";
        }
        AnonymousClass0th.A01("input", build);
        AnonymousClass0th.A01(ReviewsRequest.KEY_COUNT, "30");
        AnonymousClass0th.A01("cursor", str);
        return GraphQLParamsHelper.GSON_CONVERTER.A06(RegularImmutableMap.A00(3, new Object[]{"input", build, ReviewsRequest.KEY_COUNT, "30", "cursor", str}));
    }
}
