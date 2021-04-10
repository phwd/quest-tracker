package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusOSConfigTelemetryState {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    OFF,
    ON;

    public static GraphQLHWMOculusOSConfigTelemetryState fromString(String str) {
        return (GraphQLHWMOculusOSConfigTelemetryState) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
