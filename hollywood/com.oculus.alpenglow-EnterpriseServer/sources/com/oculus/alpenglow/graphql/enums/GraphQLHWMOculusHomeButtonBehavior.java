package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusHomeButtonBehavior {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    DISABLED,
    RESUME_ONLY,
    UNRESTRICTED;

    public static GraphQLHWMOculusHomeButtonBehavior fromString(String str) {
        return (GraphQLHWMOculusHomeButtonBehavior) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
