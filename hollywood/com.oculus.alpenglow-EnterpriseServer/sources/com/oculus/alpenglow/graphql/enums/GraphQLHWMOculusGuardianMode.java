package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusGuardianMode {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    ENABLED,
    DISABLED;

    public static GraphQLHWMOculusGuardianMode fromString(String str) {
        return (GraphQLHWMOculusGuardianMode) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
