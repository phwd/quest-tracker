package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusOSCrashReportingState {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    OFF,
    ON;

    public static GraphQLHWMOculusOSCrashReportingState fromString(String str) {
        return (GraphQLHWMOculusOSCrashReportingState) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
