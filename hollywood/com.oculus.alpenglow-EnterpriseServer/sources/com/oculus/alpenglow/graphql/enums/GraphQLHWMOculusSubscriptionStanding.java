package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusSubscriptionStanding {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    GOOD_STANDING,
    BLACKLISTED;

    public static GraphQLHWMOculusSubscriptionStanding fromString(String str) {
        return (GraphQLHWMOculusSubscriptionStanding) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
