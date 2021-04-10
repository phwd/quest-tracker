package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLRemoteWipeStatus {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    FAILURE,
    NONE,
    PENDING,
    SUCCESS;

    public static GraphQLRemoteWipeStatus fromString(String str) {
        return (GraphQLRemoteWipeStatus) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
