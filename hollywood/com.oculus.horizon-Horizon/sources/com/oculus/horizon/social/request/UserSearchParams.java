package com.oculus.horizon.social.request;

import X.AnonymousClass0p6;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.concurrent.Immutable;

@Immutable
public class UserSearchParams {
    public static final int USER_SEARCH_COUNT = 30;
    public final String cursor = null;
    public final String searchString;
    public final SearchType searchType;

    public enum SearchType {
        ALL,
        ID,
        REAL_NAME
    }

    public UserSearchParams(String str, SearchType searchType2) {
        this.searchString = str;
        this.searchType = searchType2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put(OculusContent.FriendList.USER_SEARCH_STRING_PARAM, this.searchString);
        A01.put(OculusContent.FriendList.USER_SEARCH_TYPE_PARAM, this.searchType.toString());
        ImmutableMap build = A01.build();
        String str = this.cursor;
        if (str == null) {
            str = "";
        }
        AnonymousClass0p6.A01("input", build);
        AnonymousClass0p6.A01("count", "30");
        AnonymousClass0p6.A01("cursor", str);
        return GraphQLParamsHelper.GSON_CONVERTER.A06(RegularImmutableMap.A00(3, new Object[]{"input", build, "count", "30", "cursor", str}));
    }
}
