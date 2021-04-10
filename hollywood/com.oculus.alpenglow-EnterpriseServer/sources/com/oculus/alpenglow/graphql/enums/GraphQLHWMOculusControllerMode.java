package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusControllerMode {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    GAZE_MODE_DISABLED,
    GAZE_MODE_WHEN_CONTROLLERS_NOT_FOUND,
    GAZE_MODE_ALWAYS_ENABLED;

    public static GraphQLHWMOculusControllerMode fromString(String str) {
        return (GraphQLHWMOculusControllerMode) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
