package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMOculusSubscriptionFeature {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    ARVR_ENTERPRISE_MVP;

    public static GraphQLHWMOculusSubscriptionFeature fromString(String str) {
        return (GraphQLHWMOculusSubscriptionFeature) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
