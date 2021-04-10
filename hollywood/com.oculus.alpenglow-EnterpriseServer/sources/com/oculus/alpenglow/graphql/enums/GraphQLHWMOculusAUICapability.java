package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusAUICapability {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    CASTING,
    EXPLORE,
    NOTIFICATIONS,
    NUX,
    SEE_ALL_SETTINGS,
    SHARE,
    SOCIAL,
    WIFI;

    public static GraphQLHWMOculusAUICapability fromString(String str) {
        return (GraphQLHWMOculusAUICapability) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
