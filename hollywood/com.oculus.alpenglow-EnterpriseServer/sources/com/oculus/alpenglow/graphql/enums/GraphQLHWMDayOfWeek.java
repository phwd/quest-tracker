package com.oculus.alpenglow.graphql.enums;

import com.facebook.graphql.enums.EnumHelper;

public enum GraphQLHWMDayOfWeek {
    UNSET_OR_UNRECOGNIZED_ENUM_VALUE,
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static GraphQLHWMDayOfWeek fromString(String str) {
        return (GraphQLHWMDayOfWeek) EnumHelper.A00(str, UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
    }
}
