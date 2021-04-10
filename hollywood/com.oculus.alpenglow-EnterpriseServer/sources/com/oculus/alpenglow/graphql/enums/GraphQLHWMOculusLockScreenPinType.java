package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusLockScreenPinType {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    NONE,
    PASSWORD,
    PATTERN;

    public static GraphQLHWMOculusLockScreenPinType fromString(String str) {
        return (GraphQLHWMOculusLockScreenPinType) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
