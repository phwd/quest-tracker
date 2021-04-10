package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMUnknownSourcesSetting {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    ALLOW,
    DENY;

    public static GraphQLHWMUnknownSourcesSetting fromString(String str) {
        return (GraphQLHWMUnknownSourcesSetting) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
