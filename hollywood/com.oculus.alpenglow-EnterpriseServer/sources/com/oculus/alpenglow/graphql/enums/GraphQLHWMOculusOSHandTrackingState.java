package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusOSHandTrackingState {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    DISABLED,
    ENABLED;

    public static GraphQLHWMOculusOSHandTrackingState fromString(String str) {
        return (GraphQLHWMOculusOSHandTrackingState) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
