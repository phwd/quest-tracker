package com.fasterxml.jackson.annotation;

import X.EnumC0242Mp;

public @interface JsonAutoDetect {
    EnumC0242Mp creatorVisibility() default EnumC0242Mp.DEFAULT;

    EnumC0242Mp fieldVisibility() default EnumC0242Mp.DEFAULT;

    EnumC0242Mp getterVisibility() default EnumC0242Mp.DEFAULT;

    EnumC0242Mp isGetterVisibility() default EnumC0242Mp.DEFAULT;

    EnumC0242Mp setterVisibility() default EnumC0242Mp.DEFAULT;
}
