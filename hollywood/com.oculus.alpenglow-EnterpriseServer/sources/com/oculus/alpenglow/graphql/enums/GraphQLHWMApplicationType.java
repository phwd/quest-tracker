package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMApplicationType {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    ANDROID_APK,
    ANDROID_MDM;

    public static GraphQLHWMApplicationType fromString(String str) {
        return (GraphQLHWMApplicationType) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
