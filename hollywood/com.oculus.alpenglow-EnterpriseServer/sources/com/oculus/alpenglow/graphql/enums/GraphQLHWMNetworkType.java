package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMNetworkType {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    WPA2;

    public static GraphQLHWMNetworkType fromString(String str) {
        return (GraphQLHWMNetworkType) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
