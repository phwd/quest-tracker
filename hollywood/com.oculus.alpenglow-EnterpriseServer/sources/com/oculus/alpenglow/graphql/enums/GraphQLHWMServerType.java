package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMServerType {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    HTTP,
    DROPBOX;

    public static GraphQLHWMServerType fromString(String str) {
        return (GraphQLHWMServerType) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
