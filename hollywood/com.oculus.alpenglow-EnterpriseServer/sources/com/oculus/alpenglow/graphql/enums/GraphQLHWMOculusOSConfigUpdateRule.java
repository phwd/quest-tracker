package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusOSConfigUpdateRule {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    FORCE,
    IDLE;

    public static GraphQLHWMOculusOSConfigUpdateRule fromString(String str) {
        return (GraphQLHWMOculusOSConfigUpdateRule) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
