package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusOSDeviceAdmin {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    NONE,
    AIRWATCH,
    MOBILE_IRON;

    public static GraphQLHWMOculusOSDeviceAdmin fromString(String str) {
        return (GraphQLHWMOculusOSDeviceAdmin) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
